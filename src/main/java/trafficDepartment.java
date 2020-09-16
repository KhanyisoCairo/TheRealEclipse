import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class trafficDepartment {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    static Connection getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return DriverManager.getConnection(url, username, password);

        }

        return DriverManager.getConnection(defualtJdbcUrl);

    }
    public static void main(String[] args) {

        try {
           // Connection connection = getDatabaseConnection("jdbc:postgresql://localhost/greeter");
            Connection connection = getDatabaseConnection("jdbc:postgresql://localhost/greeter?user=khanyiso&password=cairo123");
//            trafficDepartment greeter = new GreeterJDBC(connection);
//            Api api = new Api(greeter);

         //   staticFiles.location("/public"); // Static files

            port(getHerokuAssignedPort());

   //         get("/", (req, res) -> {
//
    //           Map<String, String> dataMap = new HashMap<>();
        //      dataMap.put("registration", trafficDepartment.getDatabaseConnection(String));
//
//                return new ModelAndView(dataMap, "index.hbs");
//            }, new HandlebarsTemplateEngine());

            post("/registration", (req, res) -> {

                // get form data values
                String firstName = req.queryParams("first_Name");
                String Lastname = req.queryParams("Last_Name");
                String email = req.queryParams("email");
                Map<String, String> dataMap = new HashMap<>();

                if (firstName == null) {
                    dataMap.put("error", "There is no Name entered!");
                } else if  (Lastname == null) {
                    dataMap.put("error", "There is no Name entered!");
                } else if (email == null){
                    dataMap.put("error", "There is no email entered!");
             }
//                else {
//                    String greeting = greeter.greet(name, language);
//                    // put the values from the form for Handlebars to use
//                    dataMap.put("counter", greeter.getCount().toString());
//                    dataMap.put("greeting", greeting);
//                }




                //
                return new ModelAndView(dataMap, "registration.hbs");

            }, new HandlebarsTemplateEngine());

//            get("/greeted", (req, res) -> {
//                Map<String, Object> dataMap = new HashMap<>();
//                dataMap.put("greeted_people", greeter.greetedPeople());
//                return new ModelAndView(dataMap, "greeted.hbs");
//            }, new HandlebarsTemplateEngine());

//            get("/api/greeted", api.greetedUsers(), new JsonTransformer());
//            post("/api/greet", api.greetUser(), new JsonTransformer());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
