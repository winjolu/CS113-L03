import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Term implements Comparable<Term>, Cloneable {
    private static final int DEFAULT_COEFFICIENT = 1;
    private static final int DEFAULT_EXPONENT = 1;

    private int coefficient;
    private int exponent;

    // Default constructor
    public Term() {
        this.coefficient = DEFAULT_COEFFICIENT;
        this.exponent = DEFAULT_EXPONENT;
    }

    // Constructor with coefficient and exponent
    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    // Constructor that parses a string representation of a term, regex for simplicity's sake
    public Term(String term) {
        // Regex to match terms of the form [+-]?(\d+)x(\^\d+)? or [+-]?\d+
        Pattern pattern = Pattern.compile("([+-]?\\d*)x(\\^(\\d+))?|([+-]?\\d+)");
        Matcher matcher = pattern.matcher(term.trim());

        if (matcher.matches()) {
            String coeffStr = matcher.group(1);
            String expStr = matcher.group(3);
            String constantStr = matcher.group(4);

            if (constantStr != null) {
                // Handle constant term
                this.coefficient = Integer.parseInt(constantStr);
                this.exponent = 0;
            } else {
                // Handle variable term
                if (coeffStr.equals("") || coeffStr.equals("+")) {
                    this.coefficient = 1;
                } else if (coeffStr.equals("-")) {
                    this.coefficient = -1;
                } else {
                    this.coefficient = Integer.parseInt(coeffStr);
                }

                if (expStr == null) {
                    this.exponent = 1;
                } else {
                    this.exponent = Integer.parseInt(expStr);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid term format: " + term);
        }
    }

    // Copy constructor
    public Term(Term other) {
        this.coefficient = other.coefficient;
        this.exponent = other.exponent;
    }

    // Getter for coefficient
    public int getCoefficient() {
        return this.coefficient;
    }

    // Getter for exponent
    public int getExponent() {
        return this.exponent;
    }

    // Setter for coefficient
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    // Setter for exponent
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    // Setter for both coefficient and exponent
    public void setAll(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    // Returns a string representation of the term
    public String toString() {
        if (this.coefficient == 0) {
            return "0";
        } else if (this.exponent == 0) {
            return "" + this.coefficient;
        } else if (this.exponent == 1) {
            if (this.coefficient == 1) {
                return "x";
            } else if (this.coefficient == -1) {
                return "-x";
            } else {
                return this.coefficient + "x";
            }
        } else {
            if (this.coefficient == 1) {
                return "x^" + this.exponent;
            } else if (this.coefficient == -1) {
                return "-x^" + this.exponent;
            } else {
                return this.coefficient + "x^" + this.exponent;
            }
        }
    }

    // Compares this term to another term based on their exponents in descending order
    @Override
    public int compareTo(Term term) {
        return term.exponent - this.exponent; // Descending order
    }

    // Checks if this term is equal to another object
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Term) {
            Term other = (Term) obj;
            return this.coefficient == other.coefficient && this.exponent == other.exponent;
        }
        return false;
    }

    // Returns a hash code for this term
    @Override
    public int hashCode() {
        return coefficient + exponent;
    }

    // Clones this term
    @Override
    protected Object clone() {
        return new Term(this.coefficient, this.exponent);
    }
}