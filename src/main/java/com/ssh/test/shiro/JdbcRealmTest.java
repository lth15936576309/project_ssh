package com.ssh.test.shiro;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class JdbcRealmTest {


    ComboPooledDataSource dataSource;

    {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        dataSource = (ComboPooledDataSource) applicationContext.getBean("dataSource");
    }

    @Test
    public void testAuthentication() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //设置加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        jdbcRealm.setCredentialsMatcher(matcher);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        String password = new Md5Hash("111111", "username").toString();
        UsernamePasswordToken token = new UsernamePasswordToken("lth", password);
        subject.login(token);
        subject.checkRole("admin");
        subject.logout();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
    }
}
