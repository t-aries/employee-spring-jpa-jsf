package vn.vissoft.employee.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.vissoft.employee.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Employee> findAll() {

        Query query = entityManager.createQuery("select e FROM Employee e");
        return query.getResultList();

    }

    @Override
    public List<Employee> findByName(String name, String employeeCode, String department) {
        String sql = "select e from Employee e where 1=1 ";

        if (!"".equals(name)) {
            sql = sql + " and (e.name like:name)";
        }
        if (!"".equals(employeeCode)) {
            sql = sql + " and (e.employeeCode like:employeeCode)";
        }
        if (!"".equals(department)) {
            sql = sql + " and (e.department like:department)";
        }
//        if (salary != null) {
//            sql = sql + " and (e.department between :num1 and :num2)";
//        }

        Query query = entityManager.createQuery(sql);

        if (!"".equals(name)) {
            query.setParameter("name", "%" + name + "%");
        }
        if (!"".equals(employeeCode)) {
            query.setParameter("employeeCode", "%" + employeeCode + "%");
        }
        if (!"".equals(department)) {
            query.setParameter("department", "%" + department + "%");
        }
//        if (salary != null) {
//            query.setParameter("num1", salary);
//        }

        return query.getResultList();
    }

    @Override
    @Transactional
    public void create(Employee employee) {
        this.entityManager.persist(employee);

    }

//    @Override
//    @Transactional
//    public void create(Employee employee) {
//
//        Query query = entityManager.createNativeQuery("INSERT INTO Employee (NAME, SALARY, DEPARTMENT  ) VALUES (?,?,?)",Employee.class);
//        query.setParameter(1, employee.getName());
//        query.setParameter(2, employee.getSalary());
//        query.setParameter(3, employee.getDepartment());
//        query.executeUpdate();
////        entityManager.persist(employee);
//
//    }


    @Override
    public void update(Employee employee) {
        entityManager.merge(employee);

    }

    @Override
    public void delete(Employee employee) {
        entityManager.remove(entityManager.merge(employee));

    }
}
