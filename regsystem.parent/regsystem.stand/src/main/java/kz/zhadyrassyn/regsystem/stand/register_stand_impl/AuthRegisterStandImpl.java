package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.zhadyrassyn.regsystem.controller.model.AuthInfo;
import kz.zhadyrassyn.regsystem.controller.model.enums.RoleTitleEnum;
import kz.zhadyrassyn.regsystem.controller.model.enums.UserStatusEnum;
import kz.zhadyrassyn.regsystem.controller.model.request.SignInRequest;
import kz.zhadyrassyn.regsystem.controller.model.request.SignUpRequest;
import kz.zhadyrassyn.regsystem.controller.model.response.SignInResponse;
import kz.zhadyrassyn.regsystem.controller.model.response.SignUpResponse;
import kz.zhadyrassyn.regsystem.controller.register.AuthRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.RoleDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MyConfig;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.util.JwtUtil;

import java.util.*;

@Bean
public class AuthRegisterStandImpl implements AuthRegister{
    public BeanGetter<Db> db;

    public BeanGetter<EmailSender> emailSenderBeanGetter;

    public BeanGetter<MyConfig> config;

    public BeanGetter<JwtUtil> jwtUtil;

    @Override
    public SignUpResponse signUp(String request) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        SignUpRequest requestInfo = gson.fromJson(request, SignUpRequest.class);
        System.out.println("param: " + requestInfo);

        SignUpResponse signUpResponse = new SignUpResponse();

        if(exists(requestInfo, db.get().users)) {
            signUpResponse.message = "Пользователь с таким ID уже существует";
            signUpResponse.status = "-1";
        } else {
            saveUser(requestInfo);

            signUpResponse.message = "Ваша заявка принята и отравлена на обработку модератором." +
                    "Ждите ответа в письме";
            signUpResponse.status = "1";
        }
        return signUpResponse;
    }

    @Override
    public AuthInfo activate(String activationToken) {
        return null;
    }

    private void saveUser(SignUpRequest requestInfo) {
        long nextId = db.get().userCounter.incrementAndGet();
        UserDto userDto = new UserDto(nextId, requestInfo.studentId,
                requestInfo.name, requestInfo.surname, requestInfo.patronymic,
                requestInfo.email, requestInfo.password, requestInfo.phone,
                requestInfo.gender, requestInfo.birthDate);

        db.get().users.put(userDto.id, userDto); //saving
        db.get().userToRoleMapping.put(userDto.id, (long)1); //provide role
        db.get().userToGroupMapping.put(userDto.id, requestInfo.groupId); //map to group
    }

    private boolean exists(SignUpRequest user, Map<Long, UserDto> users) {
        List<UserDto> userDtos = new ArrayList<>(users.values());
        return userDtos.stream()
                .anyMatch(t -> Objects.equals(user.studentId,
                        t.studentId));
    }


    @Override
    public SignInResponse signIn(String request) {
        SignInResponse response = new SignInResponse();

        Gson gson = new Gson();
        SignInRequest requestInfo = gson.fromJson(request, SignInRequest.class);
        System.out.println("Sign in request: " + requestInfo);

        //check if exists by email and password
        List<UserDto> userDtos = new ArrayList<>(db.get().users.values());
        boolean existsAndActive = userDtos.stream()
                .anyMatch(t ->
                                Objects.equals(requestInfo.email, t.email) &&
                                        Objects.equals(requestInfo.password, t.password) &&
                                        t.status == UserStatusEnum.ACTIVE);


        if(existsAndActive) {
            //get user id

            String token = jwtUtil.get().generateToken(requestInfo.email);
            response.status = "1";
            response.token = token;
            response.roleTitle = RoleTitleEnum.MODERATOR;
        } else {
            response.status = "-1";
        }

        return response;
    }

    @SuppressWarnings("unused")
    private RoleTitleEnum getUserRole(long id) {
        Map<Long, Long> userToRoleMap = db.get().userToRoleMapping;
        long roleId = userToRoleMap.get(id);
        return db.get().roles.get(roleId).title;
    }

//        AuthInfo output = new AuthInfo();
//
//        boolean existsInDb = checkForExistence(email, password);
//        if(!existsInDb) {
//            output.message = "Unknown user";
//            return output;
//        }
//
//        //retrieve user
//        UserDto user = getUser(email, password);
//
////        if(!user.active) {
////            output.message = "Unknown user";
////            return output;
////        }
//
//
//        output.message = "Welcome! Your role is : " + getUserRole(user.id);
//
//        return output;
//        throw new RuntimeException();
//    }

