package sample;

public class StudentMark {
    private String name;
    private String surname;
    private int mark;

    public StudentMark(String name, String surname, int mark) {
        this.name = name;
        this.surname = surname;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getMark() {
        return mark;
    }
}