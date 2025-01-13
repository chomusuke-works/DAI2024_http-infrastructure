import io.javalin.Javalin;
import zoo.*;

public class Main {
    private static final int PORT = 25565;

    public static void main(String[] args) {
        Javalin app = Javalin.create(); // Start the server on port 7000

        AnimalController animals = new AnimalController(); // Instance of ZooApi

        // Routes
        app.post("/api/animals", animals::create);
        app.get("/api/animals/{name}", animals::getOne);
        app.get("/api/animals", animals::getAll);
        app.put("/api/animals/{name}", animals::update);
        app.delete("/api/animals/{name}", animals::deleteOne);

        app.start(PORT);
    }
}