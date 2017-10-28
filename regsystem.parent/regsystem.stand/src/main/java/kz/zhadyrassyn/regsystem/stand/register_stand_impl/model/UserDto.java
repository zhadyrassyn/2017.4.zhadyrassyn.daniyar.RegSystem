package kz.zhadyrassyn.regsystem.stand.register_stand_impl.model;

public class UserDto {
    public Long id;
    public String login;
    public String password;
    public boolean disabled;

    public UserDto() {}

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
