package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.AuthInfo;

public interface AuthRegister {
    public AuthInfo signIn(String email, String password);

    public AuthInfo signUp(String email, String password);

    public AuthInfo activate(String activationToken);
}
