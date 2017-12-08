package kz.zhadyrassyn.regsystem.controller.model.request;

import kz.zhadyrassyn.regsystem.controller.model.enums.GenderEnum;

import java.util.Date;

public class SignUpRequest {
    public String studentId;
    public String name;
    public String surname;
    public String patronymic;
    public String email;
    public String password;
    public String phone;
    public int groupId;
    public GenderEnum gender;
    public Date birthDate;

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", groupId=" + groupId +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                '}';
    }
}
