package spring.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TobySpringbootApplication {

  public static void main(String[] args) {

    GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
    applicationContext.registerBean(HelloController.class);
    applicationContext.registerBean(SimpleHelloService.class);
    applicationContext.refresh();
  }

}
