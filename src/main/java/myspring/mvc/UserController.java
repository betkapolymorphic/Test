package myspring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Beta on 8/7/14.
 */
@Controller
public class UserController {
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("test","testos");
        return "index";
    }
}