//    @Override
//    public AuthInfo signUp(String email, String password) {
//        Map map = db.get().users;
//        AuthInfo output = new AuthInfo();
//
//        boolean existsInDb = checkForExistence(email, password);
//        if(existsInDb) {
//            output.message = "User with this email and password already exists";
//            return output;
//        }
//
//        //saveAndRetrieve
//        UserDto savedUser = saveAndRetrieve(email, password);
//
//        //sendEmail
//        sendEmail(savedUser);
//
//        output.message = "Please, confirm your registration with the link we have send to your email.";
//        return output;
//    }
//
//    @Override
//    public AuthInfo activate(String activationToken) {
//        AuthInfo output = new AuthInfo();
//
//        Map<String, UserDto> tokens = db.get().activationTokenToUserMapping;
//        if(!tokens.containsKey(activationToken)) {
//            output.message = "Unknown link";
//            return output;
//        }
//
//        UserDto user = tokens.get(activationToken);
////        user.active = true;
//
////        for(String key: db.get().activationTokenToUserMapping.keySet()) {
////            System.out.println(db.get().activationTokenToUserMapping.get(key));
////        }
//        output.message = "Confirmation successfully done!";
//        return output;
//    }
//
//    private void sendEmail(UserDto savedUser) {
////
////        String body = "Dear user. We are glad to welcome you!\n\n"
////                + "Please, follow to link below to confirm your registration\n\n"
////                + "http://localhost:1234/regsystem/api/sign/up/" + savedUser.activationToken;
//
//        Email emailSend = new Email();
//        emailSend.setFrom(config.get().loginAccount());
//        emailSend.setTo(savedUser.email);
//        emailSend.setSubject("Registration validation");
////        emailSend.setBody(body);
//
//        emailSenderBeanGetter.get().send(emailSend);
//    }
//
//    private boolean checkForExistence(String email, String password) {
//        System.out.println("CHECKING FOR EXISTENCE: " + email + ", " + password);
//        boolean flag = false;
//        Map<Long, UserDto> users = db.get().users;
//
//        for (Long key: users.keySet()) {
//            UserDto user = users.get(key);
//
//            if(email.equals(user.email) &&
//                    password.equals(user.password)) {
//                flag = true;
//                break;
//            }
//        }
//        return flag;
//    }
//
//    private UserDto getUser(String email, String password) {
//        UserDto userToReturn = new UserDto();
//        Map<Long, UserDto> users = db.get().users;
//
//        for (Long key: users.keySet()) {
//            UserDto user = users.get(key);
//
//            if(email.equals(user.email) &&
//                    password.equals(user.password)) {
//                userToReturn = user;
//            }
//        }
//        return userToReturn;
//    }
//
//    private UserDto saveAndRetrieve(String email, String password) {
////        UserDto user = new UserDto(email, password);
////        user.active = false;
////        user.id = db.get().counter.incrementAndGet();
////        db.get().users.put(user.id, user);
////
////        //get student role
////        long studentRoleId = getStudentRoleId();
////
////        //saveAndRetrieve
////        db.get().userToRoleMapping.put(user.id, studentRoleId);
////
////        //generateTokenForHim
////        String activationToken = UUID.randomUUID().toString();
////        user.activationToken = activationToken;
////        db.get().activationTokenToUserMapping.put(activationToken, user);
//
//        return null;
//    }
//
//    private Long getStudentRoleId() {
//        Map<Long, RoleDto> roles = db.get().roles;
//        long studentRoleId = 0;
//        for(Long key: roles.keySet()) {
//            if(roles.get(key).title.equals("student")) {
//                studentRoleId = key;
//            }
//        }
//        return studentRoleId;
//    }
//
//    private String getUserRole(Long userId) {
////        print();
//
//        Long userRoleId = db.get().userToRoleMapping.get(userId);
////        String userRole = db.get().roles.get(userRoleId).title;
//
////        System.out.println(userId + ", " + userRoleId);
////        return userRole;
//        return null;
//    }
//
//    private void print() {
//        System.out.println("USERS:");
//        for (Long key: db.get().users.keySet()) {
//            System.out.println(db.get().users.get(key));
//        }
//
//        System.out.println("ROLES:");
//        for(Long key: db.get().roles.keySet()) {
//            System.out.println(db.get().roles.get(key));
//        }
//
//        System.out.println("USER_ROLE");
//        for (Long key: db.get().userToRoleMapping.keySet()) {
//            System.out.println(key + " " + db.get().userToRoleMapping.get(key));
//        }
//    }
}