package vn.vissoft.employee.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
@Join(path = "/create", to = ("/employee-create.jsf"))
public class EmployeeCreateController {

    @Autowired
    EmployeeService employeeService;
    private Employee employee;

    @PostConstruct
    public void init() {
        employee = new Employee();
    }


    public String add() {

        employeeService.save(employee);

        employee = new Employee();
        return "/employee-list.xhtml?faces-redirect=true";
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
