package com.ssh.dao;

import com.ssh.domain.Employee;

public interface IEmployeeDao {

    Employee findByUsername(Employee employee);

    Employee findByUsernameAndPassword(Employee employee);
}
