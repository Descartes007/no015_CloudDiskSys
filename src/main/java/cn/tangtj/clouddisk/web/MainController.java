package cn.tangtj.clouddisk.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tang
 */
@Controller
@RequestMapping()
public class MainController {

    @RequestMapping()
    public String index(){
        return "redirect:/login";
    }
}
