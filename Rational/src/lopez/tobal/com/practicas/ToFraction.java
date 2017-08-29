package lopez.tobal.com.practicas;

import java.math.BigInteger;

public class ToFraction {
    private BigInteger intPart;
    private BigInteger decimal_part;
    private BigInteger non_period;

    private static final BigInteger TEN = new BigInteger("10");

    public ToFraction(BigInteger intPart, BigInteger decimal_part) {
        this.intPart = intPart;
        this.decimal_part = decimal_part;
    }
    public ToFraction(BigInteger intPart, BigInteger decimal_part, BigInteger non_period) {
        this.intPart = intPart;
        this.decimal_part = decimal_part;
        this.non_period = non_period;
    }
    public Rational exactDecimal(int numdec){
        BigInteger num, den;
        num = this.intPart.multiply(TEN.pow(numdec)).add(this.decimal_part);
        den = TEN.pow(numdec);
        return new Rational(num, den);
    }
    public Rational pureDecimal(int puredec){
        BigInteger num, den;
        num = this.intPart.multiply(TEN.pow(puredec)).add(this.decimal_part).subtract(this.intPart);
        den = TEN.pow(puredec).subtract(BigInteger.ONE);
        return new Rational(num, den);
    }
    public Rational noPureDecimal(int nopuredec, int puredec){
        BigInteger num, den;
        int digits;
        digits = contDigits(this.intPart.intValueExact()) + nopuredec;
        for(int i = 0; i < digits; i++)
        num = this.intPart.multiply(TEN.pow(nopuredec + puredec)).add(this.decimal_part)
                .subtract(this.intPart.add(this.non_period.multiply(TEN.pow(nopuredec))) );
        System.out.println(num);
        den = TEN.pow(puredec).subtract(BigInteger.ONE).multiply(TEN.pow(nopuredec));
        return new Rational(num, den);
    }
    private int contDigits(int n){
        int cifras = 0;
        while(n != 0)
        {
            n = n / 10;
            cifras++;
        }
        return cifras;
    }
}
