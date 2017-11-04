package kz.zhadyrassyn.regsystem.stand.register_stand_impl.db;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.HasAfterInject;
import kz.zhadyrassyn.regsystem.controller.model.GroupType;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.RoleDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.StudentDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Bean
public class Db implements HasAfterInject{

    public final Map<Long, StudentDto> students = new HashMap<>();
    public AtomicLong counter = new AtomicLong();

    public final Map<Long, UserDto> users = new HashMap<>();
    public final Map<Long, RoleDto> roles = new HashMap<>();
    public final Map<Long, Long> userToRoleMapping = new HashMap<>();

    public final Map<String, UserDto> activationTokenToUserMapping = new HashMap<>();

    @Override
    public void afterInject() throws Exception {
        StudentDto s1 = new StudentDto(counter.incrementAndGet(),
                "Daniyar", "Zhadyrassyn", "Temirbekovich", GroupType.A);
        StudentDto s2 = new StudentDto(counter.incrementAndGet(),
                "Sayatbek", "Samat", "Kairatovich", GroupType.B);
        StudentDto s3 = new StudentDto(counter.incrementAndGet(),
                "Maksat", "Kylyshbek", "Tohtarovich", GroupType.F);
        StudentDto s4 = new StudentDto(counter.incrementAndGet(),
                "Duysen", "Yeldisbayev", "Madiyarovich", GroupType.C);
        StudentDto s5 = new StudentDto(counter.incrementAndGet(),
                "Samal", "Aben", "Kairatovna", GroupType.D);

        students.put(s1.id, s1);
        students.put(s2.id, s2);
        students.put(s3.id, s3);
        students.put(s4.id, s4);
        students.put(s5.id, s5);


        UserDto moderator1 = new UserDto();
        moderator1.active = true;
        moderator1.id = counter.incrementAndGet();
        moderator1.email = "m1@test.ru";
        moderator1.password = "qwerty";

        UserDto moderator2 = new UserDto();
        moderator2.active = true;
        moderator2.id = counter.incrementAndGet();
        moderator2.email = "m2@test.ru";
        moderator2.password = "qwerty";

        users.put(moderator1.id, moderator1);
        users.put(moderator2.id, moderator2);

        RoleDto r1 = new RoleDto(counter.incrementAndGet(), "moderator");
        RoleDto r2 = new RoleDto(counter.incrementAndGet(), "student");

        roles.put(r1.id, r1);
        roles.put(r2.id, r2);

        userToRoleMapping.put(moderator1.id, r1.id);
        userToRoleMapping.put(moderator2.id, r1.id);
    }

}