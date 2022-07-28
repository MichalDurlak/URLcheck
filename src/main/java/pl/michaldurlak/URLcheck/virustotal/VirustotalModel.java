package pl.michaldurlak.URLcheck.virustotal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VirustotalModel {

    // Full JSON Result
    private String fullResultFromVirustotal;

    // response_code
    private int responseCodeFromVirustotal;
    // permalink
    private String permalinkFromVirustotal;
    // positives
    private int positivesFromVirustotal;
    // total
    private int totalFromVirustotal;
    // scan date
    private String scanDateFromVirustotal;


}
