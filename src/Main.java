/*
This program is regarding a library management system.
And, this is the main file of the program.
 */

//Import necessary libraries
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    //changed IN to inputText. Non constant variables should be camel case
    private static Scanner inputText;
    //Changed LIB to library
    private static library library;
    //Changed MENU to menu
    private static String menu;
    //changed CAL to calendar
    private static Calendar calendar;
    //change SDF to dateFormat
    private static SimpleDateFormat dateFormat;

    //changed Get_menu to getmenu / lowerCamelCase for method names
    private static String getMenu() {
        //sb to stringBuilder
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nLibrary Main Menu\n\n")
            .append("  M  : add member\n")
            .append("  LM : list members\n")
            .append("\n")
            .append("  B  : add book\n")
            .append("  LB : list books\n")
            .append("  FB : fix books\n")
            .append("\n")
            .append("  L  : take out a loan\n")
            .append("  R  : return a loan\n")
            .append("  LL : list loans\n")
            .append("\n")
            .append("  P  : pay fine\n")
            .append("\n")
            .append("  T  : increment date\n")
            .append("  Q  : quit\n")
            .append("\n")
            .append("Choice : ");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            inputText = new Scanner(System.in);
            library = library.INSTANCE();
            calendar = calendar.getInstance();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            //change m to member
            for (member member : library.Members()) {
                output(member);
            }
            output(" ");
            //changed b to book
            for (book book : library.Books()) {
                output(book);
            }

            menu = getMenu();

            //change e to userQuits - meaningful variable
            boolean userQuits = false;

            while (!userQuits) {
                //no method calls within argument lists of any method calls
                Date calendarDate = calendar.Date();
                output("\n" + dateFormat.format(calendarDate));
                //changed c to inputChoice
                String inputChoice = input(menu).toUpperCase();

                switch (inputChoice) {

                    case "M":
                        addMember();
                        break;

                    case "LM":
                        listMembers();
                        break;

                    case "B":
                        addBook();
                        break;

                    case "LB":
                        listBooks();
                        break;

                    case "FB":
                        fixBooks();
                        break;

                    case "L":
                        borrowBook();
                        break;

                    case "R":
                        returnBook();
                        break;

                    case "LL":
                        listCurrentLoans();
                        break;

                    case "P":
                        payFine();
                        break;

                    case "T":
                        incrementDate();
                        break;

                    case "Q":
                        userQuits = true;
                        break;

                    default:
                        output("\nInvalid option\n");
                        break;
                }

                library.SAVE();
            }
        } catch (RuntimeException e) {
            output(e);
        }
        output("\nEnded\n");
    }

    private static void payFine() {
        //no method calls within argument lists of any method calls
        PayFineControl payFineControl = new PayFineControl();
        PayFineUI payFineUi = new PayFineUI(payFineControl);
        payFineUi.run();
    }

    private static void listCurrentLoans() {
        output("");
        //no method calls within argument lists of any method calls
        List currentLoans = library.currentLoans();
        //CurrentLoans => currentLoans
        for (loan loan : currentLoans) {
            output(loan + "\n");
        }
    }

    private static void listBooks() {
        output("");
        //no method calls within argument lists of any method calls
        List books = library.books();
        //Books => books
        for (book book : books) {
            output(book + "\n");
        }
    }

    private static void listMembers() {
        output("");
        //no method calls within argument lists of any method calls
        List members = library.members();
        //Members => members
        for (member member : members) {
            output(member + "\n");
        }
    }

    private static void borrowBook() {
        //no method calls within argument lists of any method calls
        BorrowBookControl borrowBookControl = new BorrowBookControl();
        BorrowBookUI borrowBookUi = new BorrowBookUI(borrowBookControl);
        borrowBookUi.run();
    }

    private static void returnBook() {
        //no method calls within argument lists of any method calls
        ReturnBookControl returnBookControl = new ReturnBookControl();
        ReturnBookUI returnBookUi = new ReturnBookUI(returnBookControl);
        returnBookUi.run();
    }

    private static void fixBooks() {
        //no method calls within argument lists of any method calls
        FixBookControl fixBookControl = new FixBookControl();
        FixBookUI fixBookUi = new FixBookUI(fixBookControl);
        fixBookUi.run();
    }

    private static void incrementDate() {
        try {
            //no method calls within argument lists of any method calls
            String inputDays = input("Enter number of days: ");
            int days = Integer.valueOf(inputDays).intValue();
            calendar.incrementDate(days);
            library.checkCurrentLoans();

            //no method calls within argument lists of any method calls
            Date calendarDate = calendar.Date();
            String dateFromat = dateFormat.format(calendarDate);
            output(dateFromat);

        } catch (NumberFormatException e) {
            output("\nInvalid number of days\n");
        }
    }

    private static void addBook() {
        String author = input("Enter author: ");
        String title = input("Enter title: ");
        String callNo = input("Enter call number: ");
        //Add_book => addBook
        book book = library.addBook(author, title, callNo);
        output("\n" + book + "\n");
    }

    private static void addMember() {
        try {
            String lastName = input("Enter last name: ");
            String firstName = input("Enter first name: ");
            String email = input("Enter email: ");
            //no method calls within argument lists of any method calls
            String inputPhoneNo = input("Enter phone number: ");
            int phoneNo = Integer.valueOf(inputPhoneNo).intValue();
            //Add_mem => addMember
            member member = library.addMember(lastName, firstName, email, phoneNo);
            output("\n" + member + "\n");

        } catch (NumberFormatException e) {
            output("\nInvalid phone number\n");
        }
    }

    private static String input(String prompt) {
        System.out.print(prompt);
        return inputText.nextLine();
    }

    private static void output(Object object) {
        System.out.println(object);
    }

}
