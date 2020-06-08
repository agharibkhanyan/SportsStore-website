import java.io.IOException;
import java.net.URI;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@WebServlet(urlPatterns = {"/cancelOrder"})
public class DeleteOrderServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderID = -1;
        if (request.getParameter("id") != null)
            orderID = Integer.parseInt(request.getParameter("id"));
        String sOrderID = String.valueOf(orderID);
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        Response delete = target.path("v1").path("api").path("orders").path(sOrderID).request().delete();
        if(delete.getStatus() == 200) {
            request.setAttribute("isDeleted", "yes");
            request.setAttribute("orderId", sOrderID);
            target.path("v1").path("api").path("items").path(sOrderID).request().delete();
        } else {
            request.setAttribute("isDeleted", "no");
            request.setAttribute("orderId", sOrderID);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/delete.jsp");
        rd.include(request, response);  
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/delete.jsp");
        rd.include(request, response);
    }
}



