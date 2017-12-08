package kz.zhadyrassyn.regsystem.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.zhadyrassyn.regsystem.controller.model.GroupType;
import kz.zhadyrassyn.regsystem.controller.model.StudentInfo;
import kz.zhadyrassyn.regsystem.controller.register.StudentRegister;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.db.Db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Bean
public class StudentRegisterImpl implements StudentRegister{

    public BeanGetter<Db> db;

    @Override
    public StudentInfo get(int id) {
        return null;
    }

    @Override
    public List<StudentInfo> all() {
//        Map<Long, StudentDto> studentsFromDB = db.get().students;
//        List<StudentInfo> studentsToReturn = new ArrayList<>();
//        for(Long id: studentsFromDB.keySet()) {
//            StudentDto studentDto = studentsFromDB.get(id);
//            StudentInfo studentInfo = new StudentInfo(studentDto.id,
//                    studentDto.name, studentDto.surname, studentDto.patronymic,
//                    studentDto.group);
//            studentsToReturn.add(studentInfo);
//        }
//
//        return studentsToReturn;
        return null;
    }

    @Override
    public void save(int id, String name, String surname, String patronymic, GroupType group) {

    }

    @Override
    public void delete(int id) {

    }
}
