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

import javax.annotation.PostConstruct;
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
    @Autowired
    EntityManagerFactory entityManagerFactory;

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

        long id = userRepository.getNextUserId();
        user.setId(id);
        entityManager.persist(user);
        Role role = new Role();
        role.setName("Admin");
        user.setRoles(Arrays.asList(role));
        entityManager.detach(user);
        System.out.println(user);

        return user;
    }

    //@PostConstruct
    public void test() {
        long id = 1;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User u1 = entityManager.find(User.class, id);
        entityManager.detach(u1);
        User u2 = entityManager.find(User.class, id);

        System.out.println(u1.equals(u2));
      //  System.out.println(u1.equals(u2));
        User u11 = entityManager.merge(u1);
      //  System.out.println(u1.equals(u2));
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @PostConstruct
    public void testCreateChildren() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, 1L);
        user.setName("user name 1");
        Address add1 = new Address();
        add1.setAddress("address 1");
        Address add2 = new Address();
        add2.setAddress("address 2");

        user.addAddresses(user.getAddresses().get(0));

        user.addAddresses(add1, add2);
        entityManager.merge(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
