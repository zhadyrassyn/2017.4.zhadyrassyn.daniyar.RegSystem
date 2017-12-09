package kz.zhadyrassyn.regsystem.stand.register_stand_impl.db;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.HasAfterInject;
import kz.zhadyrassyn.regsystem.controller.model.enums.GenderEnum;
import kz.zhadyrassyn.regsystem.controller.model.enums.RoleTitleEnum;
import kz.zhadyrassyn.regsystem.controller.model.enums.UserStatusEnum;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.GroupDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.RoleDto;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.UserDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Bean
public class Db implements HasAfterInject{

    //counters
    public AtomicLong userCounter = new AtomicLong();
    public AtomicLong groupCounter = new AtomicLong();
    public AtomicLong roleCounter = new AtomicLong();

    //dtos
    public final Map<Long, UserDto> users = new HashMap<>();
    public final Map<Long, RoleDto> roles = new HashMap<>();
    public final Map<Long, GroupDto> groups = new HashMap<>();
    public final Map<String, UserDto> activationTokenToUserMapping = new HashMap<>();

    //mappings
    public final Map<Long, Long> userToRoleMapping = new HashMap<>();
    public final Map<Long, Long> userToGroupMapping = new HashMap<>();

    @Override
    public void afterInject() throws Exception {
        fillGroups();
        fillUsersAndRoles();

    }

    private void fillUsersAndRoles() throws ParseException {
        RoleDto r1 = new RoleDto(roleCounter.incrementAndGet(), RoleTitleEnum.STUDENT);
        RoleDto r2 = new RoleDto(roleCounter.incrementAndGet(), RoleTitleEnum.MODERATOR);
        RoleDto r3 = new RoleDto(roleCounter.incrementAndGet(), RoleTitleEnum.ADMIN);

        roles.put(r1.id, r1);
        roles.put(r2.id, r2);
        roles.put(r3.id, r3);

        UserDto u1 = new UserDto();
        u1.id = userCounter.incrementAndGet();
        u1.name = "Данияр";
        u1.surname = "Жадырасын";
        u1.patronymic = "Темирбекович";
        u1.studentId = "140103060";
        u1.email = "zhadyrassyn.daniyar@is.sdu.edu.kz";
        u1.password = "123";
        u1.gender = GenderEnum.MALE;
        u1.birthDate = stringToDate("11/06/1997");
        u1.phone = "87028733234";

        UserDto u2 = new UserDto();
        u2.id = userCounter.incrementAndGet();
        u2.name = "Димаш";
        u2.surname = "Казбек";
        u2.patronymic = "Сабыржанович";
        u2.studentId = "qwerty";
        u2.email = "dimash@test.ru";
        u2.password = "123";
        u2.gender = GenderEnum.MALE;
        u2.birthDate = stringToDate("11/06/1998");
        u2.status = UserStatusEnum.ACTIVE;
        u2.phone = "8883242343";

        UserDto u3 = new UserDto();
        u3.id = userCounter.incrementAndGet();
        u3.name = "Сандугаш";
        u3.surname = "Сарбас";
        u3.patronymic = "Дуйсеновна";
        u3.studentId = "bestadmin";
        u3.email = "sandugash@test.ru";
        u3.password = "123";
        u3.gender = GenderEnum.FEMALE;
        u3.birthDate = stringToDate("11/06/1996");
        u3.status = UserStatusEnum.ACTIVE;
        u3.phone = "87783323431";

        users.put(u1.id, u1);
        users.put(u2.id, u2);
        users.put(u3.id, u3);

        //mappings users to roles
        userToRoleMapping.put(u1.id, r1.id);
        userToRoleMapping.put(u2.id, r2.id);
        userToRoleMapping.put(u3.id, r3.id);

        //mappings users to group
        userToGroupMapping.put(u1.id, (long) 1);
    }

    private void fillGroups() {
        String[] faculties = {"EN", "PHIL", "LAW", "ECO"};
        String[] groupNames = {"A", "B", "C", "D"};

        for(int i = 0; i < 4; i++) { //faculty
            for(int j = 0; j < 4; j++) { //course
                for(int k = 0; k < 4; k++) { //groupname
                    GroupDto g = new GroupDto();
                    g.id = groupCounter.incrementAndGet();
                    g.faculty = faculties[i];
                    g.course = j+1;
                    g.name = faculties[i] + "" + g.course + "" + groupNames[k] + "03";

                    groups.put(g.id, g);
                }
            }
        }
    }

    private Date stringToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.parse(date);
    }

}