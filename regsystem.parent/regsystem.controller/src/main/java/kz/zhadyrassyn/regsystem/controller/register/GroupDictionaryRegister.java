package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.GroupInfo;

import java.util.List;

public interface GroupDictionaryRegister {
    List<GroupInfo> fetchGroups(int course, String faculty);
}
