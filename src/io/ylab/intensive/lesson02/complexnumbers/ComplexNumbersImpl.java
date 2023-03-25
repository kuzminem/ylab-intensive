package io.ylab.intensive.lesson02.complexnumbers;

import static java.lang.Math.sqrt;

public class ComplexNumbersImpl implements ComplexNumbers {
    private final double re;
    private final double im;

    public ComplexNumbersImpl(double re) {
        this.re = re;
        this.im = 0;
    }

    public ComplexNumbersImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return this.re;
    }

    public double getIm() {
        return this.im;
    }

    @Override
    public ComplexNumbers add(ComplexNumbers arg) {
        return new ComplexNumbersImpl(this.re + arg.getRe(), this.im + arg.getIm());
    }

    @Override
    public ComplexNumbers subtract(ComplexNumbers arg) {
        return new ComplexNumbersImpl(this.re - arg.getRe(), this.im - arg.getIm());
    }

    @Override
    public ComplexNumbers multiply(ComplexNumbers arg) {
        return new ComplexNumbersImpl(this.re * arg.getRe() - this.im * arg.getIm(), this.im * arg.getRe() + this.re * arg.getIm());
    }

    @Override
    public double module() {
        return sqrt(this.re * this.re + this.im * this.im);
    }

    @Override
    public String toString() {
        return re + "+" + im + "i";
    }
}
