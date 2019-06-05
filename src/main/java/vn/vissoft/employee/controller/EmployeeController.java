package vn.vissoft.employee.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@SessionScoped
//@ManagedBean(name = "employeeController")
@Named
@Join(path = "/", to = ("/employee-list.jsf"))
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    private List<Employee> employees;

    private Employee employee = new Employee();

    private String name;
    private String salaryFrom;
    private String salaryTo;

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }

    @PostConstruct
    public void init() {

        employees = employeeService.findAll();
    }

    public String add() {
        this.employee = new Employee();
        return "/employee-create.xhtml?faces-redirect=true";
    }

    public String save() {
        this.employeeService.create(employee);
        employees = employeeService.findAll();
        return "/employee-list.xhtml?faces-redirect=true";
    }

    public String delete(Employee employee) {
        this.employeeService.delete(employee);
        employees = employeeService.findAll();
        return "/employee-list.xhtml?faces-redirect=true";
    }


    public String viewEdit(Employee employee){
        this.employee = employee;
        return "/employee-edit.xhtml?faces-redirect=true";
    }

    public String editEmployee(Employee employee) {
        this.employeeService.update(employee);
        employees = employeeService.findAll();
        return "/employee-list.xhtml?faces-redirect=true";
    }

    public String searchName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo){
        employees = employeeService.findByName(name,employeeCode,department, salaryFrom, salaryTo);
        return "employee-list.xhtml?faces-redirect=true";
    }
    private Double num1;
    private Double num2;

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
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

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
