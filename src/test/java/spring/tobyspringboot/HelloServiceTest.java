package spring.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

  @Test
  void simpleHelloService() {
    SimpleHelloService simpleHelloService = new SimpleHelloService();

    String result = simpleHelloService.sayHello("Test");

    Assertions.assertThat(result).isEqualTo("Hello Test");
  }

  @Test
  void helloDecorator() {
    HelloDecorator decorator = new HelloDecorator(name -> name);

    String ret = decorator.sayHello("Test");

    Assertions.assertThat(ret).isEqualTo("*Test*");
  }

}
