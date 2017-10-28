package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.Par;
import kz.greetgo.mvc.annotations.ToJson;
import kz.zhadyrassyn.regsystem.controller.model.SignUpInfo;
import kz.zhadyrassyn.regsystem.controller.register.SignUpRegister;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

@Bean
public class SignUpController implements Controller{
    public BeanGetter<SignUpRegister> register;

    @ToJson
    @Mapping("/signup")
    public SignUpInfo signUp(@Par("username") String username,
                             @Par("password") String password) {
        return register.get().signUp(username, password);
    }
}
