import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2020iae.restservice.model.Tax;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author chuon
 */
@WebServlet(name = "StateTaxServlet", urlPatterns = {"/tax"})
public class StateTaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String zip = request.getParameter("zip");
        response.setContentType("text/html;charset=UTF-8");
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        Response verify = target.path("v1").path("api").path("tax").path(zip)
                              .request()
                              .get();

        if(verify.getStatus() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = verify.readEntity(String.class);
            Tax tax = objectMapper.readValue(jsonResponse, Tax.class);
            try (PrintWriter out = response.getWriter()) {
                out.println(tax.getRate());
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println(0.05);
            }
        }
    }
}
