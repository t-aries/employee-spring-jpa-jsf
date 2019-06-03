package vn.vissoft.employee.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.vissoft.employee.model.Employee;

import java.util.List;


public interface EmployeeRepository  {

    public List findAll();

    public Employee findById(Long id);

    public void create(Employee employee);

    public void deleteById(Employee employee);

    public void update(Employee employee);
}
