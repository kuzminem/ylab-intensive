package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageFilterApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();

        Responder responder = applicationContext.getBean(Responder.class);

        String input = "Оп-па, оп-па, у Джигурды большая жопа!";
        System.out.println(input);
        System.out.println(responder.getOutput(input));
    }
}
