package schedule.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    // logs any method in any class in the given package
    @Pointcut("execution(* schedule.service.impl.*.*(..)) || execution(* schedule.dao.impl.*.*(..))")
    public void servicePointCut() {
        // Pointcut for advice execution.
    }

    /**
     * This logging will log input arguments
     *
     * @param joinPoint method execution
     */
    // calls servicePointCut() where package is determined
    @Before("servicePointCut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info(String.format("#### A method: {%s} is called", joinPoint));
        logger.info(String.format("#### Arguments: %s ", Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * This logging will be executed in case of successful method completing
     *
     * @param joinPoint method execution
     */
    // calls servicePointCut() where package is determined
    @AfterReturning("servicePointCut()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("#### Success execution of method :{"+ joinPoint.getSignature().getName()+"}");
    }

    /**
     * This logging will be executed in case of method completes with exception
     *
     * @param joinPoint method execution
     * @param e         throwing exception
     */
    // calls servicePointCut() where package is determined
    @AfterThrowing(value = "servicePointCut()", throwing = "e")
    public void logExceptions(JoinPoint joinPoint, Exception e) {
        logger.error(String.format("#### Exception thrown in the method: {%s}, the message is {%s}",
                joinPoint, e.getMessage()));
        logger.error(" #### The stack trace is below");
        for (StackTraceElement b : e.getStackTrace()) {
            logger.error("#### " + b);
        }
    }
}