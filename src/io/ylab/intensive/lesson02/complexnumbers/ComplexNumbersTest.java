package io.ylab.intensive.lesson02.complexnumbers;

public class ComplexNumbersTest {
    public static void main(String[] args) {
        System.out.println("Создаём число по действительной части:");
        ComplexNumbers firstNum = new ComplexNumbersImpl(1);
        System.out.println(firstNum);
        System.out.println();

        System.out.println("Создаём число по действительной и мнимой части:");
        ComplexNumbers secondNum = new ComplexNumbersImpl(3, 4);
        System.out.println(secondNum);
        System.out.println();

        firstNum = new ComplexNumbersImpl(1, 2);
        System.out.println("Сложение " + firstNum + " и " + secondNum + ":");
        System.out.println(firstNum.add(secondNum));
        System.out.println();

        System.out.println("Вычитание " + firstNum + " из " + secondNum + ":");
        System.out.println(secondNum.subtract(firstNum));
        System.out.println();

        System.out.println("Умножение " + firstNum + " на " + secondNum + ":");
        System.out.println(firstNum.multiply(secondNum));
        System.out.println();

        System.out.println("Модуль " + secondNum + ":");
        System.out.println(secondNum.module());
        System.out.println();
    }
}
