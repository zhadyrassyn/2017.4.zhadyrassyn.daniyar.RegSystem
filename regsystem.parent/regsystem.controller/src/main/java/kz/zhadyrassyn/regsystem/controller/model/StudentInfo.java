package kz.zhadyrassyn.regsystem.controller.model;

public class StudentInfo {
    public Long id;
    public String name;
    public String surname;
    public String patronymic;
    public GroupType group;

    public StudentInfo() {}

    public StudentInfo(Long id, String name, String surname, String patronymic, GroupType group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
    }
}
