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
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        // Returns a formatted string with details about todoItem.
        // Format of deadline is 'day-month'

        // Expecting output for example done item:

        // `[x] 12-6 submit assignment`

        // Expecting output for example undone item:

        // `[ ] 28-6 submit assignment`
        String color = RESET;
        int diffInDays = TodoMatrix.converDateToDays(this.deadline) - TodoMatrix.converDateToDays(LocalDate.now());
        if (diffInDays == 0) {
            color = RED;
        } else if (0 < diffInDays && diffInDays <= 3) {
            color = YELLOW;
        } else if ( diffInDays > 3) {
            color = GREEN;
        }
        String marked = (this.isDone) ? "x" : " ";
        int day = this.getDeadline().getDayOfMonth();
        int month = this.getDeadline().getMonthValue();
        String title = this.getTitle();
        String format = color + "[%s] %d-%d %s" + RESET;
        return String.format(format, marked, day, month, title);
    }

    public int compareTo(TodoItem item) { 
        return this.getDeadline().compareTo(item.getDeadline()); 
    }
}