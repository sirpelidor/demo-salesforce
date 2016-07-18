package demo.salesforce.services;

import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.ws.ConnectionException;
import demo.salesforce.domain.dao.ContactSalesForceDao;
import demo.salesforce.domain.dao.OpportunitySalesForceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service deal with UI related request
 */
@Service
public class SimpleService {
    private OpportunitySalesForceDao opportunitySalesForceDao;
    private ContactSalesForceDao contactSalesForceDao;
    //name doesn't contain 'University'
    private Predicate<Opportunity> hasClosed = opportunity -> opportunity.getIsClosed()== true;

    /**
     * Filter opportunity with name contains 'University'
     * @return
     */
    public List<Opportunity> getFilteredOpportunityList(){
        List<Opportunity> list = opportunitySalesForceDao.findAll();
        return list.stream()
                .filter(hasClosed)
                .collect(Collectors.toList());
    }

    public List<Opportunity> getAllOpportunityList(){
        return opportunitySalesForceDao.findAll();
    }

    public List<Contact> getAllContactList(){
        return contactSalesForceDao.findAll();
    }

    public String getSessionId(){
        String id;
        try {
            id = opportunitySalesForceDao.getConnection().getConfig().getSessionId();
        } catch (ConnectionException e) {
            id = "sessionIdNotAvailable";
        }
        return id;
    }

    @Autowired
    public void setOpportunitySalesForceDao(OpportunitySalesForceDao opportunitySalesForceDao) {
        this.opportunitySalesForceDao = opportunitySalesForceDao;
    }

    @Autowired
    public void setContactSalesForceDao(ContactSalesForceDao contactSalesForceDao) {
        this.contactSalesForceDao = contactSalesForceDao;
    }
}
