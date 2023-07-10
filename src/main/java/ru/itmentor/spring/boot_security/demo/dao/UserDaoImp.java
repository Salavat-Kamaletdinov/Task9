package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = entityManager.createQuery("from User", User.class).getResultList();
        return userList;
    }

    @Override
    @Transactional
    public void save(User user) {
//        user.setRoles(findRolesById(roleIds));
        entityManager.persist(user);
    }
    @Override
    @Transactional
    public void updateUser(User user) {
//        user.getId();
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

    }


    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }



    @Override
    public User findByUserName(String username) {
        return entityManager.createQuery("FROM User WHERE username =:username", User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public Set<Role> findAllRoles() {
        return null;
    }

    @Override
    public Set<Role> findRolesById(Set<Long> roleIds) {
        return null;
    }
}
