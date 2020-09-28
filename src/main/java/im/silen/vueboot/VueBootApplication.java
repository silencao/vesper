package im.silen.vueboot;

import im.silen.vueboot.user.UserMapper;
import org.aopalliance.intercept.Interceptor;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.interceptor.DebugInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.lang.reflect.Method;

@SpringBootApplication
public class VueBootApplication {
    @Bean
    public Hello person() {
        Hello hello = name -> "hello " + name;
        AfterReturningAdvice afterAdvice = (returnValue, method, args, target) -> System.out.println("hello world");
        ProxyFactory factory = new ProxyFactory(hello);

        factory.addAdvice(afterAdvice);
        return (Hello) factory.getProxy();
    }

    public static void main(String[] args) {
        SpringApplication.run(VueBootApplication.class, args);
    }

}
