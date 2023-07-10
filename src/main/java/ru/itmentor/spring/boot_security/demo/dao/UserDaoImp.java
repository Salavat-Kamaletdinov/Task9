package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public void updateUser(User user) {
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
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        List<Role> roleList = query.getResultList();
        return new HashSet<>(roleList);
    }

    @Override
    public Set<Role> findRolesById(Set<Long> roleIds) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id IN :roleIds", Role.class);
        query.setParameter("roleIds", roleIds);
        List<Role> roleList = query.getResultList();
        return new HashSet<>(roleList);
    }

//    @Override
//    public Set<Role> findRolesById(Set<Long> roleIds) {
//        List<Role> roleList = entityManager.createQuery("FROM Role r WHERE r.id IN :roleIds", Role.class).getResultList();
//        return new HashSet<>(roleList);
//    }

    @Override
    @Transactional
    public void save(User user, Set<Long> roleIds) {
        user.setRoles(findRolesById(roleIds));
        entityManager.persist(user);
    }


//    @Override
//    public List<Role> findRolesById(List<Long> id) {
//        List<Role> role =
//        return role;
//    }

}
