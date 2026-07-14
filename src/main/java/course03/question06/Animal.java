package course03.question06;

public class Animal {
    private final String name;
    private final AnimalType type;
    private final int age;

    public Animal(String name, AnimalType type, int age) {
        if (name == null || name.trim().length() == 0 || type == null || age < 0) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public void printInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return name + "(" + type + ", " + age + "살)";
    }
}
