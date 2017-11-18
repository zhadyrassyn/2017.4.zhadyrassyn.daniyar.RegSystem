package kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Параметры доступа к Gmail аккаунту")
public interface MyConfig {

    @DefaultStrValue("zhadyrassyn.daniyar@is.sdu.edu.kz")
    @Description("Логин Аккаунта")
    String loginAccount();


    @DefaultStrValue("134679976431")
    @Description("Пароль Аккаунта")
    String accountPassword();


    @DefaultStrValue("smtp.gmail.com")
    @Description("Host почты")
    String smtpHost();


    @DefaultStrValue("465")
    @Description("Port почты")
    String smtpPort();


    @DefaultBoolValue(false)
    @Description("Нужно ли отправлять Email?")
    boolean needToSendEmail();

    @DefaultIntValue(10)
    @Description("Дни для хранения почты")
    int getEmailSaveDays();

}