package vn.vissoft.employee.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
    public String init() {
        employees = employeeService.findAll();
        return "/employee-list.xhtml?faces-redirect=true";
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


    public String viewEdit(Employee employee) {
        this.employee = employee;
        return "/employee-edit.xhtml?faces-redirect=true";
    }

    public String editEmployee(Employee employee) {
        this.employeeService.update(employee);
        employees = employeeService.findAll();
        return "/employee-list.xhtml?faces-redirect=true";
    }

    public String searchName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo) {
        employees = employeeService.findByName(name, employeeCode, department, salaryFrom, salaryTo);
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

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllAdmins() {
        List<Employee> employees = employeeService.findAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        Employee employee = employeeService.findById(id);
        System.out.println(employee);
        if (employee == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/employees/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        employeeService.create(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployees(@PathVariable("id") long id, @RequestBody Employee employee) {
        Employee currentEmployee = employeeService.findById(id);
        if (currentEmployee == null) {
            System.out.println("User not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else {
            currentEmployee.setName(employee.getName());
            currentEmployee.setSalary(employee.getSalary());
            currentEmployee.setDateStart(employee.getDateStart());
            currentEmployee.setEmployeeCode(employee.getEmployeeCode());
            currentEmployee.setDepartment(employee.getDepartment());
            currentEmployee.setManager(employee.getManager());
            currentEmployee.setWork(employee.getWork());
            employeeService.update(currentEmployee);
            return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployees(@PathVariable("id") long id){
        Employee employee = employeeService.findById(id);
        if (employee == null){
            return  new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else {
            employeeService.delete(employee);
            return  new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
        }
    }

}
