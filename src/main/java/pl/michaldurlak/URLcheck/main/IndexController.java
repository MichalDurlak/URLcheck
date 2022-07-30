package pl.michaldurlak.URLcheck.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michaldurlak.URLcheck.exerra.ExerraModel;
import pl.michaldurlak.URLcheck.exerra.ExerraService;
import pl.michaldurlak.URLcheck.ipqualityscore.IpqualityscoreModel;
import pl.michaldurlak.URLcheck.phisherman.PhishermanModel;
import pl.michaldurlak.URLcheck.virustotal.VirustotalModel;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }


    @PostMapping("/")
    public String getResultPage(@ModelAttribute("URLRequest") UrlModel urlModel,
                                ExerraModel exerraModel,
                                VirustotalModel virustotalModel,
                                IpqualityscoreModel ipqualityscoreModel,
                                PhishermanModel phishermanModel,
                                Model model){

        model.addAttribute("urlModel",urlModel);
        model.addAttribute("exerraModel",exerraModel);
        model.addAttribute("virustotalModel",virustotalModel);
        model.addAttribute("ipqualityscoreModel",ipqualityscoreModel);
        model.addAttribute("phishermanModel",phishermanModel);

        // Set up everything using MainService
        MainService.setUpEachSource(urlModel, exerraModel, virustotalModel, ipqualityscoreModel, phishermanModel);

        return "result";
    }


}
