package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.zhadyrassyn.regsystem.controller.model.SignInInfo;
import kz.zhadyrassyn.regsystem.controller.model.StudentInfo;
import kz.zhadyrassyn.regsystem.controller.register.SignInRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.RoleDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;

import java.util.Map;

@Bean
public class SignInRegisterImpl implements SignInRegister {

    public BeanGetter<Db> db;

    @Override
    public SignInInfo getWelcomeMessage(String username, String password) {
        UserDto userDto = new UserDto(username, password);
        Map<Long, UserDto> usersFromDb = db.get().users;
        boolean exists = exists(userDto, usersFromDb);

        SignInInfo response = new SignInInfo();
        if(exists) {
            UserDto fromDb = getUserFromDb(userDto, usersFromDb);
            RoleDto userRole = getUserRole(fromDb);
            response.id = fromDb.id;
            response.message = "Welcome, " + fromDb.login + ": " + userRole.title;
        } else {
            response.id = (long)-1;
            response.message = "Login or password is incorrect";
        }
        return response;
    }

    private RoleDto getUserRole(UserDto fromDb) {
        long roleId =  db.get().userToRoleMapping.get(fromDb.id);
        return db.get().roles.get(roleId);
    }

    private UserDto getUserFromDb(UserDto userA, Map<Long, UserDto> users) {
        for (Long key: users.keySet()) {
            UserDto userB = users.get(key);

            if(userA.login.equals(userB.login) &&
                    userA.password.equals(userB.password)) {
                return userB;
            }
        }
        return null;
    }


//
//    private void saveInDb(UserDto userDto) {
//        userDto.disabled = false;
//        userDto.id = db.get().counter.incrementAndGet();
//        db.get().users.put(userDto.id, userDto);
//
//        //student role
//        db.get().userToRoleMapping.put(userDto.id, (long)2);
//    }

    private boolean exists(UserDto userA, Map<Long, UserDto> users) {
        boolean f = false;

        for (Long key: users.keySet()) {
            UserDto userB = users.get(key);

            if(userA.login.equals(userB.login) &&
                    userA.password.equals(userB.password)) {
                f = true;
                break;
            }
        }
        return f;
    }
}
