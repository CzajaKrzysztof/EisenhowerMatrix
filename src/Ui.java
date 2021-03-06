import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private static Scanner scanner = new Scanner(System.in);
    private final String OPTIONS = "1234567";
    private final String YESNO = "yn";
    private final String QUARTERS = "IUINNUNN";
    
    public void printMenu() {
        String menu = "MENU OPTIONS:\n" +
                      "1. Show Eisenhower matrix\n" +
                      "2. Add entry \n" +
                      "3. Mark/unmark entry\n" +
                      "4. Remove chosen entry\n" +
                      "5. Remove all marked entries\n" +
                      "6. Save entries to file\n" +
                      "7. Quit\n\n";
        System.out.println(menu);
    }

    public String getMenuOption() {
        String input = "";
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print("Chose menu option: ");
            input = getInput();
            if (validateString(input, OPTIONS)) {
                isAnswerCorrect = true;
            } else {
                System.out.println("Invalid option.\n");
            }
        }
        return input;
    }

    public void addNewEntry(TodoMatrix matrix) {
        String title = this.getTitle();
        int month = this.getNumber("Enter month", 1, 12);
        int day = this.getNumber("Enter day", 1, 31);
        boolean important = this.getImportance();
        matrix.addItem(title, LocalDate.of(LocalDate.now().getYear(), month, day), important);
    }

    public void markTodoItem(TodoMatrix matrix, boolean mark) {
        System.out.println(matrix.toString());
        TodoItem todoItem= getTodoItem(matrix);
        if (todoItem.isDone()) {
            todoItem.unmark();
        } else {
            todoItem.mark();
        }
    }

    public void removeTodoItem(TodoMatrix matrix) {
        System.out.println(matrix.toString());
        String quarterStatus = getQuarter();
        TodoQuarter quarter = matrix.getQuarter(quarterStatus);
        System.out.println(quarter.toString());
        int entryIndex = getNumber("Chose entry number", 0, quarter.getItems().size()) - 1;
        quarter.removeItem(entryIndex);
    }

    private TodoItem getTodoItem(TodoMatrix matrix) {
        String quarterStatus = getQuarter();
        TodoQuarter quarter = matrix.getQuarter(quarterStatus);
        System.out.println(quarter.toString());
        int entryIndex = getNumber("Chose entry number", 0, quarter.getItems().size()) - 1;
        return quarter.getItem(entryIndex);
    }

    public String getTitle() {
        String input = "";
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print("Enter todo entry title: ");
            input = getInput();
            if (validateIsStringEmpty(input)) {
                isAnswerCorrect = true;
            } else {
                System.out.println("Title can't be empty.\n");
            }
        }
        return input;
    }

    private int getNumber(String question, int start,int end) {
        int number = 0;
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print(question + ": ");
            String input = getInput();
            if (validateNumber(start, end, input)) {
                isAnswerCorrect = true;
                number = Integer.parseInt(input);
            } else {
                System.out.println("It must be a number in range " + start + " to " + end + ".\n");
            }
        }
        return number;
    }

    private boolean getImportance() {
        boolean important = false;
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print("Is it important (y/n): ");
            String input = getInput();
            if (validateString(input, YESNO)) {
                isAnswerCorrect = true;
                if (input.equals("y")) {
                    important = true;
                }
            } else {
                System.out.println("Answer need to be 'y' or 'n'.\n");
            }
        }
        return important;

    }

    public String getQuarter() {
        String input = "";
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print("Chose quarter ('IU', 'IN', 'NU', 'NN'): ");
            input = getInput();
            if (input.length() == 2 && validateIsStringEmpty(input) && validateString(input, QUARTERS)) {
                isAnswerCorrect = true;
            } else {
                System.out.println("Valid quartesr are 'IU', 'IN', 'NU' and 'NN'.\n");
            }
        }
        return input;
    }

    private String getInput() {
        String input = scanner.nextLine();
        return input;
    }

    private boolean validateString(String input, String constant) {
        if (constant.contains(input)) {
            return true;
        }
        return false;
    }

    private boolean validateIsStringEmpty(String input) {
        if (!input.isBlank()) {
            return true;
        }
        return false;
    }

    private boolean validateNumber(int start, int end, String input) {
        if (input.matches("[\\d]+")) {
            if (numberIsInRange(start, end, input)) {
                return true;
            }
        }
        return false;
    }

    private boolean numberIsInRange(int start, int end, String input) {
        if (start <= Integer.parseInt(input) && Integer.parseInt(input) <= end) {
            return true;
        }
        return false;
    }
}