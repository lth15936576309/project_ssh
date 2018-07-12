package com.ssh.dao;

import com.ssh.domain.Employee;

public interface IEmployeeDao {
    Employee findByUsernameAndPassword(Employee employee);
}
