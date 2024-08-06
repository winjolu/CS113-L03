import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {

    private LinkedList<Term> polynomialList;

    public Polynomial() {
        this.polynomialList = new LinkedList<>();
    }

    public Polynomial(Polynomial otherPolyObj) {
        if(otherPolyObj == null) return;
        LinkedList<Term> poly = otherPolyObj.polynomialList;
        this.polynomialList = new LinkedList<>(poly);
    }

    public void addTerm(Term term) {
        this.polynomialList.add(term);
        addLikeTerms();
    }

    public void addLikeTerms() {
        LinkedList<Term> reducedPoly = new LinkedList<>();
        while(!this.polynomialList.isEmpty()) {
            int exponentTerm = this.polynomialList.get(0).getExponent();
            int coefficientSum = this.polynomialList.get(0).getCoefficient();
            this.polynomialList.removeFirst();
            ListIterator<Term> iter = this.polynomialList.listIterator();
            while(iter.hasNext()) {
                Term term = iter.next();
                if(term.getExponent() == exponentTerm) {
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

    public void sortTerms(LinkedList<Term> poly) {
        Collections.sort(poly, new Comparator<Term>() {
            @Override
            public int compare(Term t1, Term t2) {
                return t2.getExponent() - t1.getExponent();
            }
        });
    }

    public Polynomial add(Polynomial otherPolyObj) {
        LinkedList<Term> poly = otherPolyObj.polynomialList;
        this.polynomialList.addAll(poly);
        this.addLikeTerms();
        return this;
    }

    public int getNumTerms() {
        return this.polynomialList.size();
    }

    public Term getTerm(int index) {
        return this.polynomialList.get(index);
    }

    public void clear() {
        this.polynomialList.clear();
    }

    @Override
    public String toString() {
        if (this.polynomialList.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (Term t : this.polynomialList) {
            if (t.getCoefficient() > 0 && sb.length() > 0) {
                sb.append(" + ");
            }
            if (t.getCoefficient() < 0 && sb.length() > 0) {
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
