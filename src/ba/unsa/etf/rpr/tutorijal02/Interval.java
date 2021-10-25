package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean ukljucenaPocetna;
    private boolean ukljucenaKrajnja;

    public Interval(double x, double y, boolean ukljucenX, boolean ukljucenY) {
        if (x > y) throw new IllegalArgumentException();
        else {
            pocetna = x;
            krajnja = y;
            ukljucenaPocetna = ukljucenX;
            ukljucenaKrajnja = ukljucenY;
        }
    }

    public Interval() {
        pocetna = 0;
        krajnja = 0;
        ukljucenaPocetna = false;
        ukljucenaKrajnja = false;
    }

    public boolean isIn(double x) {
        if ((ukljucenaPocetna && x == pocetna) || (ukljucenaKrajnja && x == krajnja) || (pocetna < x && x < krajnja)) return true;
        return false;
    }

    public boolean isNull() {
        if (pocetna == 0 && krajnja == 0 && ukljucenaPocetna == false && ukljucenaKrajnja == false) return  true;
        return false;
    }

    public Interval intersect(Interval i) {
        double A = 0;
        double B = 0;
        boolean ukljucenoA = false;
        boolean ukljucenoB = false;

        // intervali se nikako ne preklapaju
        if ((i.krajnja < pocetna) || (i.pocetna > krajnja)) return new Interval();

        // intervali su identicni
        if ((i.pocetna == pocetna && i.krajnja == krajnja && i.ukljucenaPocetna == ukljucenaPocetna && i.ukljucenaKrajnja == ukljucenaKrajnja)) return i;

        // medjuslucajevi
        if (i.pocetna > pocetna) {
            A = i.pocetna;
            ukljucenoA = i.ukljucenaPocetna;
        } else if (i.pocetna == pocetna) {
            A = pocetna;
            ukljucenoA = i.ukljucenaPocetna && ukljucenaPocetna;
        } else if (i.pocetna < pocetna) {
            A = pocetna;
            ukljucenoA = ukljucenaPocetna;
        }

        if (i.krajnja < krajnja) {
            B = i.krajnja;
            ukljucenoB = i.ukljucenaKrajnja;
        } else if (i.krajnja == krajnja) {
            B = krajnja;
            ukljucenoB = i.ukljucenaKrajnja && ukljucenaKrajnja;
        } else if (i.krajnja > krajnja) {
            B = krajnja;
            ukljucenoB = ukljucenaKrajnja;
        }

        return new Interval(A, B, ukljucenoA, ukljucenoB);
    }

    public static Interval intersect(Interval i1, Interval i2) {
        return i1.intersect(i2);
    }

    @Override
    public String toString() {
        if (pocetna == 0 && krajnja == 0 && ukljucenaPocetna == false && ukljucenaKrajnja == false) return "()";
        char z1 = '(';
        char z2 = ')';
        if (ukljucenaPocetna) z1 = '[';
        if (ukljucenaKrajnja) z2 = ']';
        return z1 + String.valueOf(pocetna) + ',' + String.valueOf(krajnja) + z2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetna, pocetna) == 0 && Double.compare(interval.krajnja, krajnja) == 0 && ukljucenaPocetna == interval.ukljucenaPocetna && ukljucenaKrajnja == interval.ukljucenaKrajnja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pocetna, krajnja, ukljucenaPocetna, ukljucenaKrajnja);
    }
}
