package demo.salesforce.managedbean;

import com.sforce.soap.enterprise.sobject.Opportunity;
import demo.salesforce.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for opportunity.xhtml
 */
@Component
@ManagedBean
@RequestScoped
public class OpportunityBean {

    private SimpleService service;

    public List<Opportunity> getOpportunities() {
        return service.getAllOpportunityList();
    }

    public List<Opportunity> getFilteredOpportunities() {
        return service.getFilteredOpportunityList();
    }

    public String getSessionId(){
        return service.getSessionId();
    }

    @Autowired
    public void setService(SimpleService service) {
        this.service = service;
    }
}