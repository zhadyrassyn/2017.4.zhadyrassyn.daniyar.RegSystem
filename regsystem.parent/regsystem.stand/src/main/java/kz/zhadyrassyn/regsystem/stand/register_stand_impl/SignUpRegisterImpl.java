package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.zhadyrassyn.regsystem.controller.model.SignUpInfo;
import kz.zhadyrassyn.regsystem.controller.register.SignUpRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;

import java.util.Map;

@Bean
public class SignUpRegisterImpl implements SignUpRegister{

    public BeanGetter<Db> db;

    @Override
    public SignUpInfo signUp(String username, String password) {
        Map<Long, UserDto> users = db.get().users;
        UserDto userA = new UserDto(username, password);
        boolean exists = exists(userA, users);
        SignUpInfo response = new SignUpInfo();

        if(exists) {
            response.message = "User already exists";
        } else {
            saveInDb(userA);
            response.message = String.format("User with username: %s," +
                    " and password %s was created with role %s and ID: %d", userA.email,
                    userA.password, "student", userA.id);
        }
        return response;
    }

    private void saveInDb(UserDto userDto) {
        userDto.active = false;
        userDto.id = db.get().counter.incrementAndGet();
        db.get().users.put(userDto.id, userDto);

        //student role
        db.get().userToRoleMapping.put(userDto.id, (long)2);
    }


    private boolean exists(UserDto userA, Map<Long, UserDto> users) {
        boolean f = false;

        for (Long key: users.keySet()) {
            UserDto userB = users.get(key);

            if(userA.email.equals(userB.email) &&
                    userA.password.equals(userB.password)) {
                f = true;
                break;
            }
        }
        return f;
    }
}
