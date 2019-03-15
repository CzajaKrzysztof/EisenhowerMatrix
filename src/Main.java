import java.io.IOException;

public class Main {
    private final static String DATAFILE = "../data/myTodoItems.csv";
    public static void runner() {
        TodoMatrix matrix = new TodoMatrix();
        loadDataFromFile(matrix);
        Ui ui = new Ui();
        String option = "99";
        clearScreen();
        while (!option.equals("7")) {
            matrix.toString();
            ui.printMenu();
            option = ui.getMenuOption();
            switch (option) {
                case "1":
                    clearScreen();
                    ui.printMenu();
                    matrix.toString();
                    break;
                case "2":
                    clearScreen();
                    ui.addNewEntry(matrix);
                    break;
                case "3":
                    clearScreen();
                    ui.markTodoItem(matrix, true);
                    break;
                case "4":
                    clearScreen();
                    ui.removeTodoItem(matrix);
                    // ask for TodoQuarter, ask for entry number
                    // remove entry
                    break;
                case "5":
                    clearScreen();
                    matrix.archiveItems();
                    break;
                case "6":
                    clearScreen();
                    matrix.archiveItems();
                    saveDataToFile(matrix);
                    break;
                case "7":
                    clearScreen();
                    matrix.archiveItems();
                    saveDataToFile(matrix);
                    System.out.println("Bye bye");
                    break;
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    private static void loadDataFromFile(TodoMatrix matrix) {
        try {
            matrix.addItemsFromFile(DATAFILE);
        }
        catch (IOException e) {
            System.out.println("Error: file not found!" + e);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: outdated entry!");
        }
    }

    private static void saveDataToFile(TodoMatrix matrix) {
        try {
            matrix.saveItemsToFile(DATAFILE);
        }
        catch (IOException e) {
            System.out.println("Error: file not found!" + e);
        }
    }
}