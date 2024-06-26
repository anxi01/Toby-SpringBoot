package spring.tobyspringboot;

import java.util.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
@MyComponent
public class HelloController {
  private final HelloService helloService;
  private final ApplicationContext applicationContext;

  public HelloController(HelloService helloService, ApplicationContext applicationContext) {
    this.helloService = helloService;
    this.applicationContext = applicationContext;
  }

  @GetMapping
  @ResponseBody // (= @RestController)
  public String hello(String name) {

    return helloService.sayHello(Objects.requireNonNull(name));
  }
}
