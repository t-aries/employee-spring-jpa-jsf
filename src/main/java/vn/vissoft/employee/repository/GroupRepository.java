package vn.vissoft.employee.repository;

import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.model.GroupEmp;

import java.util.List;

public interface GroupRepository {
    List findAll();

    GroupEmp findById(Long id);
}
