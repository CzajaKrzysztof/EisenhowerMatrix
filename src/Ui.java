import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private static Scanner scanner = new Scanner(System.in);
    private final String OPTIONS = "12345678";
    private final String YESNO = "yn";
    
    public void printMenu() {
        String menu = "MENU OPTIONS:\n" +
                      "1. Show Eisenhower matrix\n" +
                      "2. Add entry \n" +
                      "3. Mark entry as done\n" +
                      "4. Unmart entry as done\n" +
                      "5. Remove chosen entry\n" +
                      "6. Remove all marked entries\n" +
                      "7. Save entries to file\n" +
                      "8. Quit\n\n";
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

    public String getTitle() {
        String input = "";
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.print("Enter todo entry title: ");
            input = getInput();
            if (validateTitle(input)) {
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

    private boolean validateTitle(String title) {
        if (!title.isBlank()) {
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