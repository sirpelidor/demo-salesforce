package demo.salesforce;

import demo.salesforce.servlets.LoginServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Adding LoginServlet programmatically
 */
@Configuration
public class OpenIdConfiguration {

    @Bean
    public ServletRegistrationBean addOpenId() {
        LoginServlet servlet = new LoginServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "/openidtester");
        return servletRegistrationBean;
    }
}
