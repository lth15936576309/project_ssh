package com.ssh.shiro.session;

import com.ssh.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisUtil redisUtil;

    private static final String SHIRO_SESSION_PREFIX = "session:";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession(Session session) {
        if(session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            redisUtil.set(key, value);
            redisUtil.expire(key, 600);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if(serializable == null) {
            return null;
        }
        byte[] key = getKey(serializable.toString());
        byte[] value = redisUtil.get(key);
        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session != null && session.getId() != null) {
            saveSession(session);
        }
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null) {
            return;
        }
        byte[] key = getKey(session.getId().toString());
        redisUtil.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = redisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<>();
        if(CollectionUtils.isEmpty(keys)) {
            return sessions;
        }
        for(byte[] key : keys) {
            Session session = (Session) SerializationUtils.deserialize(redisUtil.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
