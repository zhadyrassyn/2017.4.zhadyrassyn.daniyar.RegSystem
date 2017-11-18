package kz.zhadyrassyn.regsystem.server.test.util;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.Include;
import kz.zhadyrassyn.regsystem.server.beans.all.any_db.BeanConfigAnyDbAll;

@BeanConfig
@Include({BeanConfigAnyDbAll.class})
public class BeanConfigMainServerPostgresTest {
}