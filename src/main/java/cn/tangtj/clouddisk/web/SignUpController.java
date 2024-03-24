package cn.tangtj.clouddisk.web;

import cn.tangtj.clouddisk.entity.User;
import cn.tangtj.clouddisk.entity.vo.SignUpNameCheckResult;
import cn.tangtj.clouddisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/signUp")
class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String singUp(){
        return "signUp";
    }

    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String checkUserName(String userName){
        SignUpNameCheckResult result = new SignUpNameCheckResult();

        if (check(userName)) {
            User user = userService.findByName(userName);
            if (user == null) {
                result.setUsable(true);
                result.setMsg("用户名可用");
            } else {
                result.setUsable(false);
                result.setMsg("当前用户名不可用");
            }
        } else {
            result.setUsable(false);
            result.setMsg("用户名不符合规范");
        }
        return "{\"usable\":\"" + result.isUsable() +"\",\"msg\":\""+result.getMsg()+"\"}";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String regUser(String username,String password,String reuserpwd,Model model){
        if (!check(username)){
            model.addAttribute("errorNameMsg", "用户名不符合规范");
            return "signUp";
        }
        if (!check(password)){
            model.addAttribute("errorPwdMsg", "密码不符合规范");
            return "signUp";
        }
        if (!password.equals(reuserpwd)){
            model.addAttribute("signUpError", "两次密码不一致");
            return "signUp";
        }
        User user = userService.findByName(username);
        if (user == null){
            User insert = new User();
            insert.setUsername(username);
            insert.setPassword(password);
            User result =  userService.save(insert);
            if (result != null){
                model.addAttribute("signUpMsg","注册成功");
            }else{
                model.addAttribute("signUpError","注册失败,请重新注册");
            }
            return "signUp";
        }else {
            model.addAttribute("signUpError", "注册失败,用户名已被使用");
        }
        return "signUp";
    }

    public boolean check(String str){
        return str.matches("[0-9a-zA-Z]{5,10}");
    }
}
