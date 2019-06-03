package vn.vissoft.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.vissoft.employee.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {



}
