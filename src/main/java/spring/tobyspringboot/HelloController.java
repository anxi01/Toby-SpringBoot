package spring.tobyspringboot;

import java.util.Objects;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
@MyComponent
public class HelloController implements ApplicationContextAware {
  private final HelloService helloService;
  private ApplicationContext applicationContext;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping
  @ResponseBody // (= @RestController)
  public String hello(String name) {

    return helloService.sayHello(Objects.requireNonNull(name));
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    System.out.println(applicationContext);
    this.applicationContext = applicationContext;
  }
}
