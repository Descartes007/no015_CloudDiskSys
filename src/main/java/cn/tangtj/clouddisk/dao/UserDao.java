package cn.tangtj.clouddisk.dao;

import cn.tangtj.clouddisk.entity.User;
import cn.tangtj.clouddisk.utils.JdbcUtil;
import cn.tangtj.clouddisk.web.LoginController;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author tang
 */
@Repository
public class UserDao {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginController.class.getName());

    public User findByName(String name) {
        String sql = "select * from user where userName = ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanHandler<>(User.class), name);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User findById(int id) {
        String sql = "select * from user where id = ?";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.query(sql, new BeanHandler<>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User save(User user){
        String sqlUser = "insert into user(username,password,filemaxsize,filemaxcount) value(?,?,?,?)";
        QueryRunner runner = JdbcUtil.getQueryRunner();
        try {
            return runner.insert(sqlUser, new BeanHandler<>(User.class),user.getUsername(),user.getPassword(),user.getFileMaxSize(),user.getFileMaxCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
