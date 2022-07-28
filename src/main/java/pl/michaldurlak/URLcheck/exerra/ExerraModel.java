package pl.michaldurlak.URLcheck.exerra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerraModel {

    // Full JSON Result
    private String fullResultFromExerra;

    // Status
    private int statusFromExerra;

    // isScam
    private String isScamFromExerra;
}
