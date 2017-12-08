package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.AuthInfo;
import kz.zhadyrassyn.regsystem.controller.model.response.SignUpResponse;

public interface AuthRegister {
    public AuthInfo signIn(String email, String password);

    public SignUpResponse signUp(String request);

    public AuthInfo activate(String activationToken);
}
