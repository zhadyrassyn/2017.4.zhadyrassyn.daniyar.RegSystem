package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

public class UserDto {
    public Long id;
    public String email;
    public String password;
    public boolean active;
    public String activationToken = "xxx";

    public UserDto() {}

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", activationToken='" + activationToken + '\'' +
                '}';
    }
}
