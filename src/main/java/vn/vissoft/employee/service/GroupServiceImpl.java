package vn.vissoft.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.vissoft.employee.model.GroupEmp;
import vn.vissoft.employee.repository.GroupRepository;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<GroupEmp> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public GroupEmp findById(Long id) {
        return groupRepository.findById(id);
    }
}
