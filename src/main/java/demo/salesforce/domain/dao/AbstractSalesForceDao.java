package demo.salesforce.domain.dao;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import demo.salesforce.SalesforceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public abstract class AbstractSalesForceDao<T extends SObject> {
    private EnterpriseConnection connection;
    private SalesforceConfiguration configuration;

    public final EnterpriseConnection getConnection() throws ConnectionException {
        if ((connection != null) && (isConnectionAlive(connection)))
            return connection;

        //rerun loginSalesforceApi if connection expired
        return configuration.loginSalesforceApi();
    }

    /**
     * @param connection
     * @return true if connection is alive, false otherwise
     */
    private boolean isConnectionAlive(EnterpriseConnection connection) {
        try {
            connection.getUserInfo();
        } catch (ConnectionException e) {
            return false;
        }
        return true;
    }

    /**
     * convert a SObject object into its sub-type
     */
    protected Function<SObject, T> sObject2subtype = (sObject) -> (T) sObject;

    /**
     * transform a list of sOnjects into a list of its' subtype
     *
     * @param sObjects
     * @return
     */
    protected List<T> transform(SObject[] sObjects) {
        return Arrays.asList(sObjects).stream()
                .map(sObject2subtype)
                .collect(Collectors.toList());
    }

    public abstract List<T> findAll();

    @Autowired
    protected void setConnection(EnterpriseConnection con) {
        connection = con;
    }

    @Autowired
    protected void setConfiguration(SalesforceConfiguration configuration) {
        this.configuration = configuration;
    }
}