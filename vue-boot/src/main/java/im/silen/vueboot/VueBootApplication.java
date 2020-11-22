package im.silen.vueboot;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "im.silen")
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
