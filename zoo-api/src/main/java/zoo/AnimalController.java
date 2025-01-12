package zoo;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class AnimalController {

    private static final Map<String, Animal> animals = new HashMap<>();
    private static final String ALREADY_EXISTS = "There's already an animal with that name.";
    private static final String EMPTY_NAME = "Animal name must be specified.";

    public void create(Context ctx) {
        Animal animal = ctx.bodyAsClass(Animal.class);

        if (animal.getName().isEmpty()) {
            ctx.status(HttpStatus.BAD_REQUEST).result(EMPTY_NAME);

            return;
        } else if (animals.containsKey(animal.getName())) {
            ctx.status(HttpStatus.CONFLICT).result(ALREADY_EXISTS);

            return;
        }

        animals.put(animal.getName(), animal);

        ctx.status(HttpStatus.CREATED);
    }

    public void getAll(Context ctx) {
        ctx.status(HttpStatus.OK);
        ctx.json(animals.values());
    }

    public void getOne(Context ctx) {
        String name = ctx.pathParam("name");
        Animal animal = animals.get(name);

        if (animal == null) {
            ctx.status(HttpStatus.NOT_FOUND);

            return;
        }

        ctx.json(animal);
    }

    public void update(Context ctx) {
        String name = ctx.pathParam("name");
        Animal animal = animals.get(name);

        if (animal == null) {
            ctx.status(HttpStatus.NOT_FOUND);

            return;
        }

        // Create new animal with provided fields and keep old fields for unprovided ones
        Animal updatedAnimal = ctx.bodyAsClass(Animal.class);
        if (updatedAnimal.getName() == null) {
            updatedAnimal.setName(name);
        } else if (animals.containsKey(updatedAnimal.getName())) {
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
        ctx.status(HttpStatus.CREATED)
            .json(updatedAnimal);
    }

    // Delete Animal
    public void deleteOne(Context ctx) {
        try {
            String name = Objects.requireNonNull(ctx.queryParam("name"));
            Animal animal = animals.remove(name);
            if (animal != null) {
                ctx.status(HttpStatus.NO_CONTENT);
            } else {
                ctx.status(HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            ctx.status(HttpStatus.BAD_REQUEST);
        }
    }
}