import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
import static sun.plugin.security.JDK11ClassFileTransformer.init;
public class App {
    public static void main(String[] args){
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFiles.location("/public"); // Static files
        get("/", (req, res) -> {
            Map map = new HashMap();
            map.put("name", "Joe");
            return new ModelAndView(map, "index.handlebars");
        }, new HandlebarsTemplateEngine());

        post("/search", (request, response) -> {
            return "traffic: " + request.queryParams("location") + " " + request.queryParams("bookingtype") + " " + request.queryParams("code") ;
        });
    }
}