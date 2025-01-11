package zoo;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class ZooApi {

    private final Map<String, Animal> animals = new HashMap<>();
    private final String ALREADY_EXISTS = "Animal already in zoo";
    private final String NOT_FOUND = "Animal not found";
    private final String NOT_SPECIFIED = "Animal name must be specified";

    // Create Animal
    public void createAnimal(Context ctx) {
        Animal animal = ctx.bodyAsClass(Animal.class); // Parse JSON body to Animal object
        if (animal.getName() == null || animal.getName().isEmpty()) {
            ctx.status(HttpStatus.BAD_REQUEST).result(NOT_SPECIFIED);
            return;
        }
        if (animals.get(animal.getName()) != null) {  // Animal already exists
            ctx.status(HttpStatus.CONFLICT).result(ALREADY_EXISTS);
            return;
        }
        animals.put(animal.getName(), animal);
        ctx.status(HttpStatus.CREATED).json(animal);
    }

    // Read All Animals
    private void getAllAnimals(Context ctx) {
        ctx.status(HttpStatus.OK).json(new ArrayList<>(animals.values()));
    }

    // Read Single Animal by name
    public void getAnimalByName(Context ctx) {
        try {
            String name = Objects.requireNonNull(ctx.queryParam("name"));
            Animal animal = animals.get(name);
            if (animal == null) {
                ctx.status(HttpStatus.NOT_FOUND).result(NOT_FOUND);
                return;
            }
            ctx.json(animal);
        } catch (NullPointerException e) {
            getAllAnimals(ctx);
        }
    }

    // Update Animal
    public void updateAnimal(Context ctx) {
        try {
            String name = Objects.requireNonNull(ctx.queryParam("name"));
            Animal animal = animals.get(name);
            if (animal == null) {
                ctx.status(HttpStatus.NOT_FOUND).result(NOT_FOUND);
            } else {
                // Create new animal with provided fields and keep old fields for unprovided ones
                Animal updatedAnimal = ctx.bodyAsClass(Animal.class);
                if (updatedAnimal.getName() == null) {
                    updatedAnimal.setName(name);
                } else if (animals.get(updatedAnimal.getName()) != null) {
                    ctx.status(HttpStatus.CONFLICT).result(ALREADY_EXISTS);
                    return;
                }

                if (updatedAnimal.getSpecies() == null) {
                    updatedAnimal.setSpecies(animal.getSpecies());
                }

                if (updatedAnimal.getBirthDate() == 0) {
                    updatedAnimal.setBirthDate(animal.getBirthDate());
                }

                animals.put(name, updatedAnimal);
                ctx.status(HttpStatus.CREATED).json(updatedAnimal);
            }
        } catch (NullPointerException e) {
            ctx.status(HttpStatus.BAD_REQUEST).result(NOT_SPECIFIED);
        }
    }

    // Delete all Animals
    private void clearZoo(Context ctx) {
        animals.clear();
        ctx.status(HttpStatus.OK);
    }

    // Delete Animal
    public void deleteAnimal(Context ctx) {
        try {
            String name = Objects.requireNonNull(ctx.queryParam("name"));
            Animal animal = animals.remove(name);
            if (animal != null) {
                ctx.status(HttpStatus.NO_CONTENT);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result(NOT_FOUND);
            }

        } catch (NullPointerException e) {
            clearZoo(ctx);
            ctx.status(HttpStatus.OK);
        }
    }
}