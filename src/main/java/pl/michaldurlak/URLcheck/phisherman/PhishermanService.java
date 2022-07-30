package pl.michaldurlak.URLcheck.phisherman;

import kong.unirest.Unirest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.michaldurlak.URLcheck.main.UrlModel;

@Service
public class PhishermanService {

    // API KEY
    private static final String API_PHISHERMAN = System.getenv("API_PHISHERMAN");

    // STATIC URL
    private static final String URL_PHISHERMANCheck = "https://api.phisherman.gg/v2/domains/check/";
    private static final String URL_PHISHERMANInfo = "https://api.phisherman.gg/v2/domains/info/";

    public static void setResultPhisherman(UrlModel urlModel, PhishermanModel phishermanModel) {

        String responseForCheck = Unirest.get(URL_PHISHERMANCheck+urlModel.getUrlLinkProvidedByUser())
                .header("Authorization", "Bearer "+API_PHISHERMAN)
                .asString()
                .getBody();

        String responseForInfo = Unirest.get(URL_PHISHERMANInfo+urlModel.getUrlLinkProvidedByUser())
                .header("Authorization", "Bearer "+API_PHISHERMAN)
                .asString()
                .getBody();

        phishermanModel.setFullResultCheckFromPhisherman(responseForCheck);
        phishermanModel.setFullResultInfoFromPhisherman(responseForInfo);

        // do usuniecia
        System.out.println(responseForInfo);
    }

    public static void setClassificationPhisherman(UrlModel urlModel,PhishermanModel phishermanModel) {

        JSONObject jsonObjectCheck = new JSONObject(phishermanModel.getFullResultCheckFromPhisherman());
        JSONObject jsonObjectInfo = new JSONObject(phishermanModel.getFullResultInfoFromPhisherman());

        JSONObject jsonObjectInfoUrl = jsonObjectInfo.getJSONObject(urlModel.getUrlLinkProvidedByUser());
        phishermanModel.setClassificationInfoFromPhisherman(jsonObjectInfoUrl.getString("classification"));

// to delete!!!!
        if(phishermanModel.getClassificationInfoFromPhisherman().equals("unknown")){
            System.out.println("unknown");

        } else {
            System.out.println(phishermanModel.getClassificationInfoFromPhisherman());
            System.out.println(phishermanModel.getFullResultInfoFromPhisherman());
        }

    }

    public static void setModelPhisherman(UrlModel urlModel, PhishermanModel phishermanModel) {

        try{
            JSONObject jsonObjectInfo = new JSONObject(phishermanModel.getFullResultInfoFromPhisherman());
            JSONObject jsonObjectInfoUrl = jsonObjectInfo.getJSONObject(urlModel.getUrlLinkProvidedByUser());

            phishermanModel.setVerifiedPhishFromPhisherman(String.valueOf(jsonObjectInfoUrl.getBoolean("verifiedPhish")));
            phishermanModel.setTargetedBrandFromPhisherman(jsonObjectInfoUrl.getString("targetedBrand"));
            phishermanModel.setPhishCaughtFromPhisherman(String.valueOf(jsonObjectInfoUrl.getInt("phishCaught")));

            // details ->
            JSONObject jsonObjectInfoDetails = jsonObjectInfoUrl.getJSONObject("details");
            phishermanModel.setWebsiteScreenshotFromPhisherman(jsonObjectInfoDetails.getString("websiteScreenshot"));
            phishermanModel.setIpaddressFromPhisherman(jsonObjectInfoDetails.getString("ip_address"));

            // details -> country ->
            JSONObject jsonObjectInfoDetailsCountry = jsonObjectInfoDetails.getJSONObject("country");
            phishermanModel.setCountryCodeFromPhisherman(jsonObjectInfoDetailsCountry.getString("code"));
            phishermanModel.setCountryNameFromPhisherman(jsonObjectInfoDetailsCountry.getString("name"));

        } catch (Exception e){

            phishermanModel.setVerifiedPhishFromPhisherman("Brak danych");
            phishermanModel.setTargetedBrandFromPhisherman("Brak danych");
            phishermanModel.setPhishCaughtFromPhisherman("Brak danych");
            phishermanModel.setWebsiteScreenshotFromPhisherman("Brak danych");
            phishermanModel.setIpaddressFromPhisherman("Brak danych");
            phishermanModel.setCountryCodeFromPhisherman("Brak danych");
            phishermanModel.setCountryNameFromPhisherman("Brak danych");
        }

    }


    public static void setPointsPhishermanAndDescriptionNoResponse(UrlModel urlModel) {
        urlModel.setPointsPhisherman("Brak danych");
        urlModel.setDescriptionPhisherman("Brak danych");
    }


    public static void setPointsPhisherman(UrlModel urlModel, PhishermanModel phishermanModel) {
        if(phishermanModel.getClassificationInfoFromPhisherman().toLowerCase().equals("suspicious")){
            urlModel.setPointsPhisherman(String.valueOf(80));
        } else if (phishermanModel.getClassificationInfoFromPhisherman().toLowerCase().equals("malicious")){
            urlModel.setPointsPhisherman(String.valueOf(0));
        } else {
            urlModel.setPointsPhisherman(String.valueOf(100));
        }
    }

    public static void setDescriptionPhisherman(UrlModel urlModel) {
        if(Integer.parseInt(urlModel.getPointsPhisherman()) == 80){
            urlModel.setDescriptionPhisherman("PODEJRZANA");
        } else if (Integer.parseInt(urlModel.getPointsPhisherman()) == 0){
            urlModel.setDescriptionPhisherman("NIEBEZPIECZNA");
        } else {
            urlModel.setDescriptionPhisherman("BEZPIECZNA");
        }
    }
}
