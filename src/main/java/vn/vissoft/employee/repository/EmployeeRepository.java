package vn.vissoft.employee.repository;

import vn.vissoft.employee.model.Employee;

import java.util.List;


public interface EmployeeRepository {

    List<Employee> findAll();

    List findByName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo);

    Employee findById(Long id);

    void create(Employee employee);

    void delete(Employee employee);

    void update(Employee employee);
}
