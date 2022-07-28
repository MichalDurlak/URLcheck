package pl.michaldurlak.URLcheck.main;

import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.michaldurlak.URLcheck.exerra.ExerraModel;
import pl.michaldurlak.URLcheck.exerra.ExerraService;
import pl.michaldurlak.URLcheck.virustotal.VirustotalModel;
import pl.michaldurlak.URLcheck.virustotal.VirustotalService;

@Service
public class MainService {

    public static void setUpEachSource(UrlModel urlModel, ExerraModel exerraModel, VirustotalModel virustotalModel){

        //Exerra
        setUpExerraSource(urlModel,exerraModel);

        //Virustotal
        setUpVirustotalSource(urlModel, virustotalModel);

    }

    private static void setUpExerraSource(UrlModel urlModel, ExerraModel exerraModel){
        // Set downloaded result to variable
        ExerraService.setResultExerra(urlModel, exerraModel);
        //Set exerraModel
        ExerraService.setModelExerra(exerraModel);
        //Set pointsExerra
        ExerraService.setPointsExerra(urlModel, exerraModel);
        //Set description
        ExerraService.setDescriptionExerra(urlModel);

    }

    private static void setUpVirustotalSource(UrlModel urlModel, VirustotalModel virustotalModel){
        // Set downloaded result to variable
        VirustotalService.setResultVirustotal(urlModel, virustotalModel);
        // Set response_code
        VirustotalService.setResponseCode(virustotalModel);

        // If response code not equal 0 set other things
        if(virustotalModel.getResponseCodeFromVirustotal() != 0){
            VirustotalService.setModelVirustotal(virustotalModel);
            VirustotalService.setPointsVirustotal(urlModel, virustotalModel);
            VirustotalService.setDescriptionVirustotal(urlModel);
        } else {
            VirustotalService.setModelVirustotalNoResponse(virustotalModel);
            VirustotalService.setPointsVirustotalAndDescriptionNoResponse(urlModel);
        }
    }


}
