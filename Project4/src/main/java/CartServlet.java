import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2020iae.project4.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author chuon
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        if(id != null) {
            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
            String jsonResponse = target.path("v1").path("api").path("products").path(id)
                                  .request()
                                  .accept(MediaType.APPLICATION_JSON)
                                  .get(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Product pd = objectMapper.readValue(jsonResponse, Product.class);
            // Begin Store to session
            ArrayList<Product> cartList;
            HttpSession session = request.getSession(false);
            if(null == session.getAttribute("cartItems")){ // new sesstion initial
                session = request.getSession();
                cartList = new ArrayList<Product>();
            } else { // restore from previous sesstion
                cartList = (ArrayList<Product>)session.getAttribute("cartItems");
            }
            cartList.add(pd);
            session.setAttribute("cartItems", cartList);// save session
            String site = new String("./cart");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } else {
            HttpSession session = request.getSession();
            double subTotal = 0.00;
            int numOfItems = 0;
            if(null != session.getAttribute("cartItems")) {
                ArrayList<Product> cartList = (ArrayList<Product>)(session.getAttribute("cartItems"));
                for (Product p: cartList) {
                    subTotal += p.getPrice();
                }
                request.setAttribute("cartData", cartList);
                request.setAttribute("isEmpty", "no");
                numOfItems = cartList.size();
            } else {
                request.setAttribute("isEmpty", "yes");
            }
            subTotal = Math.round(subTotal*100.0)/100.0;
            request.setAttribute("subTotal", String.format("%.2f", subTotal));
            request.setAttribute("numOfItems",numOfItems);
            RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
            rd.include(request, response);
        }

    }
}
