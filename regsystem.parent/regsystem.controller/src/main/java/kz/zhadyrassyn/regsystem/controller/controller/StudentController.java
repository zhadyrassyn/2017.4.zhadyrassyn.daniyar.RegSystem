package kz.zhadyrassyn.regsystem.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.Par;
import kz.greetgo.mvc.annotations.ToJson;
import kz.zhadyrassyn.regsystem.controller.model.StudentInfo;
import kz.zhadyrassyn.regsystem.controller.register.StudentRegister;
import kz.zhadyrassyn.regsystem.controller.utils.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Bean
@Mapping("/student")
public class StudentController implements Controller {

    public BeanGetter<StudentRegister> studentRegister;
//
//    @ToJson
//    @Mapping("/get")
//    public PersonInfo getPerson(@Par("id")int id) {
//        return personRegister.get().getPersonInfo(id);
//    }

    @ToJson
    @Mapping("/all")
    public List<StudentInfo> getAllPerson() {
        return studentRegister.get().all();
    }
//

//    @ToJson
//    @PostConstruct
//    @Mapping("/save")
//    public void save(
//            @Par("id") int id, @Par("name") String name, @Par("surname") String surname, @Par("patronymic") String patronymic,
//            @Par("age") int age) {
//        personRegister.get().save(id, name, surname, patronymic, age);
//    }
//
//    @ToJson
//    @PostConstruct
//    @Mapping("/delete")
//    public void delete(@Par("id") int id) {
//        personRegister.get().deletePerson(id);
//    }
//
//    @ToJson
//    @Mapping("/daniyar")
//    public PersonInfo get() {
//        return personRegister.get().getPerson5();
//    }
}
