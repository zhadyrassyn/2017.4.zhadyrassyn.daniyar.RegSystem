package kz.zhadyrassyn.regsystem.stand.bean_containers;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.BeanScanner;
import kz.greetgo.depinject.core.Include;
import kz.zhadyrassyn.regsystem.controller.controller.BeanConfigControllers;
import kz.zhadyrassyn.regsystem.stand.beans.BeanConfigStand;
import kz.zhadyrassyn.regsystem.stand.register_stand_impl.BeanConfigRegisterStandImpl;

@BeanConfig
@BeanScanner
@Include({BeanConfigStand.class, BeanConfigControllers.class, BeanConfigRegisterStandImpl.class})
public class BeanConfigForStandBeanContainer {
}
