package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.SignInInfo;

/**
 * Created by daniyar on 28/10/17.
 */
public interface SignInRegister {
    SignInInfo getWelcomeMessage(String username, String password);
}
