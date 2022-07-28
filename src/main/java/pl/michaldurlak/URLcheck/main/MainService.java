package pl.michaldurlak.URLcheck.main;

import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.michaldurlak.URLcheck.exerra.ExerraModel;
import pl.michaldurlak.URLcheck.exerra.ExerraService;

@Service
public class MainService {

    public static void setUpEachSource(UrlModel urlModel, ExerraModel exerraModel){

        //Exerra
        setUpExerraSource(urlModel,exerraModel);


    }

    private static void setUpExerraSource(UrlModel urlModel, ExerraModel exerraModel){
        // Set downloaded result to variable
        ExerraService.setResultExerra(urlModel, exerraModel);
        //Set exerraModel
        ExerraService.setModelExerra(exerraModel);
        //Set pointsExerra
        ExerraService.setPointsExerra(urlModel, exerraModel);

    }


}
