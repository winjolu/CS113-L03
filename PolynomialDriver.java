import java.util.Scanner;

public class PolynomialDriver {
    private static Polynomial p1 = new Polynomial();
    private static Polynomial p2 = new Polynomial();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                printMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                handleChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Edit first polynomial");
        System.out.println("2. Edit second polynomial");
        System.out.println("3. Display both polynomials");
        System.out.println("4. Display result of adding the two polynomials");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                editPolynomial(p1, "first");
                break;
            case 2:
                editPolynomial(p2, "second");
                break;
            case 3:
                displayBothPolynomials();
                break;
            case 4:
                displayResultOfAddition();
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void editPolynomial(Polynomial poly, String name) {
        while (true) {
            try {
                System.out.println("Current " + name + " polynomial: " + poly);
                System.out.println("1. Clear polynomial");
                System.out.println("2. Add terms");
                System.out.println("3. Back to main menu");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        poly.clear();
                        System.out.println("Polynomial cleared.");
                        break;
                    case 2:
                        addTerms(poly);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addTerms(Polynomial poly) {
        while (true) {
            try {
                System.out.print("Enter term (e.g., 3x^2 or -4x or 5): ");
                String termStr = scanner.nextLine();
                if (termStr.equalsIgnoreCase("done")) {
                    break;
                }
                Term term = new Term(termStr);
                poly.addTerm(term);
                System.out.println("Term added.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid term format. Please try again.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void displayBothPolynomials() {
        System.out.println("First polynomial: " + p1);
        System.out.println("Second polynomial: " + p2);
    }

    private static void displayResultOfAddition() {
        Polynomial result = p1.add(p2);
        System.out.println("Result of adding the two polynomials: " + result);
    }
}