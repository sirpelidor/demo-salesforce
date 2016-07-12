package demo.salesforce;

import org.junit.Test;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

import static org.junit.Assert.*;

public class JsfConfigurationTest {

    @Test
    public void testServletRegistrationBean(){
        JsfConfiguration config = new JsfConfiguration();
        ServletRegistrationBean bean = config.servletRegistrationBean();
        assertEquals("facesServlet", bean.getServletName());
    }
}