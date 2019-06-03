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

    private Employee employee;

    @PostConstruct
    public void init() {

        employees = employeeService.findAll();
    }

    public String add() {
        this.employee = new Employee();
        return "/employee-create.xhtml?faces-redirect=true";
    }

    public String save() {
        this.employee = new Employee();
        this.employeeService.create(employee);
        return "/employee-list.xhtml?faces-redirect=true";
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
