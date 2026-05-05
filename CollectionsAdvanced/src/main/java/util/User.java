package util;

public class User {
    private final String passportNumber;
    private String name;
    private int age;

    public User(String passportNumber, String name, int age) {
        this.passportNumber = passportNumber;
        this.name = name;
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Passport: %s, Name: %s, Age: %d", passportNumber, name, age);
    }
}
