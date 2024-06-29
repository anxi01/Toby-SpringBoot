package spring.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // 클래스 레벨에서 구성 정보가 있음을 먼저 스프링 컨테이너에게 알려준다.
public class TobySpringbootApplication {

  @Bean
  public HelloController helloController(HelloService helloService) {
    return new HelloController(helloService);
  }

  @Bean
  public HelloService helloService() {
    return new SimpleHelloService();
  }

  public static void main(String[] args) {

    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
      @Override
      protected void onRefresh() {
        super.onRefresh();

        // onRefresh를 통해 애플리케이션의 특정 상태를 초기화하거나 설정할 수 있다.
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
              .addMapping("/*");
        });
        webServer.start();
      }
    };
    applicationContext.register(TobySpringbootApplication.class);
    applicationContext.refresh();
  }

}
