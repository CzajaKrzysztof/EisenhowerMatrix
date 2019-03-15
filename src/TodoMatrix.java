import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoMatrix {
    private HashMap<String,TodoQuarter> todoQuarters;

    public TodoMatrix() {
        // Constructs a *TodoMatrix* object with map 
        // of all possible quarters
        this.todoQuarters = new HashMap<String,TodoQuarter>();
        this.todoQuarters.put("IU", new TodoQuarter());
        this.todoQuarters.put("IN", new TodoQuarter());
        this.todoQuarters.put("NU", new TodoQuarter());
        this.todoQuarters.put("NN", new TodoQuarter());
    }

    public Map<String,TodoQuarter> getQuarters() {
        // Returns a private filed *todoQuarters*.
        return this.todoQuarters;
    }

    public TodoQuarter getQuarter(String status) {
        // Returns a chosen *TodoQuarter* object from a
        // map *todoQuarters*. Status should be one of
        // the possible statuses ('IU', 'IN', 'NU', 'NN').
        return todoQuarters.get(status);
    }

    public void addItem(String title, LocalDate deadLine, boolean isImportant) throws IllegalArgumentException {
        // Adds new item to map *todoQuarters* using adequate
        // key. You should use method *addItem* from
        // *TodoQuarter* class.
        int diffInDays = TodoMatrix.converDateToDays(deadLine) - TodoMatrix.converDateToDays(LocalDate.now());
        if (diffInDays < 0) {
            throw new IllegalArgumentException();
        }
        if (diffInDays <= 3 && isImportant) {
            todoQuarters.get("IU").addItem(title, deadLine);
        } else if (diffInDays > 3 && isImportant) {
            todoQuarters.get("IN").addItem(title, deadLine);
        } else if (diffInDays <= 3 && !isImportant) {
            todoQuarters.get("NU").addItem(title, deadLine);
        } else if (diffInDays > 3 && !isImportant) {
            todoQuarters.get("NN").addItem(title, deadLine);
        }
    }

    public void addItem(String title, LocalDate deadLine) {
        // This method should be overloaded so as to accept
        // two parameters only. In that case, isImportant
        // should be `false` by default.
        this.addItem(title, deadLine, false);
    }

    public void addItemsFromFile(String fileName) throws IOException{
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
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while((line = reader.readLine()) != null) {
                String[] entry = line.split("\\|");
                if ( entry.length != 0){
                    LocalDate deadLine = TodoMatrix.convertToLocalDate(entry[1]);
                    if (entry.length > 2) {
                        boolean isImportant = true;
                        this.addItem(entry[0], deadLine, isImportant);
                    } else {
                        this.addItem(entry[0], deadLine);;
                    }
                }
            }
        }
    }

    public void saveItemsToFile(String fileName) throws IOException{
        // Writes all details about TODO items to *fileName.csv*
        // file
        // Every item is written in a separate line the
        // following format:

        // `title|day-month|is_important`

        // If *isImportant* contains false then the last element
        // of line should be an empty string. Otherwise last
        // element is an arbitrary string.
        List<String> allTodoItems = new ArrayList<String>();
        allTodoItems.addAll(this.addStringsToSave(this.getQuarter("IU").getItems(), true));
        allTodoItems.addAll(this.addStringsToSave(this.getQuarter("IN").getItems(), true));
        allTodoItems.addAll(this.addStringsToSave(this.getQuarter("NU").getItems(), false));
        allTodoItems.addAll(this.addStringsToSave(this.getQuarter("NN").getItems(), false));
        this.writeToFile(fileName, allTodoItems);

    }

    public void archiveItems() {
        // Removes all *TodoItem* objects with a parameter *isDone*
        // set to *true* from list *todoItems* in every element of
        // dictionary *todoQuarters*
        if (!todoQuarters.isEmpty()) {
            todoQuarters.get("IU").archiveItems();
            todoQuarters.get("IN").archiveItems();
            todoQuarters.get("NU").archiveItems();
            todoQuarters.get("NN").archiveItems();
        }
    }

    private static int converDateToDays(LocalDate localDate) {
        int dateInDays = localDate.getYear() * 365 + localDate.getDayOfYear();
        return dateInDays;
    }

    private static LocalDate convertToLocalDate(String dayMonth) {
        LocalDate date = null;
        String year = String.valueOf(LocalDate.now().getYear());
        String[] dayAndMonth = dayMonth.split("-");
        date = LocalDate.parse(year + "-" + dayAndMonth[1] + "-" + dayAndMonth[0]);
        return date;
    }

    private Collection<String> addStringsToSave(Collection<TodoItem> todoQuarter, boolean importance) {
        Collection<String> strings = new ArrayList<String>();
        for (TodoItem item: todoQuarter) {
            String title = item.getTitle();
            String day = intToString(item.getDeadline().getDayOfMonth());
            String month = intToString(item.getDeadline().getMonthValue());
            String string = title + "|" + day + "-" + month;
            if (importance) {
                string += "|important";
            }
            strings.add(string);
        }
        return strings;
    }

    private void writeToFile(String fileName, List<String> strings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String string: strings) {
                writer.write(string + "\n");
            }
        }
    }

    private String intToString(int integer) {
        String result = String.valueOf(integer);
            if (result.length() == 1) {
                result = "0" + result;
            }
        return result;
    }

    public String toString() {
        String string = "";
        System.out.println("EisenhowerMatrix:");
        System.out.println("IMPORTANT URGENT (IU):");
        System.out.println(this.todoQuarters.get("IU").toString());
        System.out.println("IMPORTANT NOT URGENT (IN):");
        System.out.println(this.todoQuarters.get("IN").toString());
        System.out.println("NOT IMPORTANT URGENT (NU):");
        System.out.println(this.todoQuarters.get("NU").toString());
        System.out.println("NOT IMPORTANT NOT URGENT (NN):");
        System.out.println(this.todoQuarters.get("NN").toString());
        return string;
    }
}