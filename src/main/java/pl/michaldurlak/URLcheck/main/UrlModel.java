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



    // get Link with prefix
    public String getFullUrlLink() {
        return prefixUrl+urlLinkProvidedByUser;
    }

}
