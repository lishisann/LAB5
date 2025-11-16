package LAB5;

public class PersonNumber {

    private String name;
    private Integer number;

    public PersonNumber(String name, Integer number) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = capitalize(name);
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    private String capitalize(String name) {    // делает только первую букву заглавной
        name = name.toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    @Override
    public String toString() {
        return name + ":" + (number != null ? number : "нет номера");
    }
}
