package kz.zhadyrassyn.regsystem.stand.bean_containers;

import kz.greetgo.depinject.core.BeanContainer;
import kz.greetgo.depinject.core.Include;
import kz.zhadyrassyn.regsystem.stand.beans.StandServer;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MainScheduler;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MyConfig;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.scheduler.MyTask;

@Include(BeanConfigForStandBeanContainer.class)
public interface StandBeanContainer extends BeanContainer {
  StandServer standServer();

  MyConfig myConfig();

  MainScheduler getMainScheduler();

  MyTask myTask();

}