package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

import kz.zhadyrassyn.regsystem.controller.model.GroupType;

public class StudentDto {
    public Long id;
    public String name;
    public String surname;
    public String patronymic;
    public GroupType group;

    public StudentDto() {}

    public StudentDto(Long id, String name, String surname, String patronymic, GroupType group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
    }
}
