package com.ssh.service.impl;

import com.ssh.dao.IEmployeeDao;
import com.ssh.domain.Employee;
import com.ssh.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeDao employeeDao;

    @Override
    public Employee login(Employee employee) {
        Employee loginEmployee = employeeDao.findByUsernameAndPassword(employee);
        return loginEmployee;
    }

    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
