package vn.vissoft.employee.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

@SessionScoped
@Named
@Join(path = "/", to = ("/employee-list.jsf"))
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = employeeService.findAll();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
