package demo.salesforce.domain.dao;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactSalesForceDaoTest {

    @Test
    public void testFindAll() throws ConnectionException {
        EnterpriseConnection mockConnection = mock(EnterpriseConnection.class);
        QueryResult mockQueryResult = mock(QueryResult.class);

        ContactSalesForceDao dao = new ContactSalesForceDao();
        dao.setConnection(mockConnection);
        when(mockConnection.query(anyString())).thenReturn(mockQueryResult);
        when(mockQueryResult.getRecords()).thenReturn(getList());

        List<?> list = dao.findAll();
        assertEquals(3, list.size());
    }

    @Test
    public void testTransform() {
        ContactSalesForceDao dao = new ContactSalesForceDao();
        List<?> list = dao.transform(getList());

        String type = "com.sforce.soap.enterprise.sobject.Contact";
        Object o = list.get(0);
        assertEquals(type, o.getClass().getTypeName());
    }

    @Test(expected = ClassCastException.class)
    public void badTransform() {
        ContactSalesForceDao dao = new ContactSalesForceDao();
        List<Contact> list = dao.transform(getBadList());
        Contact c = list.get(0);
    }

    private SObject[] getList() {
        Contact c1 = new Contact();
        c1.setName("c1");
        Contact c2 = new Contact();
        c1.setName("c2");
        Contact c3 = new Contact();
        c1.setName("c3");

        SObject[] ary = {c1, c2, c3};
        return ary;
    }

    private SObject[] getBadList() {
        Opportunity o1 = new Opportunity();
        o1.setName("o1");
        Opportunity o2 = new Opportunity();
        o2.setName("o2");
        Opportunity o3 = new Opportunity();
        o3.setName("o3");

        SObject[] ary = {o1, o2, o3};
        return ary;
    }
}