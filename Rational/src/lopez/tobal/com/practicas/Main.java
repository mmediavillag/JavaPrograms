package lopez.tobal.com.practicas;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
	    Rational p = new Rational(new BigInteger("-1"), new BigInteger("2"));
	    Rational q = new Rational(new BigInteger("-3"), new BigInteger("4"));
	    Rational p_plus_q = p.add(q);
	    Rational p_prod_q = p.multiply(q);
        Rational p_div_q = p.divide(q);

        Rational p_minus_q = p.subtract(q);
        System.out.println(p_plus_q);
        System.out.println(p_minus_q);
        System.out.println(p_prod_q);
        System.out.println(p_div_q);
        System.out.println(p.absolute());
        System.out.println(p.compares(q));

        Rational r = new Rational(new BigInteger("-4"), new BigInteger("6"));
        System.out.println(r.simplify());
        System.out.println(r.pow(2));
        System.out.println(r.inverse());
        System.out.println(r.toReal());
        System.out.println(r.mod());

        Rational q1 = new Rational("-8/5");
        Rational q2 = new Rational("3 / 4");
        Rational q3 = new Rational("2");

        System.out.println("WITH CHARACTERS");
        System.out.println("===============");
        System.out.println(q1.multiply(q2));
        System.out.println(q1.multiply(q3));

        Rational g = new Rational();

        ToFraction n1 = new ToFraction(new BigInteger("2"), new BigInteger("83"));
        ToFraction n2 = new ToFraction(new BigInteger("1"), new BigInteger("13"), new BigInteger("1"));
        ToFraction n3 = new ToFraction(new BigInteger("0"), new BigInteger("1769"), new BigInteger("17"));

        System.out.println(n1.exactDecimal(2));
        System.out.println(n1.pureDecimal(2));
        System.out.println(n2.noPureDecimal(1, 1));
        System.out.println(n3.noPureDecimal(2, 2));
}
}
