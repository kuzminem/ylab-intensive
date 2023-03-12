package io.ylab.task02.complexnumbers;

public interface ComplexNumbers {

    ComplexNumbers add(ComplexNumbers arg);

    ComplexNumbers subtract(ComplexNumbers arg);

    ComplexNumbers multiply(ComplexNumbers arg);

    double module();

    String toString();

    double getRe();

    double getIm();
}
