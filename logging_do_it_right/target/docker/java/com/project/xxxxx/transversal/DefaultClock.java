package com.project.xxxxx.transversal;

import io.jsonwebtoken.Clock;
import java.util.Date;

public class DefaultClock implements Clock {
    public static final Clock INSTANCE = new DefaultClock();

    @Override
    public Date now() {
        return new Date();
    }
}
