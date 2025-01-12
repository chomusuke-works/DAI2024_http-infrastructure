package zoo;

public class Animal {
    private String name;
    private String species;
    private int birthYear;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String toString() { return String.format("%s is a %s born in the year %d.", name, species, birthYear); }
}
