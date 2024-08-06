import java.util.Scanner;

public class PolynomialDriver {
    private static Scanner scanner = new Scanner(System.in);
    private static Polynomial polynomial1 = new Polynomial();
    private static Polynomial polynomial2 = new Polynomial();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    editPolynomial(polynomial1, "first");
                    break;
                case 2:
                    editPolynomial(polynomial2, "second");
                    break;
                case 3:
                    displayAdditionResult();
                    break;
                case 4:
                    displayPolynomials(); //!
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Program exited.");
    }

    private static void displayMenu() {
        System.out.println("\nPolynomial Operations Menu:");
        System.out.println("1. Edit the first polynomial");
        System.out.println("2. Edit the second polynomial");
        System.out.println("3. Display the result of adding the first and second polynomial");
        System.out.println("4. Display stored polynomials"); //!
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number between 1 and 5: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void editPolynomial(Polynomial polynomial, String polynomialName) {
        boolean back = false;
        while (!back) {
            System.out.println("\nEdit " + polynomialName + " polynomial:");
            System.out.println("1. Clear polynomial");
            System.out.println("2. Add term to polynomial");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    polynomial.clear();
                    System.out.println("Polynomial cleared.");
                    break;
                case 2:
                    addTermToPolynomial(polynomial);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTermToPolynomial(Polynomial polynomial) {
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter term (e.g., 2x^3 or -4x): ");
        String termStr = scanner.nextLine();
        try {
            Term term = parseTerm(termStr);
            polynomial.addTerm(term);
            System.out.println("Term added: " + term);
        } catch (Exception e) {
            System.out.println("Invalid term format. Please try again.");
        }
    }

    private static Term parseTerm(String termStr) throws Exception {
        termStr = termStr.replace(" ", ""); // Remove spaces
        int coefficient = 1;
        int exponent = 0;

        if (termStr.contains("x")) {
            String[] parts = termStr.split("x");
            if (parts.length == 0 || parts.length > 2) {
                throw new Exception("Invalid term format");
            }

            if (!parts[0].isEmpty()) {
                coefficient = Integer.parseInt(parts[0].replace("+", ""));
            } else {
                coefficient = 1;
            }

            if (parts.length == 2 && !parts[1].isEmpty()) {
                if (parts[1].startsWith("^")) {
                    exponent = Integer.parseInt(parts[1].substring(1));
                } else {
                    throw new Exception("Invalid term format");
                }
            } else {
                exponent = 1;
            }
        } else {
            coefficient = Integer.parseInt(termStr);
        }

        return new Term(coefficient, exponent);
    }

    private static void displayAdditionResult() {
        Polynomial sum = polynomial1.add(polynomial2);
        System.out.println("First polynomial: " + polynomial1);
        System.out.println("Second polynomial: " + polynomial2);
        System.out.println("Sum: " + sum);
    }

    private static void displayPolynomials() { //!
        System.out.println("First polynomial: " + polynomial1); //!
        System.out.println("Second polynomial: " + polynomial2); //!
    } //!
}
