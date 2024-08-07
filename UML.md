+-------------------+
|      Polynomial   |
+-------------------+
| -polynomialList: LinkedList<Term> |
+-------------------+
| +Polynomial()     |
| +Polynomial(otherPolyObj: Polynomial) |
| +addTerm(term: Term): void |
| +addLikeTerms(): void |
| +sortTerms(poly: LinkedList<Term>): void |
| +add(otherPolyObj: Polynomial): Polynomial |
| +getNumTerms(): int |
| +getTerm(index: int): Term |
| +clear(): void    |
| +toString(): String |
+-------------------+

+-------------------+
|        Term       |
+-------------------+
| -coefficient: int |
| -exponent: int    |
+-------------------+
| +Term()           |
| +Term(coefficient: int, exponent: int) |
| +Term(term: String) |
| +Term(other: Term) |
| +getCoefficient(): int |
| +getExponent(): int |
| +setCoefficient(coefficient: int): void |
| +setExponent(exponent: int): void |
| +setAll(coefficient: int, exponent: int): void |
| +toString(): String |
| +compareTo(term: Term): int |
| +equals(obj: Object): boolean |
| +hashCode(): int  |
| +clone(): Object  |
+-------------------+

+-------------------+
|  PolynomialDriver |
+-------------------+
| (no attributes)   |
+-------------------+
| +main(args: String[]): void |
+-------------------+

+-------------------+
|       Main        |
+-------------------+
| (no attributes)   |
+-------------------+
| +main(args: String[]): void |
+-------------------+