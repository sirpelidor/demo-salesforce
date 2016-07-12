package demo.salesforce.managedbean;

import com.sforce.soap.enterprise.sobject.Contact;
import demo.salesforce.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for contact.xhtml
 */
@Component
@ManagedBean
@RequestScoped
public class ContactBean {
    private SimpleService service;

    public List<Contact> getContacts() {
        return service.getAllContactList();
    }

    @Autowired
    public void setService(SimpleService service) {
        this.service = service;
    }
}
