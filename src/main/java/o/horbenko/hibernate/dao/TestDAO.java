package o.horbenko.hibernate.dao;

import o.horbenko.exception.PersistenceException;
import o.horbenko.hibernate.entity.TestEntity;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@Repository("postgresDadDAOImpl")
public class TestDAO {

    private final SessionFactory sessionFactory;


    @Autowired
    public TestDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<TestEntity> getAll() throws PersistenceException {
        try {

            return sessionFactory.getCurrentSession().createCriteria(TestEntity.class).list();

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new PersistenceException("Exception in db layer", e);
        }
    }






}
