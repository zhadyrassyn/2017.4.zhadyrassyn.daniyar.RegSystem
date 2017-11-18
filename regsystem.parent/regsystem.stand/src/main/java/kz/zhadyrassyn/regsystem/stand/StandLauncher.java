package kz.zhadyrassyn.regsystem.stand;

import kz.greetgo.depinject.Depinject;
import kz.greetgo.depinject.gen.DepinjectUtil;
import kz.zhadyrassyn.regsystem.stand.bean_containers.StandBeanContainer;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MainScheduler;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MyConfig;
import kz.zhadyrassyn.regsystem.stand.util.Modules;

public class StandLauncher {
    public static void main(String[] args) throws Exception {
        new StandLauncher().run();
    }

    private void run() throws Exception {

        DepinjectUtil.implementAndUseBeanContainers("kz.zhadyrassyn.regsystem.stand",
                Modules.standDir() + "/build/src_bean_container");


        StandBeanContainer container = Depinject.newInstance(StandBeanContainer.class);

        MainScheduler mainScheduler = container.getMainScheduler();
        mainScheduler.startSchedulers(container.myTask());

        MyConfig myConfig = container.myConfig();

        System.out.println(myConfig.loginAccount());
        container.standServer().start().join();

    }
}
