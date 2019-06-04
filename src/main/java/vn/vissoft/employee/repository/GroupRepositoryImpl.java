package vn.vissoft.employee.repository;

import org.springframework.stereotype.Repository;
import vn.vissoft.employee.model.Employee;
import vn.vissoft.employee.model.GroupEmp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<GroupEmp> findAll() {

        Query query = entityManager.createQuery("select g FROM GroupEmp g");
        return query.getResultList();

    }

    @Override
    public GroupEmp findById(Long id) {
        Query query = entityManager.createQuery("select g FROM GroupEmp g where g.id=:id")
                .setParameter("id", id);
        return (GroupEmp) query.getSingleResult();
    }
}
