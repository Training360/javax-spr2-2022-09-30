package empapp;

import empapp.dto.CreateEmployeeCommand;
import empapp.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class MeasureAspect {

    @Around("execution(* empapp.EmployeeService.*(..))")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = null;
        if ("createEmployee".equals(joinPoint.getSignature().getName())) {
            CreateEmployeeCommand command = (CreateEmployeeCommand) joinPoint.getArgs()[0];
            result = joinPoint.proceed(new Object[]{new CreateEmployeeCommand(command.getName().toUpperCase(), null)});
        }
        else{
            result = joinPoint.proceed();
        }
        long end = System.currentTimeMillis();


        log.info("Call method {} in {} ms", joinPoint.getSignature().getName(),
                end - start);

        if (result instanceof EmployeeDto r) {
            r.setCreated(LocalDateTime.now());
        }

        return result;
    }
}
