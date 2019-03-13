import java.time.LocalDate;

public class TodoItem {
    private String title;
    private LocalDate deadline;
    private boolean isDone;

    public TodoItem(String title, LocalDate deadline) {
        // Constructs a ToDoItem objec
    }

    public String getTitle() {
        // Getter for the * title * field
    }

    public LocalDate getDeadline() {
        // Getter for the * deadline * field
    }

    public void mart() {
        // Sets the object's * isDone * attribute to True
    }

    public void unmark() {
        // Sets the object's * isDone * attribute to False
    }

    public String toString() {
        // Returns a formatted string with details about todoItem.
        // Format of deadline is 'day-month'

        // Expecting output for example done item:

        // `[x] 12-6 submit assignment`

        // Expecting output for example undone item:

        // `[ ] 28-6 submit assignment`
    }
}