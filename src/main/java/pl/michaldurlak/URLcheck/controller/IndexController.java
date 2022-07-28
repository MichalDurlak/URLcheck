package pl.michaldurlak.URLcheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }


    @PostMapping("/")
    public String getResultPage(){

        return "result";
    }
}
