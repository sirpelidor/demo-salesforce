package demo.salesforce.servlets;

import org.apache.log4j.Logger;
import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LoginServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(LoginServlet.class);
    private static final long serialVersionUID = 922104757068395570L;
    static final long ONE_HOUR = 3600000L;
    static final long TWO_HOUR = ONE_HOUR * 2L;
    static final String ATTR_MAC = "openid_mac";
    static final String ATTR_ALIAS = "openid_alias";
    private OpenIdManager manager;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        manager = new OpenIdManager();
    }//init


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String op = request.getParameter("op");
        if (op == null) {
            // check nonce:
            logger.info(">>>>>>>> openid.response_nonce==>" + request.getParameter("openid.response_nonce"));
            checkNonce(request.getParameter("openid.response_nonce"));
            response.sendRedirect("/appui/index.xhtml");//brand new acct
        } else if ("Google".equals(op) || "Yahoo".equals(op)) {
            manager.setReturnTo("http://" + request.getServerName() + ":" + request.getServerPort() + "/openidtester");

            // redirect to Google/Yahoo sign on page:
            //String alias = manager.lookupExtNsAlias(op);
            Endpoint endpoint = manager.lookupEndpoint(op);
            String alias = endpoint.getAlias();
            logger.info(">>>>>>>> endpoint.getUrl()==>" + endpoint.getUrl());
            Association association = manager.lookupAssociation(endpoint);
            HttpSession session = request.getSession();
            session.setAttribute(ATTR_MAC, association.getRawMacKey());
            session.setAttribute(ATTR_ALIAS, alias);
            String url = manager.getAuthenticationUrl(endpoint, association);
            response.sendRedirect(url);
        } else {
            throw new ServletException("Bad parameter op=" + op);
        }
    }//doGet

    private void checkNonce(String nonce) {
        // check response_nonce to prevent replay-attack:
        if (nonce == null || nonce.length() < 20)
            throw new OpenIdException("Verify failed.");
        long nonceTime = getNonceTime(nonce);
        long diff = System.currentTimeMillis() - nonceTime;
        if (diff < 0)
            diff = (-diff);
        if (diff > ONE_HOUR)
            throw new OpenIdException("Bad nonce time.");
        if (isNonceExist(nonce))
            throw new OpenIdException("Verify nonce failed.");
        storeNonce(nonce, nonceTime + TWO_HOUR);
    }//checkNonce


    private boolean isNonceExist(String nonce) {
        //check if nonce is exist in database:
        return false;
    }//isNonceExist


    private void storeNonce(String nonce, long expires) {
        //store nonce in database:
    }//storeNonce


    private long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .parse(nonce.substring(0, 19) + "+0000")
                    .getTime();
        } catch (ParseException e) {
            throw new OpenIdException("Bad nonce time.");
        }
    }//getNonce

}//class