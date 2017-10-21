package kz.zhadyrassyn.regsystem.stand.register_stand_impl.db;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.HasAfterInject;
import kz.zhadyrassyn.regsystem.controller.model.GroupType;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.model.StudentDto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Bean
public class Db implements HasAfterInject{

    public final Map<Long, StudentDto> students = new HashMap<>();
    public AtomicLong counter = new AtomicLong();

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
    }
}
