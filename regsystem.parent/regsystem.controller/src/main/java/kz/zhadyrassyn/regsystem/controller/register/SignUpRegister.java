package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.SignUpInfo;

public interface SignUpRegister {
    SignUpInfo signUp(String username, String password);
}
