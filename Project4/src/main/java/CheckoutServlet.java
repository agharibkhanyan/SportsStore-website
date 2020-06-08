import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2020iae.project4.Item;
import com.s2020iae.project4.Order;
import com.s2020iae.project4.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
/**
 *
 * @author chuon
 */
@WebServlet(name = "ConfirmationServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        double subTotal = 0.00;
        int numOfItems = 0;
        if(null != session.getAttribute("cartItems")) {
            ArrayList<Product> cartList = (ArrayList<Product>)(session.getAttribute("cartItems"));
            request.setAttribute("cartData", cartList);
            for (Product p: cartList) {
                subTotal += p.getPrice();
            }
            request.setAttribute("isEmpty", "no");
            numOfItems = cartList.size();
        } else {
            request.setAttribute("isEmpty", "yes");
        }
        subTotal = Math.round(subTotal*100.0)/100.0;
        request.setAttribute("subTotal", String.format("%.2f", subTotal));
        request.setAttribute("numOfItems",numOfItems);

        RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
        rd.include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> paramMap = new HashMap<String, String>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String currParamName = paramNames.nextElement();
            String currParamValue = request.getParameter(currParamName);
            if(currParamName.equals("zip"))
                currParamValue = currParamValue.substring(0, 5);
            paramMap.put(currParamName, currParamValue);
        }

        String orderId = null;
            if (paramMap.get("billzip").isEmpty()){
                paramMap.put("billzip", paramMap.get("zip"));
                paramMap.put("billstate", paramMap.get("state"));
                paramMap.put("billaddr", paramMap.get("address"));
                paramMap.put("billcity", paramMap.get("city"));
            } else {
                String tempZip = paramMap.get("billzip").substring(0, 5);
                paramMap.remove("billzip");
                paramMap.put("billzip", tempZip);
            }
            Order insertOrder = new Order();
            insertOrder.setFirstName(paramMap.get("firstname"));
            insertOrder.setLastName(paramMap.get("lastname"));
            insertOrder.setEmail(paramMap.get("email"));
            insertOrder.setPhone(paramMap.get("phone"));
            insertOrder.setAddress(paramMap.get("address"));
            insertOrder.setCity(paramMap.get("city"));
            insertOrder.setState(paramMap.get("state"));
            insertOrder.setZip(Integer.parseInt(paramMap.get("zip")));
            insertOrder.setBillAddr(paramMap.get("billaddr"));
            insertOrder.setBillCity(paramMap.get("billcity"));
            insertOrder.setBillState(paramMap.get("billstate"));
            insertOrder.setBillZip(Integer.parseInt(paramMap.get("billzip")));
            insertOrder.setMethod(paramMap.get("method"));
            insertOrder.setCardName(paramMap.get("cardname"));
            insertOrder.setCardNumber(paramMap.get("cardnumber"));
            insertOrder.setExpMonth(Integer.parseInt(paramMap.get("expmonth")));
            insertOrder.setExpYear(Integer.parseInt(paramMap.get("expyear")));
            insertOrder.setCvv(Integer.parseInt(paramMap.get("cvv")));
            insertOrder.setPrice(paramMap.get("price"));


            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
            Response verify = target.path("v1").path("api").path("orders")
                                  .request(MediaType.APPLICATION_JSON)
                                  .post(Entity.entity(insertOrder, MediaType.APPLICATION_JSON));
            if(verify.getStatus() == 200) { //Success
                HttpSession session = request.getSession();
                // Get Order ID
                String jsonOrderId = target.path("v1").path("api").path("orders").path("last")
                                      .request()
                                      .accept(MediaType.APPLICATION_JSON)
                                      .get(String.class);
                ObjectMapper objectMapper = new ObjectMapper();
                orderId = (String)objectMapper.readValue(jsonOrderId, String.class);
                 ArrayList<Product> cartList = (ArrayList<Product>)(session.getAttribute("cartItems"));
                 for (Product p: cartList) {
                    Item insertItem = new Item();
                    insertItem.setOrderId(Integer.parseInt(orderId));
                    insertItem.setProductId(p.getId());
                    target.path("v1").path("api").path("items")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(insertItem, MediaType.APPLICATION_JSON));
                 }
                session.invalidate();
            }
            request.setAttribute("orderID", Integer.parseInt(orderId));
            RequestDispatcher rd = request.getRequestDispatcher("/confirmation");
            rd.forward(request, response);
    }
}
