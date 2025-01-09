import io.javalin.Javalin;
import zoo.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000); // Start the server on port 7000

        ZooApi zooApi = new ZooApi(); // Instance of ZooApi

        // Routes
        app.post("/animals", zooApi::createAnimal);
        app.get("/animals", zooApi::getAllAnimals);
        app.get("/animals/{id}", zooApi::getAnimalById);
        app.put("/animals/{id}", zooApi::updateAnimal);
        app.delete("/animals/{id}", zooApi::deleteAnimal);
    }
}