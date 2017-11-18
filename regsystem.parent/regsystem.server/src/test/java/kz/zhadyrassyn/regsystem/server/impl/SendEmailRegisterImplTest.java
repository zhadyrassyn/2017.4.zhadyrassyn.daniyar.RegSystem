package kz.zhadyrassyn.regsystem.server.impl;

import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.depinject.testng.AbstractDepinjectTestNg;
import kz.greetgo.depinject.testng.ContainerConfig;
import kz.zhadyrassyn.regsystem.controller.register.SendEmailRegister;
import kz.zhadyrassyn.regsystem.server.test.util.BeanConfigMainServerPostgresTest;
import org.testng.annotations.Test;

@ContainerConfig(BeanConfigMainServerPostgresTest.class)
public class SendEmailRegisterImplTest extends AbstractDepinjectTestNg {

    public BeanGetter<SendEmailRegister> sendEmailRegister;

    @Test
    public void testToSend() throws Exception {
        sendEmailRegister.get().toSend();
    }

    @Test
    public void testPrepareSendEmail() throws Exception {
        sendEmailRegister.get().prepareSendEmail();
    }

}
