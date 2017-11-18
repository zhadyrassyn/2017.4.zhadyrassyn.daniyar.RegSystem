package kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.email.EmailSenderController;
import kz.greetgo.scheduling.FromConfig;
import kz.greetgo.scheduling.HasScheduled;
import kz.greetgo.scheduling.Scheduled;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Bean
public class MyTask implements HasScheduled {

    public BeanGetter<EmailSenderController> emailSenderControllerBeanGetter;
    private static Logger LOG = Logger.getLogger(MyTask.class);
    private final Random random = new Random();

    @FromConfig("Параметры запуска таска по файлам")
    @Scheduled("repeat every 90 sec")
    public void doAJob() throws IOException {
//        File parentFile;
//        File file = new File(""+System.getProperty("user.home") + "/education.d/" + System.currentTimeMillis());
//        parentFile = file.getParentFile();
//        if(!parentFile.exists()){
//            parentFile.mkdirs();
//        }
//        file.createNewFile();
        LOG.info("[SCHEDULED__001] НАЧАЛО ПРОЦЕССА ОТПРАВКИ ПИСЬМА");
        emailSenderControllerBeanGetter.get().sendAllExistingEmails();
        LOG.info("[SCHEDULED__002] КОНЕЦ ПРОЦЕССА ОТПРАВКИ ПИСЬМА");
    }

    @Scheduled("23:00")
    public void task2(){
        //Nothing for check
    }

//    @Scheduled("повторять каждые 10000 сек")
//    public void doSchedule() throws InterruptedException, IOException {
//        LOG.info("[SCHEDULED__001] Начало процесса");
//        Thread.sleep(500);
//        if(random.nextBoolean()){
//            LOG.error("[SCHEDULED__003] Ошибка в ходе выполнения операции");
//        }
//
//        if(LOG.isDebugEnabled())
//            LOG.debug("Сообщение "+ gun());
//        LOG.info("[SCHEDULED__002] Конец процесса");
//    }


    private String gun() throws IOException {
        File parentFile;
        File file = new File(""+System.getProperty("user.home") + "/regsystem.d/" + System.currentTimeMillis());
        parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        file.createNewFile();

        return null;
    }
}