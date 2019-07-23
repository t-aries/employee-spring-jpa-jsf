package vn.vissoft.employee.service;

import org.springframework.stereotype.Service;
import vn.vissoft.employee.model.Employee;
import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> findAll();

    List findByName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo);

    Employee findById(Long id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
