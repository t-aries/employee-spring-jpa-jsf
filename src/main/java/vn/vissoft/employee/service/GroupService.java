package vn.vissoft.employee.service;

import vn.vissoft.employee.model.GroupEmp;

import java.util.List;

public interface GroupService {

    List<GroupEmp> findAll();

    GroupEmp findById(Long id);
}
