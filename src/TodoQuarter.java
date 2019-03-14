import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TodoQuarter{

    private List<TodoItem> todoItems;
    
    public TodoQuarter() {
        // Constructs a *TodoQuarter* object with list of
        // TodoItem objects
        this.todoItems = new ArrayList<TodoItem>();
    }

    public void addItem(String title, LocalDate deadline) {
        // Append *TodoItem* object to *todoItems* sorted 
        // decreasing by *deadline*.
        this.todoItems.add(new TodoItem(title, deadline));
        Collections.sort(this.todoItems);
    }

    public void removeItem(int index) {
        // Removes *TodoItem* object using *index* of
        // list *todoItems*
        this.todoItems.remove(index);
    }

    public void archiveItems() {
        // Removes all *TodoItem* objects with a parameter
        // *isDone* set to *true* from list *todoItems*.
        for (int i = 0; i < this.todoItems.size(); i++) {
            if (this.todoItems.get(i).isDone()) {
                this.todoItems.remove(i);
            }
        }
    }

    public TodoItem getItem(int index) {
        // Returns *TodoItem* object from *index* of list
        // *todoItems*.
        return this.getItems().get(index);
    }

    public List<TodoItem> getItems() {
        // Returns private field *todoItems*.
        return this.todoItems;
    }

    public String toString() {
        // Returns a formatted list of *todoItems* sorted 
        // decreasing by *deadline*. There is an expecting
        // output:

        // ```1. [ ] 9-6  go to the doctor
        // 2. [x] 11-6 submit assignment``
        String string = "";
        int i = 1;
        for (TodoItem item: this.todoItems) {
            string += i + ". " + item.toString() + "\n";
            i++;
        }
        return string;
    }

}