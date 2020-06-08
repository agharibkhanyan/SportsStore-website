import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.s2020iae.project4.Product;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "ProductsServlet", urlPatterns = {"/products"})
public class AllProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        String jsonResponse = target.path("v1").path("api").path("products")
                              .request()
                              .accept(MediaType.APPLICATION_JSON)
                              .get(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Product> listProduct = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<Product>>(){});
        request.setAttribute("data", listProduct);

        RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
        rd.include(request, response);
    }
}
