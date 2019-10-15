package router;

import contorllers.PlanController;
import exceptions.ApiException;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

import javax.servlet.http.HttpServletResponse;

public class Router implements SparkApplication {


    public void init() {

        exceptionHandler();

        Spark.post("/generate-plan", PlanController::post);


        Spark.get("/*", Router::notFound);
        Spark.put("/*", Router::notFound);
        Spark.post("/*", Router::notFound);
        Spark.patch("/*", Router::notFound);
        Spark.options("/*", Router::notFound);

    }

    private static String notFound(Request request, Response response) {

        response.status(HttpServletResponse.SC_NOT_FOUND);

        throw new ApiException(HttpServletResponse.SC_NOT_FOUND, String.format("Route %s not found", request.uri()));
    }

    private void exceptionHandler() {
        Spark.notFound(Router::notFound);

        Spark.exception(ApiException.class, Router::apiExceptionHandler);

        // Catch any other exception and wrap this in ApiException
        Spark.exception(Exception.class, Router::unknownExceptionHandler);
    }

    private static void apiExceptionHandler(ApiException e, Request request, Response response) {

        response.status(e.getStatus());

        response.body(e.toString());

        setHeaders(response);
    }


    private static void unknownExceptionHandler(Exception e, Request request, Response response) {
        ApiException apiException = new ApiException(500, "internal server error");

        e.printStackTrace();

        apiExceptionHandler(apiException, request, response);
    }

    private static void setHeaders(Response response) {

        response.header("Content-Type", "application/json");
        response.header("Vary", "Accept,Accept-Encoding");
        response.header("Cache-Control", "max-age=0");
    }
}
