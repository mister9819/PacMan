package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class Controller {

    /**
     * The main entry Point2D.Double into the program.
     *
     * @param args The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        post("/setCanvasDims", ((request, response) -> {
            return gson.toJson("setCanvasDims");
        }));

        post("/init", (request, response) -> {
            String[] arguments = request.body().split("&");
            int level = parseInt(arguments[0].split("=")[1]);
            int numberOfGhosts = parseInt(arguments[1].split("=")[1]);
            return gson.toJson(dis.init(level, numberOfGhosts));
        });

        get("/update", (request, response) -> {
            return gson.toJson(dis.update());
        });

        get("/reset", (request, response) -> {
            return gson.toJson(dis.reset());
        });

        post("/keypress", (request, response) -> {
            dis.keypress(parseInt(request.body().split("=")[1]));
            return gson.toJson("update");
        });

        get("*", (request, response) -> {
            response.redirect("index.html");
            return gson.toJson("invalid path!");
        });
    }

    /**
     * Get the heroku assigned port number.
     *
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
