public class Term implements Comparable<Term> {           
    //attributes, instance variables, data fields
    private static final int DEFAULT_COEFFICIENT = 1; 
    private static final int DEFAULT_EXPONENT = 1; 

    private int coefficient;
    private int exponent;

    //constructors
    public Term() {
        this.coefficient = DEFAULT_COEFFICIENT;
        this.exponent = DEFAULT_EXPONENT;
    }

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(String term) {
        if (term.equals("")) {
            this.coefficient = 0;
            this.exponent = 0;
        } else if (!term.contains("x")) {
            this.coefficient = Integer.parseInt(term);
            this.exponent = 0;
        } else if (!term.contains("^") && term.contains("x")) {
            if(term.equals("+x")) {
                this.coefficient = 1;
                this.exponent = 1;
            } else if(term.equals("-x")) {
                this.coefficient = -1;
                this.exponent = 1;
            } else {
                String coefficientTerm = term.substring(0, term.length() - 1);
                this.coefficient = Integer.parseInt(coefficientTerm);
                this.exponent = 1;
            }
        } else if (term.contains("^") && term.contains("x")) {
            String coefficientTerm = term.substring(0, term.indexOf("x")); 
            String exponentTerm = term.substring(term.indexOf("^") + 1); //!
            this.exponent = Integer.parseInt(exponentTerm); //!
            if(coefficientTerm.equals("+")) { //!
                this.coefficient = 1;
            } else if(coefficientTerm.equals("-")) { //!
                this.coefficient = -1;
            } else {
                this.coefficient = Integer.parseInt(coefficientTerm); 
            }
        }
    } 

    // dupe constructor
    public Term dupe() {
        return new Term(this.coefficient, this.exponent);
    }


    
    //setters
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    
    public void setExponent(int exponent) {
        this.exponent = exponent;;
    }
    
    public void setTerm(int coefficient, int exponent) {
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }
    
    public void setAll(int coefficient, int exponent) {
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
    }
    
    //getters
    public int getCoefficient() {
        return this.coefficient;
    }

    public int getExponent() {
        return this.exponent;
    }


    //toString

    // from video:
    // public String toString() {
    //     String term = "";
    //     if(this.coefficient == 0) {
    //         return "";
    //     } else if(this.exponent == 0 && this.coefficient > 0) {
    //         return "" + this.coefficient;
    //     } else if (this.exponent == 0 && this.coefficient < 0) {
    //         return "" + this.coefficient;
    //     } else if(this.exponent == 1 && this.coefficient == 1) {
    //         return "+x";
    //     } else if (this.exponent == 1 && this.coefficient == -1) {
    //         return "-x";
    //     } else if (this.exponent == 1 && this.coefficient > 1) {
    //         return "+" + this.coefficient + "x";
    //      } else if (this.exponent == 1 && this.coefficient < 1) {
    //         return "" + this.coefficient + "x";
    //     } else if (this.coefficient == 1) {
    //         return "+x^" + this.exponent;
    //     } else if (this.coefficient == -1) {
    //         return "-x^" + this.exponent;
    //     } else if (this.coefficient > 1) {
    //         return "+" + "x^" + this.exponent;
    //     } else if (this.coefficient < 1) {
    //         return "" + "x^" + this.exponent;
    //     }
    //     return term;
    // }

// refactor:
public String toString() {
    if(this.coefficient == 0) {
        return "0"; 
    } else if(this.exponent == 0) {
        return "" + this.coefficient; 
    } else if(this.exponent == 1) {
        if(this.coefficient == 1) {
            return "x"; 
        } else if(this.coefficient == -1) {
            return "-x"; 
        } else {
            return this.coefficient + "x"; 
        }
    } else {
        if(this.coefficient == 1) {
            return "x^" + this.exponent; 
        } else if(this.coefficient == -1) {
            return "-x^" + this.exponent; 
        } else {
            return this.coefficient + "x^" + this.exponent; 
        }
    }
}


    //compareTo
    public int compareTo(Term term) {
        return this.exponent - term.exponent;
    }

    //equals
    public boolean equals(Object obj) {
        if(obj instanceof Term) {
            Term other = (Term) obj;
            return this.coefficient == other.coefficient && this.exponent == other.exponent;
        }
        return false;
    }

    //hashCode
    public int hashCode() {
        return coefficient + exponent;
    }
}
