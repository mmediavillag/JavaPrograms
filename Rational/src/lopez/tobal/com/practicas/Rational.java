package lopez.tobal.com.practicas;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Rational implements Comparable<Rational>{
    private BigInteger numerator;
    private BigInteger denominator;
    private static final BigInteger ZERO = new BigInteger("0");
    private static final BigInteger ONE = new BigInteger("1");

    public Rational() {
        this(ZERO, ONE);
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        if(denominator.equals(ZERO))
            throw new IllegalArgumentException("The denominator can't be ZERO.");
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public Rational(String fraction){
        fraction = fraction.replaceAll(" ","");
        if(!fraction.contains("/")){
            this.numerator = new BigInteger(fraction);
            this.denominator = BigInteger.ONE;
        }
        else {
            String[] fraction1 = fraction.split(Pattern.quote("/"));
            if(fraction1[1].equals("0"))
                throw new IllegalArgumentException("The denominator can't be ZERO.");
            this.numerator = new BigInteger(fraction1[0]);
            this.denominator = new BigInteger(fraction1[1]);
        }
        normalize();
    }
    public BigInteger getNumerator() {
        return numerator;
    }

    public void setNumerator(BigInteger numerator) {
        this.numerator = numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public void setDenominator(BigInteger denominator) {
        this.denominator = denominator;
    }
    private void  normalize(){
        BigInteger the_gcd = numerator.gcd(denominator);

        if(numerator.signum() == -1 && denominator.signum() == -1){
            numerator = numerator.abs();
            denominator = denominator.abs();
        }
        numerator = numerator.divide(the_gcd);
        denominator = denominator.divide(the_gcd);
    }
    public Rational opposite(){
        return new Rational(this.numerator.negate(), this.denominator);
    }

    public Rational absolute() {
        if(this.numerator.signum() == 1 && this.denominator.signum() == 1) return this;
        else return this.opposite();
    }

    public Rational add(Rational q) {
        BigInteger product = this.denominator.multiply(q.denominator);
        BigInteger the_lcm = product.divide(this.denominator.gcd(q.denominator));
        BigInteger n = (the_lcm.divide(this.denominator)).multiply(this.numerator);
        n = n.add((the_lcm.divide(q.denominator)).multiply(q.numerator));
        return new Rational(n, the_lcm);
    }
    public Rational subtract(Rational q){
        return this.add(q.opposite());
    }
    public Rational multiply(Rational q){
        return new Rational(this.numerator.multiply(q.numerator), this.denominator.multiply(q.denominator));
    }
    public Rational divide(Rational q){
        if(q.denominator.equals(ZERO) || (q.numerator.equals(ZERO) && q.denominator.equals(ZERO)))
            throw new ArithmeticException("Divided by ZERO is illegal.");
        return new Rational(this.numerator.multiply(q.denominator), this.denominator.multiply(q.numerator));
    }
    public BigInteger mod(){
        return this.numerator.remainder(this.denominator);
    }
    public Rational inverse(){
        if(this.numerator.equals(ZERO))
            throw new ArithmeticException("Not exist the inverse when the numerator is ZERO.");
        return new Rational(this.denominator, this.numerator);
    }
    public Rational pow(int n){
        return new Rational(this.numerator.pow(n), this.denominator.pow(n));
    }
    public double toReal(){
        assert !this.denominator.equals(ZERO);
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }
    public String compares(Rational q){
        Integer expression1 = this.numerator.multiply(q.denominator).intValueExact();
        Integer expression2 = this.denominator.multiply(q.numerator).intValueExact();
        if(expression1 < expression2) return this.toString() + " < " + q.toString();
        else if(expression1 > expression2) return this.toString() + " > " + q.toString();
        else return this.toString() + " = " + q.toString();
    }
    public Rational simplify(){
        normalize();
        return new Rational(this.numerator, this.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        return numerator.equals(rational.numerator) && denominator.equals(rational.denominator);
    }

    @Override
    public int hashCode() {
        int result = numerator.hashCode();
        result = 31 * result + denominator.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if(numerator.equals(ZERO) && ! denominator.equals(ZERO))
            return "0";
        if(denominator.equals(ONE)) return "" + numerator;
        else if((numerator.signum() == -1 && denominator.signum() == -1) ||
                (numerator.signum() == 1 && denominator.signum() == -1))
            return numerator.negate() + " / " + denominator.negate();
        else return numerator + " / " + denominator;
    }

    @Override
    public int compareTo(Rational q) {
        if (this.subtract(q).numerator.signum() == 1)
            return 1;
        else if (this.subtract(q).numerator.signum() == -1)
            return -1;
        else
            return 0;
    }
}
