package com.bol.assignment.kalaha.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Aspects created for logging the performance of each game events & exceptions
 *
 * @author BOL.com
 */
@Aspect
@Component
@Slf4j
public class GameLogging {

    /**
     * Logs the total time taken to complete the execution
     */
    private Object around(final ProceedingJoinPoint jp, final String msg) throws Throwable {
        LocalDateTime startTime = LocalDateTime.now();
        Object obj = jp.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        log.info("Thread - " + Thread.currentThread().getId() + ": " + msg + (ChronoUnit.MILLIS.between(startTime, endTime)));
        return obj;
    }

    /**
     * Aspect created around the start game endpoint
     */
    @Around("execution(* com.bol.assignment.kalaha.controller.GameController.startGame(..))")
    public Object logStartGame(final ProceedingJoinPoint jp) throws Throwable {
        return around(jp, "Total Execution Time for Creating Game : ");
    }

    /**
     * Aspect created around the sowing stones endpoint
     */
    @Around("execution(* com.bol.assignment.kalaha.controller.GameController.sowStones(..))")
    public Object logSowStones(final ProceedingJoinPoint jp) throws Throwable {
        return around(jp, "Total Execution Time for sowing stones : ");
    }

    /**
     * Aspect created for logging logs when exception occurs
     */
    @AfterThrowing(pointcut = "execution(* com.bol.assignment.kalaha.controller.GameController.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(final Exception ex) {
        log.error(ex.getLocalizedMessage());
    }
}
