package vn.vissoft.employee.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.vissoft.employee.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("select e from Employee e ");
        return query.getResultList();
    }

    @Override
    public Employee findById(Long id) {
        Query query = entityManager.createQuery("select e from Employee e where e.id=:id")
                .setParameter("id", id);
        return (Employee) query.getSingleResult();
    }

    @Override
    public void create(Employee employee) {
       entityManager.persist(employee);

    }

//    @Override
//    public void create(Employee employee) {
//        Query query = entityManager.createQuery()
//        entityManager.persist(employee);
//
//    }


    @Override
    public void deleteById(Employee employee) {
        entityManager.merge(employee);

    }

    @Override
    public void update(Employee employee) {
        entityManager.remove(entityManager.merge(employee));

    }
}
