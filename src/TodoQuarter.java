public class TodoQuarter {
    private List<TodoItem> todoItems;
    
    public TodoQuarter() {
        // Constructs a *TodoQuarter* object with list of
        // TodoItem objects
    }

    public void addItem(String title, LocalDate deadline) {
        // Append *TodoItem* object to *todoItems* sorted 
        // decreasing by *deadline*.
    }

    public void removeItem(int index) {
        // Removes *TodoItem* object using *index* of
        // list *todoItems*
    }

    public void archiveItems() {
        // Removes all *TodoItem* objects with a parameter
        // *isDone* set to *true* from list *todoItems*.
    }

    public TodoItem getItem(int index) {
        // Returns *TodoItem* object from *index* of list
        // *todoItems*.
    }

    public List getItems() {
        // Returns private field *todoItems*.
    }

    public String toString() {
        // Returns a formatted list of *todoItems* sorted 
        // decreasing by *deadline*. There is an expecting
        // output:

        // ```1. [ ] 9-6  go to the doctor
        // 2. [x] 11-6 submit assignment``
    }

}