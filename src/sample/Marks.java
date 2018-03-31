package sample;

public class Marks {
    private int id;
    private int student_id;
    private String title;
    private int mark;
    private int time_stamp;

    public Marks(int id, int student_id, String title, int mark, int time_stamp) {
        this.id = id;
        this.student_id = student_id;
        this.title = title;
        this.mark = mark;
        this.time_stamp = time_stamp;
    }

    public int getID() {
        return id;
    }
    public int getStudent_id() {
        return student_id;
    }
    public String getTitle() {
        return title;
    }
    public int getMark() {
        return mark;
    }
    public int getTime_stamp() {
        return time_stamp;
    }

}
