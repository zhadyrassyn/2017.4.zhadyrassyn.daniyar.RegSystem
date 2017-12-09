package kz.zhadyrassyn.regsystem.controller.model.response;

import kz.zhadyrassyn.regsystem.controller.model.enums.RoleTitleEnum;

public class SignInResponse {
    public String status;
    public String token;
    public RoleTitleEnum roleTitle;
}
