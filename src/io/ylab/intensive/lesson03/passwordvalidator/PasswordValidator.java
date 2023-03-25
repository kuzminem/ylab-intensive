package io.ylab.intensive.lesson03.passwordvalidator;

public class PasswordValidator {
    static boolean isValidated(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {

        try {
            if (login.length() == 0) {
                return true;
            } else if (login.length() >= 20) {
                throw new WrongLoginException("Логин слишком длинный");
            } else if (!login.matches("\\w+")) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            }

            if (password.length() == 0) {
                return true;
            } else if (password.length() >= 20) {
                throw new WrongPasswordException("Пароль слишком длинный");
            } else if (!password.matches("\\w+")) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            } else if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароль и подтверждение не совпадают");
            }
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static void main(String[] args)
            throws WrongLoginException, WrongPasswordException {

        String login;
        String password;
        String confirmPassword;

        login = "Go0d_log1n";
        password = "password";
        confirmPassword = "password";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "B@d_log1n";
        password = "password";
        confirmPassword = "password";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "";
        password = "Empty_login";
        confirmPassword = "Empty_login";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Login_20_char_length";
        password = "password";
        confirmPassword = "password";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Login_19_charLength";
        password = "password";
        confirmPassword = "password";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Good_password";
        password = "Go0d_pas5word";
        confirmPassword = "Go0d_pas5word";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Bad_password";
        password = "B@d_pas5word";
        confirmPassword = "B@d_pas5word";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Empty_password";
        password = "";
        confirmPassword = "";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Password_20";
        password = "Password_20_char_len";
        confirmPassword = "Password_20_char_len";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Password_19";
        password = "Password_19_charLen";
        confirmPassword = "Password_19_charLen";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Pass_no_confirm";
        password = "password";
        confirmPassword = "confirmation";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();

        login = "Кириллица";
        password = "password";
        confirmPassword = "password";
        System.out.println(login
                + " + " + password
                + " + " + confirmPassword
                + " = " + isValidated(login, password, confirmPassword));
        System.out.println();
    }
}
