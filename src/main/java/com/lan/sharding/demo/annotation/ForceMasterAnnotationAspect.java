package com.lan.sharding.demo.annotation;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription 强制主库实现，使用完要释放掉
 * @date 2019-04-11 15:46
 */
@Aspect
@Component
public class ForceMasterAnnotationAspect {

    @Around("@annotation(com.lan.sharding.demo.annotation.ForceMaster)")
    public void doForceMaster(ProceedingJoinPoint pjp) throws Throwable {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            hintManager.close();
        }

    }


}
