package pl.jsolve.sweetener.tests.stub.person;

public class City {

    private String name;
    private long population;

    public City() {
    }

    public City(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
