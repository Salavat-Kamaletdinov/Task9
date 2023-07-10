package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
//        user.setRoles(findRolesById(roleId));
        userDao.updateUser(user);

    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);

    }
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void saveUser(User user, Set<Long> roleIds) {
        userDao.save(user, roleIds);
    }

    @Override
    public User findByName(String username) {
        return userDao.findByUserName(username);
    }

    public Set<Role> findAllRoles(){
        return userDao.findAllRoles();
    }




}
