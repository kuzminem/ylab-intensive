package io.ylab.intensive.lesson05.sqlquerybuilder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class SQLQueryExtenderTest {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        SQLQueryBuilder queryBuilder = applicationContext.getBean(SQLQueryBuilder.class);

        List<String> tables = queryBuilder.getTables();
        for (String t : tables) {
            System.out.println(queryBuilder.queryForTable(t));
        }
        System.out.println();
        System.out.println(queryBuilder.queryForTable("abracadabra"));
    }
}
