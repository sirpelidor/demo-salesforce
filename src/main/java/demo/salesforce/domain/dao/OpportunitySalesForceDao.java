package demo.salesforce.domain.dao;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.ws.ConnectionException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpportunitySalesForceDao extends AbstractSalesForceDao<Opportunity> {
    private static Logger logger = Logger.getLogger(OpportunitySalesForceDao.class);

    @Override
    public List<Opportunity> findAll() {
        List<Opportunity> opportunities;
        String query = "select AccountId, Amount, CampaignId, CloseDate, CreatedById, CreatedDate, CurrentGenerators__c, DeliveryInstallationStatus__c, " +
                "Description, ExpectedRevenue, Fiscal, FiscalQuarter, FiscalYear, ForecastCategory, ForecastCategoryName, HasOpenActivity, HasOpportunityLineItem, " +
                "HasOverdueTask, IsClosed, IsDeleted, IsPrivate, IsWon, LastActivityDate, LastModifiedById, LastModifiedDate, LastReferencedDate, LastViewedDate, " +
                "LeadSource, MainCompetitors__c, Name, NextStep, OrderNumber__c, OwnerId, Pricebook2Id, Probability, StageName, SystemModstamp, " +
                "TotalOpportunityQuantity, TrackingNumber__c, Type " +
                "from Opportunity where AccountId != null";

        try {
            QueryResult queryResult = this.getConnection().query(query);
            opportunities = this.transform(queryResult.getRecords());
        } catch (ConnectionException e) {
            logger.fatal(e.getMessage());
            opportunities = new ArrayList<>();
        }
        return opportunities;
    }

}