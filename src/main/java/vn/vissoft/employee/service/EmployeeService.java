package vn.vissoft.employee.service;

import org.springframework.stereotype.Service;
import vn.vissoft.employee.model.Employee;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    void save(Employee employee);

    void deleteById(Long id);
}
