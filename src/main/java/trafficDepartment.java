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

          staticFiles.location("/public"); // Static files

            port(getHerokuAssignedPort());
        get("/", (request, response) -> {

            response.redirect("/booking");

            return "";
        });
            get("/booking", (req, res) -> {

               Map<String, String> dataMap = new HashMap<>();

                return new ModelAndView(dataMap, "booking.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/availableSlots", (req, res) -> {

                Map<String, String> dataMap = new HashMap<>();

                return new ModelAndView(dataMap, "availableSlots.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/registration", (req, res) -> {

                Map<String, String> dataMap = new HashMap<>();

                return new ModelAndView(dataMap, "registration.handlebars");
            }, new HandlebarsTemplateEngine());


            post("/booking", (req, res) -> {

                // get form data values
                String learnersLicence = req.queryParams("LearnersLicence");
                String driversLicence = req.queryParams("DriversLicence");
                String codeType = req.queryParams("CodeType");
           //     String codeA1 = req.queryParams("Code");
//                String codeB = req.queryParams("CodeB");
//                String codeC1 = req.queryParams("CodeC1");
                String location =req.queryParams("Location");
                Map<String, String> dataMap = new HashMap<>();

                if (learnersLicence == null) {
                    dataMap.put("error", "Please select a type!");
                } else if  (driversLicence == null) {
                    dataMap.put("error", "please select a type!");
                } else if ( codeType == null){
                    dataMap.put("error", "Please select a Code type!");
             }else if (location == null){
                    dataMap.put("error", "Please select a Location!");
                }else {
                    dataMap.put("error", "There is nothing selected!");

                }

                return new ModelAndView(dataMap, "booking.handlebars");

            }, new HandlebarsTemplateEngine());
            post("/availableSlots", (req, res) -> {

                // get form data values
                String date = req.queryParams("date");

                Map<String, String> dataMap = new HashMap<>();

                if (date == null) {
                    dataMap.put("error", "please select a Date!");
                } else {
                    dataMap.put("error","There is no date selected");
                }
                return new ModelAndView(dataMap, "availableSlots.handlebars");

            }, new HandlebarsTemplateEngine());

            post("/registration", (req, res) -> {

                // get form data values
                String firstName = req.queryParams("FirstName");
                String lastName = req.queryParams("LastName");
                String identityNumber = req.queryParams("ID");
                String cellphoneNumber =req.queryParams("CellPhone");
                String address =req.queryParams("address");
                String email = req.queryParams("email");

                Map<String, String> dataMap = new HashMap<>();

                if (firstName == null) {
                    dataMap.put("error", "There is no Name entered!!");
                } else if(lastName == null) {
                    dataMap.put("error","There is no Name entered!");
                } else if (identityNumber == null){
                    dataMap.put("error","There is no ID entered!");
                } else if(cellphoneNumber == null){
                    dataMap.put("error","There is no Cellphone Number entered!");
                }else if(address == null){
                    dataMap.put("error","There is no Address entered!");
                }else if (email == null){
                    dataMap.put("error","There is no Email Address entered!");
                }else {
                    dataMap.put("error","There is Nothing entered!");
                }
                return new ModelAndView(dataMap, "registration.handlebars");

            }, new HandlebarsTemplateEngine());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
