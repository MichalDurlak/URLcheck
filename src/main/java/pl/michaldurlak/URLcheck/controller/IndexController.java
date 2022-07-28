package pl.michaldurlak.URLcheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michaldurlak.URLcheck.model.UrlModel;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }


    @PostMapping("/")
    public String getResultPage(@ModelAttribute("URLRequest") UrlModel urlModel, Model model){

        model.addAttribute("urlModel",urlModel);

        return "result";
    }
}
