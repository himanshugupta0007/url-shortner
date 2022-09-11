/**
 *
 */
package com.mysocialpal.urlshortner.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This configuration class configures the AOP for logging purpose. This defines
 * the aspects and point cut expressions
 *
 * @author Himanshu.Gupta
 *
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class URLShortnerAspectConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.mysocialpal.urlshortner.controller.*.*(..))")
    public void loggingOnController() {
    }

    @Pointcut("execution(* com.mysocialpal.urlshortner.service.*.*(..))")
    public void loggingOnService() {
    }

    @Pointcut("execution(* com.mysocialpal.urlshortner.utility.*.*(..))")
    public void loggingOnUtility() {
    }

    @Around("@annotation(com.mysocialpal.urlshortner.config.TrackTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        logger.info("Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: "
                + point.getSignature().getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");
        return object;
    }

}
