package demo.salesforce.services;

import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Opportunity;
import demo.salesforce.domain.dao.ContactSalesForceDao;
import demo.salesforce.domain.dao.OpportunitySalesForceDao;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleServiceTest {

    @Test
    public void testGetAllOpportunityList(){
        OpportunitySalesForceDao opportunitySalesForceDao = mock(OpportunitySalesForceDao.class);
        when(opportunitySalesForceDao.findAll()).thenReturn(getOpportunities());

        SimpleService svc = new SimpleService();
        svc.setOpportunitySalesForceDao(opportunitySalesForceDao);

        List<Opportunity> list = svc.getAllOpportunityList();
        assertEquals(4, list.size());
    }

    @Test
    public void testGetFilteredOpportunityList(){
        OpportunitySalesForceDao opportunitySalesForceDao = mock(OpportunitySalesForceDao.class);
        when(opportunitySalesForceDao.findAll()).thenReturn(getOpportunities());

        SimpleService svc = new SimpleService();
        svc.setOpportunitySalesForceDao(opportunitySalesForceDao);

        List<Opportunity> list = svc.getFilteredOpportunityList();
        assertEquals(3, list.size());
    }

    @Test
    public void testGetAllContactList(){
        ContactSalesForceDao dao = mock(ContactSalesForceDao.class);
        when(dao.findAll()).thenReturn(getContacts());

        SimpleService svc = new SimpleService();
        svc.setContactSalesForceDao(dao);

        List<Contact> list = svc.getAllContactList();
        assertEquals(3, list.size());
    }

    private List<Opportunity> getOpportunities(){
        Opportunity o1 = new Opportunity();
        o1.setName("o1");
        o1.setIsClosed(true);
        Opportunity o2 = new Opportunity();
        o2.setName("o2");
        o2.setIsClosed(true);
        Opportunity o3 = new Opportunity();
        o3.setName("o3 University");
        o3.setIsClosed(true);
        Opportunity o4 = new Opportunity();
        o4.setName("o4");
        o4.setIsClosed(false);

        return Arrays.asList(o1,o2,o3,o4);
    }

    private List<Contact> getContacts(){
        Contact c1 = new Contact();
        c1.setName("c1");
        Contact c2 = new Contact();
        c2.setName("c2");
        Contact c3 = new Contact();
        c3.setName("c3");

        return Arrays.asList(c1,c2,c3);
    }
}