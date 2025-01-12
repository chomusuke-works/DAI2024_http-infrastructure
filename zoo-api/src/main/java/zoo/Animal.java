package zoo;

public class Animal {
    private String name;
    private String species;
    private int birthDate;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getBirthDate() { return birthDate; }

    public void setBirthDate(int birthDate) { this.birthDate = birthDate; }

    public String toString() { return String.format("%s is a %s born in the year %d.", name, species, birthDate); }
}
