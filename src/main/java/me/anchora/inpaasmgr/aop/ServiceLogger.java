package me.anchora.inpaasmgr.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ServiceLogger {

    private Logger logger = Logger.getLogger(getClass());

    public void doAfter(JoinPoint jp) {
        logger.info(jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName() + " is ended.");
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            time = System.currentTimeMillis() - time;
            logger.info(pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName() + " invocation time: " + time + " ms");
        }
    }

    public void doBefore(JoinPoint jp) {
        logger.info(jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName() + " is start.");
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        logger.info(jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName() + " throw exception. " + ex.getMessage());
    }

}