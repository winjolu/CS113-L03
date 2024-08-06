import java.util.ArrayList;
import java.util.List;

/**
 * Represents a polynomial composed of multiple terms.
 */
public class Polynomial {

    private List<Term> terms;

    /**
     * Default constructor initializing an empty polynomial.
     */
    public Polynomial() {
        this.terms = new ArrayList<>();
    }

    /**
     * Adds a term to the polynomial. Combines terms with the same exponent.
     * @param term The term to add to the polynomial.
     */
    public void addTerm(Term term) {
        boolean combined = false;
        for (Term t : terms) {
            if (t.getExponent() == term.getExponent()) {
                t.setCoefficient(t.getCoefficient() + term.getCoefficient());
                combined = true;
                break;
            }
        }
        if (!combined) {
            terms.add(term);
        }
    }

    /**
     * Adds another polynomial to this polynomial.
     * @param other The polynomial to add to this polynomial.
     * @return The resulting polynomial after addition.
     */
    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        for (Term t : this.terms) {
            result.addTerm(t.dupe());
        }
        for (Term t : other.terms) {
            result.addTerm(t.dupe());
        }
        return result;
    }

    /**
     * Multiplies another polynomial with this polynomial.
     * @param other The polynomial to multiply with this polynomial.
     * @return The resulting polynomial after multiplication.
     */
    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        for (Term t1 : this.terms) {
            for (Term t2 : other.terms) {
                int newCoefficient = t1.getCoefficient() * t2.getCoefficient();
                int newExponent = t1.getExponent() + t2.getExponent();
                result.addTerm(new Term(newCoefficient, newExponent));
            }
        }
        return result;
    }

    /**
     * Clears all terms from the polynomial.
     */
    public void clear() { //!
        terms.clear(); //!
    }

    @Override
    public String toString() {
        if (terms.isEmpty()) {
            return "0"; // handle case when polynomial is zero
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            Term t = terms.get(i);
            if (t.getCoefficient() > 0 && i > 0) {
                sb.append(" + ");
            } else if (t.getCoefficient() < 0) {
                sb.append(" - ");
            }
            sb.append(Math.abs(t.getCoefficient()));
            if (t.getExponent() != 0) {
                sb.append("x");
                if (t.getExponent() != 1) {
                    sb.append("^").append(t.getExponent());
                }
            }
        }
        return sb.toString();
    }
}
