package demo.salesforce.domain.dao;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.ws.ConnectionException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactSalesForceDao extends AbstractSalesForceDao<Contact> {
    private static Logger logger = Logger.getLogger(ContactSalesForceDao.class);

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts;
        String query = "select Id, Name, Account.Name from Contact where accountId != null";

        try {
            QueryResult queryResult = this.getConnection().query(query);
            contacts = this.transform(queryResult.getRecords());
        } catch (ConnectionException e) {
            logger.fatal(e.getMessage());
            contacts = new ArrayList<>();
        }
        return contacts;
    }
}