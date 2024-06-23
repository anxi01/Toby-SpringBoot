package spring.tobyspringboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TobySpringbootApplication {

  public static void main(String[] args) {

    ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
    WebServer webServer = serverFactory.getWebServer(servletContext -> {
      HelloController helloController = new HelloController();

      servletContext.addServlet("frontController", new HttpServlet() {
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
          // 프론트 컨트롤러 : 앞단에서 공통적인 기능 처리 및 여러가지 작업을 매핑
          // 인증, 보안, 다국어, 공통 기능 ...

          // 매핑 : 웹 요청의 정보를 가지고 어떤 로직을 수행하는 코드를 결정하는 것
          if (req.getRequestURI().equals("/hello") && req.getMethod()
              .equals(HttpMethod.GET.name())) {
            String name = req.getParameter("name");

            // 바인딩 : 파라미터(name)를 받아서 처리하는 오브젝트에 넘겨주는 것
            String ret = helloController.hello(name);

            resp.setStatus(HttpStatus.OK.value());
            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
            resp.getWriter().println(ret);
          } else if (req.getRequestURI().equals("/user")) {

          } else {
            resp.setStatus(HttpStatus.NOT_FOUND.value());
          }
        }
      }).addMapping("/*");
    });
    webServer.start();
  }

}
