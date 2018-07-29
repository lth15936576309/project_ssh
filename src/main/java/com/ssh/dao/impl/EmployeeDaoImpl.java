package com.ssh.dao.impl;

import com.ssh.dao.IEmployeeDao;
import com.ssh.domain.Employee;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class EmployeeDaoImpl extends HibernateDaoSupport implements IEmployeeDao {

    @Override
    public Employee findByUsername(Employee employee) {
        String hql = "from Employee where username = ?";
        List<Employee> list = (List<Employee>) this.getHibernateTemplate().find(hql, employee.getUsername());
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Employee findByUsernameAndPassword(Employee employee) {
        String hql = "from Employee where username = ? and password = ?";
        List<Employee> list = (List<Employee>) this.getHibernateTemplate().find(hql, employee.getUsername(), employee.getPassword());
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


}
