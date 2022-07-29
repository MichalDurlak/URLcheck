package pl.michaldurlak.URLcheck.ipqualityscore;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.michaldurlak.URLcheck.main.UrlModel;
import pl.michaldurlak.URLcheck.virustotal.VirustotalModel;

@Service
public class IpqualityscoreService {

    // API KEY
    private static final String API_IPQUALITYSCORE = System.getenv("API_IPQUALITYSCORE");
    // STATIC URL
    private static final String URL_IPQUALITYSCORE = "https://ipqualityscore.com/api/json/url";

    private static RestTemplate restTemplate = new RestTemplate();

    public static void setResultIpqualityscore(UrlModel urlModel, IpqualityscoreModel ipqualityscoreModel) {
        ipqualityscoreModel.setFullResultFromIpqualityscore(restTemplate.getForObject(URL_IPQUALITYSCORE+"/{API_ipqualityscoreservice}/{urlProvided}",
                String.class, API_IPQUALITYSCORE,urlModel.getUrlLinkProvidedByUser()));
    }

    public static void setSuccessIpqualityscore(IpqualityscoreModel ipqualityscoreModel) {
        JSONObject jsonObject = new JSONObject(ipqualityscoreModel.getFullResultFromIpqualityscore());
        ipqualityscoreModel.setSuccessFromVirustotal(jsonObject.getBoolean("success"));
    }


    public static void setModelIpqualityscore(IpqualityscoreModel ipqualityscoreModel) {
        JSONObject jsonObject = new JSONObject(ipqualityscoreModel.getFullResultFromIpqualityscore());
        ipqualityscoreModel.setUnsafeFromVirustotal(String.valueOf(jsonObject.getBoolean("unsafe")));
        ipqualityscoreModel.setIpAddressFromVirustotal(jsonObject.getString("ip_address"));
        ipqualityscoreModel.setDomainRankFromVirustotal(jsonObject.getInt("domain_rank"));
        ipqualityscoreModel.setParkingFromVirustotal(String.valueOf(jsonObject.getBoolean("parking")));
        ipqualityscoreModel.setSpammingFromVirustotal(String.valueOf(jsonObject.getBoolean("spamming")));
        ipqualityscoreModel.setMalwareFromVirustotal(String.valueOf(jsonObject.getBoolean("malware")));
        ipqualityscoreModel.setPhishingFromVirustotal(String.valueOf(jsonObject.getBoolean("phishing")));
        ipqualityscoreModel.setSuspiciousFromVirustotal(String.valueOf(jsonObject.getBoolean("suspicious")));
        ipqualityscoreModel.setAdultFromVirustotal(String.valueOf(jsonObject.getBoolean("adult")));
        ipqualityscoreModel.setRiskScoreFromVirustotal(jsonObject.getInt("risk_score"));
        ipqualityscoreModel.setCategoryFromVirustotal(jsonObject.getString("category"));

    }

    public static void setModelIpqualityscoreNoResponse(IpqualityscoreModel ipqualityscoreModel) {
        ipqualityscoreModel.setUnsafeFromVirustotal("Brak danych");
        ipqualityscoreModel.setIpAddressFromVirustotal("Brak danych");
        ipqualityscoreModel.setDomainRankFromVirustotal(0);
        ipqualityscoreModel.setParkingFromVirustotal("Brak danych");
        ipqualityscoreModel.setSpammingFromVirustotal("Brak danych");
        ipqualityscoreModel.setMalwareFromVirustotal("Brak danych");
        ipqualityscoreModel.setPhishingFromVirustotal("Brak danych");
        ipqualityscoreModel.setSuspiciousFromVirustotal("Brak danych");
        ipqualityscoreModel.setAdultFromVirustotal("Brak danych");
        ipqualityscoreModel.setRiskScoreFromVirustotal(0);
        ipqualityscoreModel.setCategoryFromVirustotal("Brak danych");
    }

    public static void setPointsIpqualityscore(UrlModel urlModel, IpqualityscoreModel ipqualityscoreModel) {
        urlModel.setPointsIpqualityscore(String.valueOf(100-ipqualityscoreModel.getRiskScoreFromVirustotal()));
    }
    public static void setDescriptionIpqualityscore(UrlModel urlModel) {
        int ipqualityscoreScore = Integer.parseInt(urlModel.getPointsIpqualityscore());
        if(ipqualityscoreScore >= 75 && ipqualityscoreScore < 85){
            urlModel.setDescriptionIpqualityscore("WYSOKIE ZAGROŻENIE");
        } else if(ipqualityscoreScore >= 85 && ipqualityscoreScore < 100){
            urlModel.setDescriptionIpqualityscore("PODEJRZANA");
        } else if(ipqualityscoreScore == 100){
            urlModel.setDescriptionIpqualityscore("BEZPIECZNA");
        }else {
            urlModel.setDescriptionIpqualityscore("NIEBEZPIECZNA!!");
        }
    }

    public static void setPointsIpqualityscoreAndDescriptionNoResponse(UrlModel urlModel) {
        urlModel.setPointsIpqualityscore("Brak danych");
        urlModel.setDescriptionIpqualityscore("Brak danych");
    }
}
