package vn.vissoft.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
