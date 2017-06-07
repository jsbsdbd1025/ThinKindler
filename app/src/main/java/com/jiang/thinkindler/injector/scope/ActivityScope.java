package com.jiang.thinkindler.injector.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
