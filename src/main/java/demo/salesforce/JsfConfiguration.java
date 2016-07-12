package demo.salesforce;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;

/**
 * Adding FaceServlet programmatically, this still rely FaceServlet define in web.xml
 */
@Configuration
public class JsfConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "/appui/*");
        return servletRegistrationBean;
    }
}