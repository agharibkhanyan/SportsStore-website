import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2020iae.restservice.model.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "StateServlet", urlPatterns = {"/state"})
public class StateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String state = request.getParameter("state");
        response.setContentType("text/html;charset=UTF-8");
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/SportStoreRestService/").build());
        Response verify = target.path("v1").path("api").path("states").path(state)
                              .request()
                              .get();
        
        if(verify.getStatus() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = verify.readEntity(String.class);
            ArrayList<State> listState = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<State>>(){});
            try (PrintWriter out = response.getWriter()) {
                out.println("<ul>");
                for(State rs : listState) {
                    out.println("<li>" + rs.getName() + "</li>");
                }
                out.println("</ul>");
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<ul>");
                out.println("<li>Not Found</li>");
                out.println("</ul>");
            }
        }
    }
}
