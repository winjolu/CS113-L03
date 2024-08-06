import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private List<Term> terms;

    public Polynomial() {
        this.terms = new ArrayList<>();
    }

    public void addTerm(Term term) {
        // Combine terms with the same exponent
        for (Term t : terms) {
            if (t.getExponent() == term.getExponent()) {
                t.setCoefficient(t.getCoefficient() + term.getCoefficient());
                return;
            }
        }
        // Add new term if no existing term has the same exponent
        terms.add(term);
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            Term t = terms.get(i);
            if (t.getCoefficient() > 0 && i > 0) {
                sb.append(" + ");
            }
            if (t.getCoefficient() < 0) {
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
