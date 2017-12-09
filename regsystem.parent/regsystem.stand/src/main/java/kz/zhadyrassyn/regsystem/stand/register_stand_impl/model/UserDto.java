package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

import kz.zhadyrassyn.regsystem.controller.model.enums.GenderEnum;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.enums.UserStatusEnum;

import java.util.Date;

public class UserDto {
    public Long id;
    public String studentId;
    public String name;
    public String surname;
    public String patronymic;
    public String email;
    public String password;
    public String phone;
    public GenderEnum gender;
    public Date birthDate;
    public UserStatusEnum status = UserStatusEnum.NOT_VALIDATED;

    public UserDto() {}

    public UserDto(Long id, String studentId, String name, String surname, String patronymic, String email, String password, String phone, GenderEnum gender, Date birthDate) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", status=" + status +
                '}';
    }
}
