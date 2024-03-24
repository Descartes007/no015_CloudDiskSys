package cn.tangtj.clouddisk.security;

import cn.tangtj.clouddisk.entity.User;
import cn.tangtj.clouddisk.service.UserService;
import cn.tangtj.clouddisk.utils.UserUtil;
import cn.tangtj.clouddisk.web.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author tang
 */
@Component
public class SystemRealm extends AuthorizingRealm {

    private static Logger logger = LogManager.getLogger(LoginController.class.getName());

    private final UserService userService;

    @Autowired
    public SystemRealm(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        logger.info("用户 {},密码 {password} 尝试登录",username,password);
        User user = userService.findByName(username);
        if (user == null){
            throw new UnknownAccountException();
        }else {
            User now = UserUtil.getPrincipal();
            if (now != null){
                SecurityUtils.getSubject().logout();
            }
            if (user.getPassword().equals(password)){
                logger.info("用户 {} 登录成功",user.getUsername());
                return new SimpleAuthenticationInfo(user,password,getName());
            }
        }
        return null;
    }
}
