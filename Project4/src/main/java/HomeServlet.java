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
import java.util.List;
import javax.servlet.http.HttpSession;
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
public class HomeServlet extends HttpServlet {
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
        ArrayList<Product> listProduct = new ArrayList<Product>();
        List<Product> sourceProduct = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>(){});
        for(Product rs : sourceProduct) {
            if(rs.getId() <= 5) {
                listProduct.add(new Product(rs.getId(), rs.getName(), rs.getSummary(), rs.getThumbnail(), rs.getCategory(), rs.getDetail(), rs.getPrice()));
            }
        }
        request.setAttribute("data", listProduct);

        HttpSession session = request.getSession();
        if(null != session.getAttribute("tracking")) { // Create new if not exist
            ArrayList<Product> trackList = (ArrayList<Product>)(session.getAttribute("tracking"));
            request.setAttribute("trackData", trackList);
            request.setAttribute("historyTitle", "BROWSER HISTORY");
        } else {
            ArrayList<Product> topSell = new ArrayList<Product>();
            for(Product rs : sourceProduct) {
                if(rs.getId() > 5 && rs.getId() <= 10) {
                    topSell.add(new Product(rs.getId(), rs.getName(), rs.getSummary(), rs.getThumbnail(), rs.getCategory(), rs.getDetail(), rs.getPrice()));
                }
            }
            request.setAttribute("trackData", topSell);
            request.setAttribute("historyTitle", "TOP SELL");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.include(request, response);
    }
}
