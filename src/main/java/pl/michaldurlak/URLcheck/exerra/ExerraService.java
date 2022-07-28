package pl.michaldurlak.URLcheck.exerra;

import kong.unirest.Unirest;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.michaldurlak.URLcheck.main.UrlModel;

@Service
public class ExerraService {

    // API KEY
    private static final String API_EXERRA = System.getenv("API_EXERRA");
    // STATIC URL
    private static final String URL_EXERRA = "https://exerra-phishing-check.p.rapidapi.com/?url=";


    public static void setResultExerra(UrlModel urlModel, ExerraModel exerraModel) {
        String response = Unirest.get(URL_EXERRA+urlModel.getFullUrlLink())
                .header("X-RapidAPI-Key", API_EXERRA)
                .header("X-RapidAPI-Host", "exerra-phishing-check.p.rapidapi.com")
                .asString()
                .getBody();

        exerraModel.setFullResultFromExerra(response);
    }

    public static void setModelExerra(ExerraModel exerraModel){
        JSONObject jsonObject = new JSONObject(exerraModel.getFullResultFromExerra());
        exerraModel.setStatusFromExerra(jsonObject.getInt("status"));
        exerraModel.setIsScamFromExerra(String.valueOf(jsonObject.getBoolean("isScam")));
    }

    public static void setPointsExerra(UrlModel urlModel, ExerraModel exerraModel){
        if(exerraModel.getIsScamFromExerra().equals("true")){
            urlModel.setPointsExerra(0);
        } else {
            urlModel.setPointsExerra(100);
        }
    }

    public static void setDescriptionExerra(UrlModel urlModel) {
        if(urlModel.getPointsExerra() == 100){
            urlModel.setDescriptionExerra("BEZPIECZNA");
        } else {
            urlModel.setDescriptionExerra("NIEBEZPIECZNA");
        }
    }
}
