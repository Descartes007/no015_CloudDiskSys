package cn.tangtj.clouddisk.service;

import cn.tangtj.clouddisk.dao.UserDao;
import cn.tangtj.clouddisk.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tang
 */
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public User save(User user){
        return userDao.save(user);
    }
}
