package com.ssh.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorityFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object obj) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Subject subject = getSubject(request, servletResponse);
        String roles[] = (String[]) obj;
        if(roles == null || roles.length == 0) {
            return true;
        }
        return false;
    }
}
