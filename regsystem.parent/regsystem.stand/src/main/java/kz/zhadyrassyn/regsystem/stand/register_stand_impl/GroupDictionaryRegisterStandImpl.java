package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.zhadyrassyn.regsystem.controller.model.GroupInfo;
import kz.zhadyrassyn.regsystem.controller.register.GroupDictionaryRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.GroupDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Bean
public class GroupDictionaryRegisterStandImpl implements GroupDictionaryRegister{

    public BeanGetter<Db> db;

    @Override
    public List<GroupInfo> fetchGroups(int course, String faculty) {
        List<GroupInfo> result = new ArrayList<>();

        for(Long id: db.get().groups.keySet()) {
            GroupDto groupDto = db.get().groups.get(id);
            if(groupDto.course == course &&
                    Objects.equals(groupDto.faculty, faculty)) {
                GroupInfo info = new GroupInfo();
                info.id = id;
                info.name = groupDto.name;
                result.add(info);
            }
        }
        return result;
    }
}
