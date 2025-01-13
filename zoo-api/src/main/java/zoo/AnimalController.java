package zoo;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class AnimalController {
    private static final String ALREADY_EXISTS = "There's already an animal with that name.";
    private static final String EMPTY_NAME = "Animal name must be specified.";

    private final Map<String, Animal> animals = new HashMap<>();

    public void create(Context ctx) {
        Animal animal = ctx.bodyAsClass(Animal.class);

        if (animal.name.isEmpty()) {
            ctx.status(HttpStatus.BAD_REQUEST).result(EMPTY_NAME);

            return;
        } else if (animals.containsKey(animal.name)) {
            ctx.status(HttpStatus.CONFLICT).result(ALREADY_EXISTS);

            return;
        }

        animals.put(animal.name, animal);

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

        String species = ctx.queryParam("species");
        String birthYearString = ctx.queryParam("birthYear");
        Integer newBirthYear = null;
        try {
            if (birthYearString != null)
                newBirthYear = Integer.parseInt(birthYearString);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                .result("Birth year must be an integer.");

            return;
        }

        if (species != null) {
            animal.species = species;
        }
        if (newBirthYear != null) {
            animal.birthYear = newBirthYear;
        }

        ctx.status(HttpStatus.OK);
    }

    // Delete Animal
    public void deleteOne(Context ctx) {
        String name = ctx.pathParam("name");
        if (!animals.containsKey(name)) {
            ctx.status(HttpStatus.NOT_FOUND);

            return;
        }

        animals.remove(name);
    }
}