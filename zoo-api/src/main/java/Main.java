import io.javalin.Javalin;
import zoo.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000); // Start the server on port 7000

        ZooApi zooApi = new ZooApi(); // Instance of ZooApi

        // Routes
        app.post("/animals", zooApi::createAnimal);
        app.get("/animals", zooApi::getAnimalByName);
        app.put("/animals", zooApi::updateAnimal);
        app.delete("/animals", zooApi::deleteAnimal);
    }
}