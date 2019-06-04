package vn.vissoft.employee.repository;


import vn.vissoft.employee.model.Employee;

import java.util.List;


public interface EmployeeRepository {

    List findAll();

    Employee findById(Long id);

    void create(Employee employee);

    void delete(Employee employee);

    void update(Employee employee);
}
