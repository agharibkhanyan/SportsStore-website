import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2020iae.project4.Order;
import com.s2020iae.project4.Item;
import com.s2020iae.project4.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author chuon
 */
@WebServlet(urlPatterns = {"/confirmation"})
public class NewConfirmServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderID = -1;
        if (request.getParameter("id") == null)
            orderID = (int) request.getAttribute("orderID");
        else
            orderID = Integer.parseInt(request.getParameter("id"));
        
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        String orderIDString = String.valueOf(orderID);
        Response verify = target.path("v1").path("api").path("orders").path(orderIDString)
                              .request()
                              .get();

        if(verify.getStatus() == 200) {
            Order anOrder = verify.readEntity(Order.class);
            request.setAttribute("OrderObject", anOrder);
            request.setAttribute("isExist", "yes");
            
            String jsonResponse = target.path("v1").path("api").path("items").path(orderIDString)
                              .request()
                              .accept(MediaType.APPLICATION_JSON)
                              .get(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Item> items = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<Item>>(){});
            ArrayList<Product> OrderItems = new ArrayList<Product>();
            String pid = null;
            for(Item i: items) {
                pid = Integer.toString(i.getProductId());
                Product oneProduct = target.path("v1").path("api").path("products").path(pid)
                        .request()
                        .accept(MediaType.APPLICATION_JSON)
                        .get(Product.class);
                OrderItems.add(oneProduct);
            }
            
            request.setAttribute("OrderItems", OrderItems);
        } else {
            request.setAttribute("isExist", "no");
        }
        
        request.setAttribute("orderID", orderIDString);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/confirmation.jsp");
        rd.include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/confirmation.jsp");
        rd.include(request, response);
    }
}
