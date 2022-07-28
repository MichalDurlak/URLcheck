package pl.michaldurlak.URLcheck.virustotal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.michaldurlak.URLcheck.exerra.ExerraModel;
import pl.michaldurlak.URLcheck.main.UrlModel;

@Service
public class VirustotalService {

    // API KEY
    private static final String API_VIRUSTOTAL = System.getenv("API_VIRUSTOTAL");

    // STATIC URL
    private static final String URL_virustotal = "https://www.virustotal.com/vtapi/v2/url/report";

    private static RestTemplate restTemplate = new RestTemplate();

    public static void setResultVirustotal(UrlModel urlModel, VirustotalModel virustotalModel) {
        virustotalModel.setFullResultFromVirustotal(restTemplate.getForObject(URL_virustotal+"?apikey={API_VIRUSTOTAL}&resource={urlProvided}&allinfo=false&scan=0",
                String.class , API_VIRUSTOTAL, urlModel.getFullUrlLink()));
    }

    public static void setResponseCode(VirustotalModel virustotalModel) {
        JSONObject jsonObject = new JSONObject(virustotalModel.getFullResultFromVirustotal());
        virustotalModel.setResponseCodeFromVirustotal(jsonObject.getInt("response_code"));
    }

    public static void setModelVirustotal(VirustotalModel virustotalModel){
        JSONObject jsonObject = new JSONObject(virustotalModel.getFullResultFromVirustotal());
        //permalink
        virustotalModel.setPermalinkFromVirustotal(jsonObject.getString("permalink"));
        //positives
        virustotalModel.setPositivesFromVirustotal(jsonObject.getInt("positives"));
        //total
        virustotalModel.setTotalFromVirustotal(jsonObject.getInt("total"));
        //scan_date
        virustotalModel.setScanDateFromVirustotal(jsonObject.getString("scan_date"));
    }

    public static void setPointsVirustotal(UrlModel urlModel, VirustotalModel virustotalModel){
        int finalScore = 100-((100*virustotalModel.getPositivesFromVirustotal())/virustotalModel.getTotalFromVirustotal());
        urlModel.setPointsVirustotal(String.valueOf(finalScore));

    }

    public static void setDescriptionVirustotal(UrlModel urlModel){
        int pointsVirustotal = Integer.parseInt(urlModel.getPointsVirustotal());

        if(pointsVirustotal > 90 && pointsVirustotal <= 99){
            urlModel.setDescriptionVirustotal("WYSOKIE ZAGROŻENIE");
        } else if(pointsVirustotal == 100){
            urlModel.setDescriptionVirustotal("BEZPIECZNA");
        }else {
            urlModel.setDescriptionVirustotal("NIEBEZPIECZNA!!");
        }
    }

    // if no value
    public static void setModelVirustotalNoResponse(VirustotalModel virustotalModel) {
        //permalink
        virustotalModel.setPermalinkFromVirustotal("Brak danych");
        //positives
        virustotalModel.setPositivesFromVirustotal(0);
        //total
        virustotalModel.setTotalFromVirustotal(0);
        //scan_date
        virustotalModel.setScanDateFromVirustotal("Brak danych");
    }
    public static void setPointsVirustotalAndDescriptionNoResponse(UrlModel urlModel){
        urlModel.setPointsVirustotal("Brak danych");
        urlModel.setDescriptionVirustotal("Brak danych");

    }

}
