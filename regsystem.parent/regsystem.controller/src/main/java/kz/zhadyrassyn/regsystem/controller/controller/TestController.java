package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.ToJson;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

@Bean
@Mapping("/intro")
public class TestController implements Controller{
    @ToJson
    @Mapping("/index")
    public String intro() {
        return "hello, world";
    }
}
