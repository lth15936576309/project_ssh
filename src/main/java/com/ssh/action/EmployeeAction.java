package com.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Employee;
import com.ssh.service.IEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {

    private Logger logger = LoggerFactory.getLogger(EmployeeAction.class);

    private Employee employee = new Employee();

    private IEmployeeService employeeService;

    /**
     * 用户登录
     * @return
     */
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        //String password = new Md5Hash(employee.getPassword()).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(employee.getUsername(), employee.getPassword());
        try {
            subject.login(token);
            subject.logout();
            return SUCCESS;
        } catch (AuthenticationException e) {
            logger.info(e.getMessage());
            addActionError("用户名或密码错误");
            return INPUT;
        }
    }

    @Override
    public Employee getModel() {
        return employee;
    }

    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
