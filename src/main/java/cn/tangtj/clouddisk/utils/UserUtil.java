package cn.tangtj.clouddisk.utils;

import cn.tangtj.clouddisk.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author tang
 */
public class UserUtil {

    public static User getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user != null) {
            return user;
        }
        return null;
    }
}
