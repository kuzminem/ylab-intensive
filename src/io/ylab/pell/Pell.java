package io.ylab.pell;

import java.util.Scanner;

public class Pell {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            if (n == 0) {
                System.out.println(0);
            } else {
                long an = 1;
                long anminus1 = 0;
                long anminus2;
                for (int i = 1; i < n; i++) {
                    anminus2 = anminus1;
                    anminus1 = an;
                    an = 2 * anminus1 + anminus2;
                }
                System.out.println(an);
            }
        }
    }
}
