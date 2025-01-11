package zoo;

public class Animal {
    private String name;
    private String species;
    private int birthDate;

    // Getters and Setters
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

    public int getBirthDate() { return birthDate; }

    protected void setBirthDate(int birthDate) { this.birthDate = birthDate; }

    public String toString() { return name + " is a " + species + " born in " + birthDate; }
}
