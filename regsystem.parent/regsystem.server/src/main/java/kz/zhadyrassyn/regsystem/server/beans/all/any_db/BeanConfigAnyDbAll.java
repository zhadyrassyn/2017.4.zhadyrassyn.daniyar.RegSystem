package kz.zhadyrassyn.regsystem.server.beans.all.any_db;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.BeanScanner;
import kz.greetgo.depinject.core.Include;
import kz.zhadyrassyn.regsystem.server.impl.BeanConfigRegisterServerImpl;

@BeanConfig
@BeanScanner
@Include({BeanConfigRegisterServerImpl.class})
public class BeanConfigAnyDbAll {
}