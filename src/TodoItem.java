import java.time.LocalDate;

public class TodoItem implements Comparable<TodoItem>{
    private String title;
    private LocalDate deadline;
    private boolean isDone;

    public TodoItem(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        // Returns a formatted string with details about todoItem.
        // Format of deadline is 'day-month'

        // Expecting output for example done item:

        // `[x] 12-6 submit assignment`

        // Expecting output for example undone item:

        // `[ ] 28-6 submit assignment`
        String marked = (this.isDone) ? "x" : " ";
        return "[" + marked + "] " + this.getDeadline().getDayOfMonth() + "-" + this.getDeadline().getMonthValue() + " "
                + this.getTitle();
    }

    public int compareTo(TodoItem item) { 
        return this.getDeadline().compareTo(item.getDeadline()); 
    }
}