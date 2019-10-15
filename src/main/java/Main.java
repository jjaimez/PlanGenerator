import router.Router;
import spark.Spark;

public enum Main {
    ;

    public static void main(String[] args) {
        Spark.port(8080);
        new Router().init();
        Spark.awaitInitialization();


        System.out.println("Listening on http://localhost:8080/");
    }


}
