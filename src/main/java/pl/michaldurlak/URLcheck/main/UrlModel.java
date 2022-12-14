package pl.michaldurlak.URLcheck.main;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlModel {

    // link provided by user
    private String urlLinkProvidedByUser;
    // prefix from link is it https or http
    private String prefixUrl;
    // full link (with prefix)
    private String fullUrlLink;

    //Exerra
    private int pointsExerra;
    private String descriptionExerra;

    //Virustotal
    private String pointsVirustotal;
    private String descriptionVirustotal;

    //Ipqualityscore
    private String pointsIpqualityscore;
    private String descriptionIpqualityscore;

    //Phisherman
    private String pointsPhisherman;
    private String descriptionPhisherman;


    // get Link with prefix
    public String getFullUrlLink() {
        return prefixUrl+urlLinkProvidedByUser;
    }

}
