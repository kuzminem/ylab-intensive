package io.ylab.intensive.lesson04.persistentmap;

import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PersistentMapTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);

        persistentMap.init("firstMap");
        persistentMap.put("One", "Uno");
        persistentMap.put("Two", "Due");
        persistentMap.put("Three", "Tre");
        persistentMap.put("Four", "Quattro");
        persistentMap.put("Five", "Cinque");

        persistentMap.init("secondMap");
        persistentMap.put("One", "Eins");
        persistentMap.put("Two", "Zwei");
        persistentMap.put("Three", "Drei");
        persistentMap.put("Four", "Vier");
        persistentMap.put("Five", "Funf");

        persistentMap.init("firstMap");
        System.out.println(persistentMap.getKeys());
        System.out.println(persistentMap.containsKey("Two"));
        System.out.println(persistentMap.get("Two"));
        System.out.println(persistentMap.containsKey("Six"));
        System.out.println(persistentMap.get("Six"));
        persistentMap.clear();
        System.out.println(persistentMap.getKeys());

        persistentMap.init("secondMap");
        System.out.println(persistentMap.get("Three"));
        persistentMap.put("Three", "Polizei");
        System.out.println(persistentMap.get("Three"));
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
