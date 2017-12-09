package kz.zhadyrassyn.regsystem.controller.model.request;

public class SignInRequest {
    public String email;
    public String password;

    public SignInRequest() {}

    @Override
    public String toString() {
        return "SignInRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
