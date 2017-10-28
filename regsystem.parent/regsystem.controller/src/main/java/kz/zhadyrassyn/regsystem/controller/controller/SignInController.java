package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.Par;
import kz.greetgo.mvc.annotations.ToJson;
import kz.zhadyrassyn.regsystem.controller.model.SignInInfo;
import kz.zhadyrassyn.regsystem.controller.register.SignInRegister;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

@Bean
public class SignInController implements Controller{

    public BeanGetter<SignInRegister> register;
    @ToJson
    @Mapping("/signIn")
    public SignInInfo signIn(@Par("username")String username, @Par("password")String password) {
        return register.get().getWelcomeMessage(username, password);
    }

}
