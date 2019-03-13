import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class TodoMatrix {
    private HashMap<String,TodoQuarter> todoQuarters;

    public TodoMatrix() {
        // Constructs a *TodoMatrix* object with map 
        // of all possible quarters
    }

    public HashMap<String,TodoQuarter> getQuarters() {
        // Returns a private filed *todoQuarters*.
        return this.todoQuarters;
    }

    // public TodoQuarter getQuarter(String status) {
    //     // Returns a chosen *TodoQuarter* object from a
    //     // map *todoQuarters*. Status should be one of
    //     // the possible statuses ('IU', 'IN', 'NU', 'NN').
    // }

    public void addItem(String title, LocalDate deadline, boolean isImportant) {
        // Adds new item to map *todoQuarters* using adequate
        // key. You should use method *addItem* from
        // *TodoQuarter* class.
    }

    public void addItem(String title, LocalDate deadline) {
        // This method should be overloaded so as to accept
        // two parameters only. In that case, isImportant
        // should be `false` by default. 
    }

    public void addItemsFromFile(String fileName) {
        // Reads data from *fileName.csv* file and appends
        // *TodoItem* objects to attributes *todoItems* in
        // the properly *TodoQuarter* objects.
        // Every item is written in a separate line the
        // following format:

        // `title|day-month|is_important`

        // If *isImportant* is equal to false then last
        // element is an empty string. Otherwise the last
        // element is an arbitrary string.
        // If the last element of line is an empty string,
        // *isImportant* is equal to false - it means that
        // the item should be assigned to a not important
        // TODO quarter. Otherwise item should be assign to
        // an important TODO quarter.
    }

    public void saveItemsToFile(String fileName) {
        // Writes all details about TODO items to *fileName.csv*
        // file
        // Every item is written in a separate line the
        // following format:

        // `title|day-month|is_important`

        // If *isImportant* contains false then the last element
        // of line should be an empty string. Otherwise last
        // element is an arbitrary string.
    }

    public void archiveItems() {
        // Removes all *TodoItem* objects with a parameter *isDone*
        // set to *true* from list *todoItems* in every element of
        // dictionary *todoQuarters*
    }

    public String toString() {
        String string = "";
        // Returns a todoQuarters list (an Eisenhower todoMatrix)
        // formatted to string.
        return string;
    }
}