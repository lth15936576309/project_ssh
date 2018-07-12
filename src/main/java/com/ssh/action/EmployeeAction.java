package com.ssh.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Employee;
import com.ssh.service.IEmployeeService;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {

    private Employee employee = new Employee();

    private IEmployeeService employeeService;

    /**
     * 用户登录
     * @return
     */
    public String login() {
        Employee loginEmployee = employeeService.login(employee);
        if(loginEmployee == null) {
            addActionError("用户名或密码错误");
            return INPUT;
        } else {
            ActionContext.getContext().getSession().put("loginEmployee",loginEmployee);
            return SUCCESS;
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
