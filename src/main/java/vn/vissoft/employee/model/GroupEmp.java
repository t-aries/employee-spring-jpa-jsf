package vn.vissoft.employee.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@SequenceGenerator(name="seq")
public class GroupEmp {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    public GroupEmp() {
    }

    public GroupEmp(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public GroupEmp(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
