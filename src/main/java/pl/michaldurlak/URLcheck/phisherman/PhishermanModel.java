package pl.michaldurlak.URLcheck.phisherman;

import lombok.Getter;
import lombok.Setter;
import pl.michaldurlak.URLcheck.main.UrlModel;

@Getter
@Setter
public class PhishermanModel {

    // Full JSON Result
    private String fullResultCheckFromPhisherman;
    private String fullResultInfoFromPhisherman;

    //classification
    private String classificationCheckFromPhisherman;
    private String classificationInfoFromPhisherman;

    //verifiedPhish
    private String verifiedPhishFromPhisherman;
    //targetedBrand
    private String targetedBrandFromPhisherman;
    //phishCaught
    private String phishCaughtFromPhisherman;

    // details ->
    //websiteScreenshot
    private String websiteScreenshotFromPhisherman;
    //ip_address
    private String ipaddressFromPhisherman;

    // details -> country ->
    //code
    private String countryCodeFromPhisherman;
    //name
    private String countryNameFromPhisherman;
}
