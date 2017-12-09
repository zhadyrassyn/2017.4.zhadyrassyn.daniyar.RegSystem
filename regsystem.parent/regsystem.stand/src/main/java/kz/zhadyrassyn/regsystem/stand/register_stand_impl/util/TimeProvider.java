package kz.zhadyrassyn.regsystem.stand.register_stand_impl.util;

import kz.greetgo.depinject.core.Bean;

import java.io.Serializable;
import java.util.Date;

@Bean
public class TimeProvider implements Serializable {
    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
}

