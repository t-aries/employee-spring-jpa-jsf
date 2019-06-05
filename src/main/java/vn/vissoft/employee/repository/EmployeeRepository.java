package vn.vissoft.employee.repository;


import vn.vissoft.employee.model.Employee;

import java.util.List;


public interface EmployeeRepository {

    List findAll();

    List findByName(String name, String employeeCode, String department);

    void create(Employee employee);

    void delete(Employee employee);

    void update(Employee employee);
}
