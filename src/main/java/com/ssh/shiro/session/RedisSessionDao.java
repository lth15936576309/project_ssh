package com.ssh.shiro.session;

import com.ssh.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisUtil redisUtil;

    private static final String SHIRO_SESSION_PREFIX = "session:";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        byte[] key = getKey(session.getId().toString());
        return null;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
