package com.lan.sharding.annotation;

import java.lang.annotation.*;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription 强制主库配置
 * @date 2019-04-11 15:45
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ForceMaster {

}
