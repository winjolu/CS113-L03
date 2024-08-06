import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Edit the first polynomial");
            System.out.println("2. Edit the second polynomial");
            System.out.println("3. Display the result of adding the two polynomials");
            System.out.println("4. Display both polynomials");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    editPolynomial(scanner, p1);
                    break;
                case 2:
                    editPolynomial(scanner, p2);
                    break;
                case 3:
                    Polynomial sum = p1.add(new Polynomial(p2));
                    System.out.println("Sum of the two polynomials: " + sum);
                    break;
                case 4:
                    System.out.println("First polynomial: " + p1);
                    System.out.println("Second polynomial: " + p2);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void editPolynomial(Scanner scanner, Polynomial polynomial) {
        while (true) {
            System.out.println("Edit Polynomial Menu:");
            System.out.println("1. Clear polynomial");
            System.out.println("2. Add a term");
            System.out.println("3. Go back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    polynomial.clear();
                    System.out.println("Polynomial cleared.");
                    break;
                case 2:
                    System.out.print("Enter term (e.g., 3x^2 or -5x): ");
                    String termInput = scanner.nextLine();
                    Term term = new Term(termInput);
                    polynomial.addTerm(term);
                    System.out.println("Term added.");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
