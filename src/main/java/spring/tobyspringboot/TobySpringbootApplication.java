package spring.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class TobySpringbootApplication {

  @Bean
  public ServletWebServerFactory servletContainer() {
    return new TomcatServletWebServerFactory();
  }

  @Bean
  public DispatcherServlet dispatcherServlet() {
    // ApplicationContextAware의 setApplicationContext() 메서드를 이용하여
    // 기존 DispatcherServlet(ApplicationContext)를 넘겨주지 않아도 자동으로 넣어주어 동작한다.
    return new DispatcherServlet();
  }

  public static void main(String[] args) {

    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
      @Override
      protected void onRefresh() {
        super.onRefresh();

        ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
        DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet", dispatcherServlet)
              .addMapping("/*");
        });
        webServer.start();
      }
    };
    applicationContext.register(TobySpringbootApplication.class);
    applicationContext.refresh();
  }

}
