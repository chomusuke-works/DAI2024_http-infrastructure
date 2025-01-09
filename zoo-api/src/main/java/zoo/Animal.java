package zoo;

public class Animal {
    private int id;
    private String name;
    private String species;

    // Getters and Setters
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
