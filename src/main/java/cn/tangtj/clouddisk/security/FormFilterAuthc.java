package cn.tangtj.clouddisk.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FormFilterAuthc extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        String message="";
        if (UnknownAccountException.class.getName().equals(className)){
            message = "帐号或密码错误，请重试。";
        }else{
            message = "系统出现点问题，请稍后再试。";
        }
        request.setAttribute("message",message);
        return true;
    }
}
