package o.horbenko.hibernate.dal;

import o.horbenko.exception.PersistanceException;
import o.horbenko.hibernate.dao.TestDAO;
import o.horbenko.hibernate.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("testDAL")
public class TestDALService {

    private final TestDAO testDAO;

    @Autowired
    public TestDALService(TestDAO testDAO) {
        this.testDAO = testDAO;
    }


    @Transactional(rollbackFor = Exception.class)
    public List<TestEntity> getAll() throws PersistanceException {
        return testDAO.getAll();
    }

}
