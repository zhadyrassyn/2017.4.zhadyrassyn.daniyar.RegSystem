package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.Par;
import kz.greetgo.mvc.annotations.ToJson;
import kz.zhadyrassyn.regsystem.controller.model.GroupInfo;
import kz.zhadyrassyn.regsystem.controller.register.GroupDictionaryRegister;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

import java.util.List;

@Bean
public class GroupDictionaryController implements Controller{

    public BeanGetter<GroupDictionaryRegister> groupDictionaryRegister;

    @ToJson
    @Mapping("/groups")
    public List<GroupInfo> fetchGroups(@Par("course")int course,
                                       @Par("faculty")String faculty) {
        System.out.println(course + ", " + faculty);
        return groupDictionaryRegister.get().fetchGroups(course, faculty);
    }
}
