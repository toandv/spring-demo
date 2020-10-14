package com.example.demo;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional()
public class UserService implements Service1 {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TransactionInterceptor transactionInterceptor;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

//    @Autowired
//    ApplicationContext context;
//
//    @Autowired
//    JpaTransactionManager transactionManager;
//
//    @Autowired
//    EntityManagerFactory entityManagerFactory;

    TestService testService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {
        // return userRepository.save(user);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(user);
//        entityManager.getTransaction().commit();
//        List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();
//        System.out.println(users);
//        User u = (User) entityManager.createNamedQuery("findUserById1").setParameter("id", 1L).getSingleResult();
//        System.out.println(u);
//        return user;

        entityManager.persist(user);
        Role role = new Role();
        role.setName("Admin");
        user.setRoles(Arrays.asList(role));

        jdbcTemplate.query("select * from users", rs -> {

        });

        test();
        return user;
    }

    public void test() {
        System.out.println("test");
    }


    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
