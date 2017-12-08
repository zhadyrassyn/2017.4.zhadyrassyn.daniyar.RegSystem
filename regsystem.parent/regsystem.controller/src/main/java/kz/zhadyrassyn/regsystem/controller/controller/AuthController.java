package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.mvc.annotations.*;
import kz.zhadyrassyn.regsystem.controller.model.AuthInfo;
import kz.zhadyrassyn.regsystem.controller.model.response.SignUpResponse;
import kz.zhadyrassyn.regsystem.controller.register.AuthRegister;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

@Bean
public class AuthController implements Controller {
    public BeanGetter<AuthRegister> authRegister;

    @ToJson
    @Mapping("/sign/in")
    public AuthInfo signIn(@Par("email")String email, @Par("password")String password) {
        return authRegister.get().signIn(email, password);
    }

    @ToJson
    @Mapping("/sign/up")
    public SignUpResponse signUp(@RequestInput String signUpRequest) {
        return authRegister.get().signUp(signUpRequest);
    }

    @ToJson
    @Mapping("/sign/up/{activationToken}")
    public AuthInfo activate(@ParPath("activationToken")String activationToken) {
        return authRegister.get().activate(activationToken);
    }
}