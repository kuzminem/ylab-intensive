package io.ylab.intensive.lesson02.sequences;

public class SequencesImpl implements Sequences {
    @Override
    public void a(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 1073741823) {
            System.out.println("The argument is greater than 1073741823");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i * 2);
            }
        }
    }

    @Override
    public void b(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 1073741824) {
            System.out.println("The argument is greater than 1073741824");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i * 2 - 1);
            }
        }
    }

    @Override
    public void c(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        }
        if (n > 46340) {
            System.out.println("The argument is greater than 46340.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i * i);
            }
        }
    }

    @Override
    public void d(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 1290) {
            System.out.println("The argument is greater than 1290.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i * i * i);
            }
        }
    }

    @Override
    public void e(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i % 2 == 0 ? -1 : 1);
            }
        }
    }

    @Override
    public void f(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println((i % 2 == 0 ? -1 : 1) * i);
            }
        }
    }

    @Override
    public void g(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 46340) {
            System.out.println("The argument is greater than 46340.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println((i % 2 == 0 ? -1 : 1) * i * i);
            }
        }
    }

    @Override
    public void h(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(i % 2 == 0 ? 0 : i / 2 + 1);
            }
        }
    }

    @Override
    public void i(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 12) {
            System.out.println("The argument is greater than 12.");
        } else {
            int f = 1;
            for (int i = 1; i <= n; i++) {
                f *= i;
                System.out.println(f);
            }
        }
    }

    @Override
    public void j(int n) {
        if (n < 1) {
            System.out.println("The argument is less than 1.");
        } else if (n > 46) {
            System.out.println("The argument is greater than 46.");
        } else {
            int an = 1;
            int anminus1 = 0;
            int anminus2;
            System.out.println(an);
            for (int i = 2; i <= n; i++) {
                anminus2 = anminus1;
                anminus1 = an;
                an = anminus1 + anminus2;
                System.out.println(an);
            }
        }
    }
}
