package pl.michaldurlak.URLcheck.main;

import org.springframework.stereotype.Service;
import pl.michaldurlak.URLcheck.exerra.ExerraModel;
import pl.michaldurlak.URLcheck.exerra.ExerraService;
import pl.michaldurlak.URLcheck.ipqualityscore.IpqualityscoreModel;
import pl.michaldurlak.URLcheck.ipqualityscore.IpqualityscoreService;
import pl.michaldurlak.URLcheck.phisherman.PhishermanModel;
import pl.michaldurlak.URLcheck.phisherman.PhishermanService;
import pl.michaldurlak.URLcheck.virustotal.VirustotalModel;
import pl.michaldurlak.URLcheck.virustotal.VirustotalService;

@Service
public class MainService {

    public static void setUpEachSource(UrlModel urlModel, ExerraModel exerraModel, VirustotalModel virustotalModel, IpqualityscoreModel ipqualityscoreModel, PhishermanModel phishermanModel){

        //Exerra
        setUpExerraSource(urlModel,exerraModel);

        //Virustotal
        setUpVirustotalSource(urlModel, virustotalModel);

        //Ipqualityscore
        setUpIpqualityscoreSource(urlModel, ipqualityscoreModel);

        //Phisherman
        setUpPhishermanSource(urlModel, phishermanModel);
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

    private static void setUpIpqualityscoreSource(UrlModel urlModel, IpqualityscoreModel ipqualityscoreModel){
        // Set downloaded result to variable
        IpqualityscoreService.setResultIpqualityscore(urlModel, ipqualityscoreModel);
        // Set success
        IpqualityscoreService.setSuccessIpqualityscore(ipqualityscoreModel);

        // If success is true
        if(ipqualityscoreModel.isSuccessFromVirustotal() == true){
            IpqualityscoreService.setModelIpqualityscore(ipqualityscoreModel);
            IpqualityscoreService.setPointsIpqualityscore(urlModel, ipqualityscoreModel);
            IpqualityscoreService.setDescriptionIpqualityscore(urlModel);
        } else {
            IpqualityscoreService.setModelIpqualityscoreNoResponse(ipqualityscoreModel);
            IpqualityscoreService.setPointsIpqualityscoreAndDescriptionNoResponse(urlModel);
        }
    }

    private static void setUpPhishermanSource(UrlModel urlModel, PhishermanModel phishermanModel){
        // Set downloaded result to variable
        PhishermanService.setResultPhisherman(urlModel, phishermanModel);
        // Set classification
        PhishermanService.setClassificationPhisherman(urlModel, phishermanModel);
        // Try to set phishermanModel
        PhishermanService.setModelPhisherman(urlModel, phishermanModel);

        //If Classification is unknown
        if(phishermanModel.getClassificationInfoFromPhisherman().equals("unknown")){
            PhishermanService.setPointsPhishermanAndDescriptionNoResponse(urlModel);
        } else {
            PhishermanService.setPointsPhisherman(urlModel, phishermanModel);
            PhishermanService.setDescriptionPhisherman(urlModel);
        }

    }


}
