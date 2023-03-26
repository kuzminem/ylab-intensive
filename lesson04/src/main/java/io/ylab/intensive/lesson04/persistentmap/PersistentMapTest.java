package io.ylab.intensive.lesson04.persistentmap;

import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PersistentMapTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);
        // Написать код демонстрации работы
        persistentMap.init("firstMap");
        persistentMap.put("One", "Uno");
        System.out.println(persistentMap.containsKey("One"));
        System.out.println(persistentMap.containsKey("Two"));
        System.out.println(persistentMap.get("One"));
        persistentMap.put("One", "Due");
        System.out.println(persistentMap.get("One"));
        persistentMap.put("Two", "Uno");
        System.out.println(persistentMap.getKeys());
        persistentMap.remove("One");
        System.out.println(persistentMap.getKeys());
        persistentMap.clear();
        System.out.println(persistentMap.getKeys());
    }

    public static DataSource initDb() throws SQLException {
        String createMapTable = ""
                + "drop table if exists persistent_map; "
                + "CREATE TABLE if not exists persistent_map (\n"
                + "   map_name varchar,\n"
                + "   KEY varchar,\n"
                + "   value varchar\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMapTable, dataSource);
        return dataSource;
    }
}
