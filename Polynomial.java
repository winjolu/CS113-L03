import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {

    private LinkedList<Term> polynomialList;

    // Default constructor initializing an empty polynomial
    public Polynomial() {
        this.polynomialList = new LinkedList<>();
    }

    // Copy constructor creating a deep copy of the given polynomial
    public Polynomial(Polynomial otherPolyObj) {
        if (otherPolyObj == null) return;
        this.polynomialList = new LinkedList<>();
        for (Term term : otherPolyObj.polynomialList) {
            this.polynomialList.add(new Term(term.getCoefficient(), term.getExponent()));
        }
    }

    // Adds a term to the polynomial and ensures the polynomial is in reduced form
    public void addTerm(Term term) {
        this.polynomialList.add(term);
        addLikeTerms();
    }

    // Combines like terms in the polynomial and sorts them in descending order of exponents
    public void addLikeTerms() {
        LinkedList<Term> reducedPoly = new LinkedList<>();
        while (!this.polynomialList.isEmpty()) {
            int exponentTerm = this.polynomialList.get(0).getExponent();
            int coefficientSum = this.polynomialList.get(0).getCoefficient();
            this.polynomialList.removeFirst();
            ListIterator<Term> iter = this.polynomialList.listIterator();
            while (iter.hasNext()) {
                Term term = iter.next();
                if (term.getExponent() == exponentTerm) {
                    coefficientSum += term.getCoefficient();
                    iter.remove();
                }
            }
            if (coefficientSum != 0) {
                reducedPoly.add(new Term(coefficientSum, exponentTerm));
            }
        }
        sortTerms(reducedPoly);
        this.polynomialList = reducedPoly;
    }

    // Sorts the terms in the polynomial in descending order of exponents
    public void sortTerms(LinkedList<Term> poly) {
        Collections.sort(poly, new Comparator<Term>() {
            @Override
            public int compare(Term t1, Term t2) {
                return t2.getExponent() - t1.getExponent();
            }
        });
    }

    // Adds another polynomial to this polynomial and returns the result as a new polynomial
    public Polynomial add(Polynomial otherPolyObj) {
        Polynomial result = new Polynomial(this);
        for (Term term : otherPolyObj.polynomialList) {
            result.addTerm(new Term(term.getCoefficient(), term.getExponent()));
        }
        result.addLikeTerms();
        return result;
    }

    // Returns the number of terms in the polynomial
    public int getNumTerms() {
        return this.polynomialList.size();
    }

    // Returns the term at the specified index
    public Term getTerm(int index) {
        return this.polynomialList.get(index);
    }

    // Clears all terms from the polynomial
    public void clear() {
        this.polynomialList.clear();
    }

    // Returns a string representation of the polynomial
    @Override
    public String toString() {
        if (this.polynomialList.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder(); //yay SB!
        boolean isFirstTerm = true; //doesn't append '+' to first term
        for (Term t : this.polynomialList) { //iteration time
            if (!isFirstTerm || t.getCoefficient() < 0) {
                sb.append(t.getCoefficient() > 0 ? " + " : " - ");
            }
            sb.append(Math.abs(t.getCoefficient())); //use absolute value of coefficient to handle sign correctly
            if (t.getExponent() != 0) {
                sb.append("x");
                if (t.getExponent() != 1) {
                    sb.append("^").append(t.getExponent());
                }
            }
            isFirstTerm = false;
        }
        return sb.toString();
    }
}