import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a polynomial composed of multiple terms.
 */
public class Polynomial {

    private LinkedList<Term> polynomialList;

    /**
     * Default constructor initializing an empty polynomial.
     */
    public Polynomial() {
        this.polynomialList = new LinkedList<>();
    }

    /** copy constructor */
    public Polynomial(Polynomial otherPolyObj) {
        if(otherPolyObj == null) return;
        LinkedList<Term> poly = otherPolyObj.polynomialList;
        this.polynomialList = new LinkedList<>(poly);
    }

    
    /**
     * Adds a term to the polynomial. Combines terms with the same exponent.
     * @param term The term to add to the polynomial.
     */
    public void addTerm(Term term) {
        this.polynomialList.add(term);
        // this.addLikeTerms();
        // for (Term t : terms) {
        //     if (t.getExponent() == term.getExponent()) {
        //         t.setCoefficient(t.getCoefficient() + term.getCoefficient());
        //         combined = true;
        //         break;
        //     }
        // }
        // if (!combined) {
        //     terms.add(term);
        // }
    }
    
    //add like terms and sort
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

    //sort terms in descending order
    public void sortTerms(LinkedList<Term> poly) {
        Collections.sort(poly, new Comparator<Term>() {
            @Override
            public int compare(Term t1, Term t2) {
                return t2.getExponent() - t1.getExponent();
            }
        });
    }
    /**
     * Adds another polynomial to this polynomial.
     * @param other The polynomial to add to this polynomial.
     * @return The resulting polynomial after addition.
     */
    public Polynomial add(Polynomial otherPolyObj) {
        LinkedList<Term> poly = otherPolyObj.polynomialList;
        this.polynomialList.addAll(poly);
        this.addLikeTerms();
    }

    // how many terms are there?
    public int getNumTerms() {
        return this.polynomialList.size();
    }

    //get term
    public Term getTerm(int index) {
        return this.polynomialList.get(index);
    }


    
    // clear all terms
    public void clear() {
        return this.polynomialList.clear(); 
    }
    
    @Override
    public String toString() {
        String output = "";
        if (this.polynomialList.isEmpty()) {
            output = "0";
        } else {
            ListIterator<Term> iter = this.polynomialList.listIterator();
            Term curr = iter.next();
            if (curr.getCoefficient() > 0) {
                output += curr.toString().substring(1);
            }else {
                output += curr.toString();
            }
            while(iter.hasNext()) {
                curr = iter.next();
                output += iter.next().toString();
            }
        // if (terms.isEmpty()) {
        //     return "0"; // handle case when polynomial is zero
        // }
        // StringBuilder sb = new StringBuilder();
        // boolean isFirstTerm = true; //!
        // for (Term t : terms) {
        //     if (t.getCoefficient() == 0) {
        //         continue; // Skip terms with a coefficient of 0
        //     }
        //     if (!isFirstTerm) { //!
        //         if (t.getCoefficient() > 0) {
        //             sb.append(" + ");
        //         } else {
        //             sb.append(" - ");
        //             sb.append(Math.abs(t.getCoefficient()));
        //         }
        //     } else {
        //         if (t.getCoefficient() < 0) {
        //             sb.append("-");
        //         }
        //         sb.append(Math.abs(t.getCoefficient()));
        //         isFirstTerm = false; //!
        //     }
        //     if (t.getExponent() != 0) {
        //         sb.append("x");
        //         if (t.getExponent() != 1) {
        //             sb.append("^").append(t.getExponent());
        //         }
        //     }
        }
        return output;
        // return sb.toString();
    }
}

// /**
//  * Multiplies another polynomial with this polynomial.
//  * @param other The polynomial to multiply with this polynomial.
//  * @return The resulting polynomial after multiplication.
//  */
// public Polynomial multiply(Polynomial other) {
//     Polynomial result = new Polynomial();
//     for (Term t1 : this.terms) {
//         for (Term t2 : other.terms) {
//             int newCoefficient = t1.getCoefficient() * t2.getCoefficient();
//             int newExponent = t1.getExponent() + t2.getExponent();
//             result.addTerm(new Term(newCoefficient, newExponent));
//         }
//     }
//     return result;
// }