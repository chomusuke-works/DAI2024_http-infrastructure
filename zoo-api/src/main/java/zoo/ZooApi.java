package zoo;

import io.javalin.http.Context;
import java.util.*;

public class ZooApi {

    private final Map<Integer, Animal> animals = new HashMap<>();
    private int animalCounter = 1;  // Counter of animals in the Zoo

    // Create Animal
    public void createAnimal(Context ctx) {
        Animal animal = ctx.bodyAsClass(Animal.class); // Parse JSON body to Animal object
        animal.setId(animalCounter++); // Assign an ID
        animals.put(animal.getId(), animal);
        ctx.status(201).json(animal);
    }

    // Read All Animals
    public void getAllAnimals(Context ctx) {
        ctx.json(new ArrayList<>(animals.values()));
    }

    // Read Single Animal by ID
    public void getAnimalById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Animal animal = animals.get(id);
        if (animal != null) {
            ctx.json(animal);
        } else {
            ctx.status(404).result("Animal not found");
        }
    }

    // Update Animal
    public void updateAnimal(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Animal animal = animals.get(id);
        if (animal != null) {
            Animal updatedAnimal = ctx.bodyAsClass(Animal.class);
            animals.put(id, updatedAnimal);
            ctx.json(updatedAnimal);
        } else {
            ctx.status(404).result("Animal not found");
        }
    }

    // Delete Animal
    public void deleteAnimal(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Animal animal = animals.remove(id);
        if (animal != null) {
            ctx.status(204); // No content, successfully deleted
        } else {
            ctx.status(404).result("Animal not found");
        }
    }

    // Delete Animal
    public void clearZoo(Context ctx) {
        animals.clear();
        ctx.status(204); // No content, successfully deleted
    }
}