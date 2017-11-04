package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.zhadyrassyn.regsystem.controller.model.AuthInfo;
import kz.zhadyrassyn.regsystem.controller.register.AuthRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.helpers.SendMailHelper;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.RoleDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;

import java.util.Map;
import java.util.UUID;

@Bean
public class AuthRegisterStandImpl implements AuthRegister{
    public BeanGetter<Db> db;

    @Override
    public AuthInfo signIn(String email, String password) {
        AuthInfo output = new AuthInfo();

        boolean existsInDb = checkForExistence(email, password);
        if(!existsInDb) {
            output.message = "Unknown user";
            return output;
        }

        //retrieve user
        UserDto user = getUser(email, password);

        if(!user.active) {
            output.message = "Unknown user";
            return output;
        }


        output.message = "Welcome! Your role is : " + getUserRole(user.id);

        return output;
    }

    @Override
    public AuthInfo signUp(String email, String password) {
        Map map = db.get().users;
        AuthInfo output = new AuthInfo();

        boolean existsInDb = checkForExistence(email, password);
        if(existsInDb) {
            output.message = "User with this email and password already exists";
            return output;
        }

        //saveAndRetrieve
        UserDto savedUser = saveAndRetrieve(email, password);

        //sendEmail
        sendEmail(savedUser);

        output.message = "Please, confirm your registration with the link we have send to your email.";
        return output;
    }

    @Override
    public AuthInfo activate(String activationToken) {
        AuthInfo output = new AuthInfo();

        Map<String, UserDto> tokens = db.get().activationTokenToUserMapping;
        if(!tokens.containsKey(activationToken)) {
            output.message = "Unknown link";
            return output;
        }

        UserDto user = tokens.get(activationToken);
        user.active = true;

//        for(String key: db.get().activationTokenToUserMapping.keySet()) {
//            System.out.println(db.get().activationTokenToUserMapping.get(key));
//        }
        output.message = "Confirmation successfully done!";
        return output;
    }

    private void sendEmail(UserDto savedUser) {
        SendMailHelper sendMailHelper = new SendMailHelper(savedUser.email,
                savedUser.activationToken);
        sendMailHelper.send();
    }

    private boolean checkForExistence(String email, String password) {
        System.out.println("CHECKING FOR EXISTENCE: " + email + ", " + password);
        boolean flag = false;
        Map<Long, UserDto> users = db.get().users;

        for (Long key: users.keySet()) {
            UserDto user = users.get(key);

            if(email.equals(user.email) &&
                    password.equals(user.password)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private UserDto getUser(String email, String password) {
        UserDto userToReturn = new UserDto();
        Map<Long, UserDto> users = db.get().users;

        for (Long key: users.keySet()) {
            UserDto user = users.get(key);

            if(email.equals(user.email) &&
                    password.equals(user.password)) {
                userToReturn = user;
            }
        }
        return userToReturn;
    }

    private UserDto saveAndRetrieve(String email, String password) {
        UserDto user = new UserDto(email, password);
        user.active = false;
        user.id = db.get().counter.incrementAndGet();
        db.get().users.put(user.id, user);

        //get student role
        long studentRoleId = getStudentRoleId();

        //saveAndRetrieve
        db.get().userToRoleMapping.put(user.id, studentRoleId);

        //generateTokenForHim
        String activationToken = UUID.randomUUID().toString();
        user.activationToken = activationToken;
        db.get().activationTokenToUserMapping.put(activationToken, user);

        return user;
    }

    private Long getStudentRoleId() {
        Map<Long, RoleDto> roles = db.get().roles;
        long studentRoleId = 0;
        for(Long key: roles.keySet()) {
            if(roles.get(key).title.equals("student")) {
                studentRoleId = key;
            }
        }
        return studentRoleId;
    }

    private String getUserRole(Long userId) {
//        print();

        Long userRoleId = db.get().userToRoleMapping.get(userId);
        String userRole = db.get().roles.get(userRoleId).title;

//        System.out.println(userId + ", " + userRoleId);
        return userRole;
    }

    private void print() {
        System.out.println("USERS:");
        for (Long key: db.get().users.keySet()) {
            System.out.println(db.get().users.get(key));
        }

        System.out.println("ROLES:");
        for(Long key: db.get().roles.keySet()) {
            System.out.println(db.get().roles.get(key));
        }

        System.out.println("USER_ROLE");
        for (Long key: db.get().userToRoleMapping.keySet()) {
            System.out.println(key + " " + db.get().userToRoleMapping.get(key));
        }
    }
}