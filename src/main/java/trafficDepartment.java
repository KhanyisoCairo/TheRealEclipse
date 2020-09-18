import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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


    static Jdbi getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        String local = processBuilder.environment().get("LOCAL");
        if (local == null && database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);

        } else if (local != null && database_url != null) {
            return Jdbi.create(database_url);
        }

        return Jdbi.create(defualtJdbcUrl);

    }

    public static void main(String[] args) {

        try {

           // Connection connection = getDatabaseConnection("jdbc:postgresql://localhost/greeter");
            Jdbi jdbi = getDatabaseConnection("jdbc:postgresql://localhost/trafficDepartment?user=khanyiso&password=cairo123");
            
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


            post("/book", (req, res) -> {
                String bookingId = req.queryParams("bookingId");

                Booking theBooking = jdbi.withHandle(h -> {
                    h.execute("update booking set booked = true where id = ?", Integer.parseInt(bookingId));

                    Booking booking = h.createQuery(
                            "select * from booking where id = ? ")
                            .bind(0, Integer.parseInt(bookingId))
                            .mapToBean(Booking.class)
                            .findOnly();

                    return booking;

                });

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("booking", theBooking);

                return new ModelAndView(dataMap, "thankyou.handlebars");

            }, new HandlebarsTemplateEngine());

            post("/booking", (req, res) -> {

                // get form data values
                String licenceType = req.queryParams("licenceType");
                String location = req.queryParams("location");
                String codeType = req.queryParams("code");

                List<Booking> bookings = jdbi.withHandle( h -> {

                    if (licenceType == null) {
                        return new ArrayList<>();
                    }

                    if (!licenceType.equals("")
                            && codeType.equals("")
                            && location.equals("")) {
                        return h.createQuery(
                                "select * from booking where booked = false "  +
                                        " and license_type = ?")
                                .bind(0, licenceType)
                                .mapToBean(Booking.class)
                                .list();
                    }


                    List<Booking> bookingList = h.createQuery(
                            "select * from booking where booked = false and department = ? " +
                                    "and license_code = ? and license_type = ?")
                            .bind(0, location)
                            .bind(1, codeType)
                            .bind(2, licenceType)
                            .mapToBean(Booking.class)
                            .list();

                    return bookingList;

                });

                Map<String, Object> dataMap = new HashMap<>();

                dataMap.put("bookings", bookings);
                System.out.println(bookings.size() + " ------");
                if (bookings.size()<1){
                    dataMap.put("canShow", new Object());
                    dataMap.put("bookings", new Object());
                }

                System.out.println(dataMap.containsKey("canShow"));

                return new ModelAndView(dataMap, "booking.handlebars");

            }, new HandlebarsTemplateEngine());


            post("/availableSlots", (req, res) -> {

                // get form data values
                String date = req.queryParams("date");

                Map<String, String> dataMap = new HashMap<>();

                if (date == null) {
                    dataMap.put("error", "please select a Date!");
                } else {
                    dataMap.put("error", "There is no date selected");
                }
                return new ModelAndView(dataMap, "availableSlots.handlebars");

            }, new HandlebarsTemplateEngine());


            post("/registration", (req, res) -> {

                // get form data values
                String firstName = req.queryParams("FirstName");
                String lastName = req.queryParams("LastName");
                String identityNumber = req.queryParams("ID");
                String cellphoneNumber = req.queryParams("CellPhone");
                String address = req.queryParams("address");
                String email = req.queryParams("email");

                Map<String, String> dataMap = new HashMap<>();

                if (firstName == null) {
                    dataMap.put("error", "There is no Name entered!!");
                } else if (lastName == null) {
                    dataMap.put("error", "There is no Name entered!");
                } else if (identityNumber == null) {
                    dataMap.put("error", "There is no ID entered!");
                } else if (cellphoneNumber == null) {
                    dataMap.put("error", "There is no Cellphone Number entered!");
                } else if (address == null) {
                    dataMap.put("error", "There is no Address entered!");
                } else if (email == null) {
                    dataMap.put("error", "There is no Email Address entered!");
                } else {
                    dataMap.put("error", "There is Nothing entered!");
                }
                return new ModelAndView(dataMap, "registration.handlebars");

            }, new HandlebarsTemplateEngine());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
