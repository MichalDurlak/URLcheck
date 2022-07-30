package pl.michaldurlak.URLcheck.ipqualityscore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IpqualityscoreModel {

    // Full JSON Result
    private String fullResultFromIpqualityscore;

    // success
    private boolean successFromVirustotal;

    // unsafe
    private String unsafeFromVirustotal;
    //ip_address
    private String ipAddressFromVirustotal;
    //domain_rank
    private int domainRankFromVirustotal;
    //dns_valid
    private String dnsValidFromVirustotal;
    //parking
    private String parkingFromVirustotal;
    //spamming
    private String spammingFromVirustotal;
    //malware
    private String malwareFromVirustotal;
    //phishing
    private String phishingFromVirustotal;
    //suspicious
    private String suspiciousFromVirustotal;
    //adult
    private String adultFromVirustotal;
    //risk_score
    private int riskScoreFromVirustotal;
    //category
    private String categoryFromVirustotal;

}
