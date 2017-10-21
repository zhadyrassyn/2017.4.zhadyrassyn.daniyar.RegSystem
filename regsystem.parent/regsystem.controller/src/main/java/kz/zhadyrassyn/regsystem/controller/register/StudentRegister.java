package kz.zhadyrassyn.regsystem.controller.register;

import kz.zhadyrassyn.regsystem.controller.model.GroupType;
import kz.zhadyrassyn.regsystem.controller.model.StudentInfo;

import java.util.List;

public interface StudentRegister {
    StudentInfo get(int id);

    List<StudentInfo> all();

    void save(int id, String name, String surname, String patronymic, GroupType group);

    void delete(int id);

}
