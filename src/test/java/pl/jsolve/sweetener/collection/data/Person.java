package pl.jsolve.sweetener.collection.data;

import pl.jsolve.sweetener.collection.Collections;

import java.util.List;

public class Person {

    private String name;
    private String lastName;
    private int age;
    private Company company;
    private List<String> categoriesOfDrivingLicense = Collections.newArrayList();

    public Person() {
    }

    public Person(String name, String lastName, int age, Company company, List<String> categoriesOfDrivingLicense) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.company = company;
        this.categoriesOfDrivingLicense = categoriesOfDrivingLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getCategoryOfDrivingLicense() {
        return categoriesOfDrivingLicense;
    }

    public void setCategoryOfDrivingLicense(List<String> categoryOfDrivingLicense) {
        this.categoriesOfDrivingLicense = categoryOfDrivingLicense;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", lastName=" + lastName + "]";
    }
}
