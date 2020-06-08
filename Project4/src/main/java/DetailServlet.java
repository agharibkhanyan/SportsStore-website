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
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        String jsonResponse = target.path("v1").path("api").path("products").path(id)
                              .request()
                              .accept(MediaType.APPLICATION_JSON)
                              .get(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Product pd = objectMapper.readValue(jsonResponse, Product.class);

        ArrayList<Product> trackList;
        HttpSession session = request.getSession(false);
        if(null == session.getAttribute("tracking")){ // new sesstion initial
            session = request.getSession(true);
            trackList = new ArrayList<Product>();
        } else {
            trackList = (ArrayList<Product>)session.getAttribute("tracking");
            if(trackList.size() > 4) {
                trackList.remove(0);
            }
        }
        trackList.add(pd);
        session.setAttribute("tracking", trackList);// save session

        request.setAttribute("data", pd);
        RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
        rd.include(request, response);
    }
}
