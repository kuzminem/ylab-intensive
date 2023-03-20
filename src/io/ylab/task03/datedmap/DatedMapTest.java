package io.ylab.task03.datedmap;

public class DatedMapTest {
    public static void main(String[] args) {
        DatedMap map = new DatedMapImpl();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");

        System.out.println(map.get("1") + " " + map.getKeyLastInsertionDate("1"));
        System.out.println(map.get("2") + " " + map.getKeyLastInsertionDate("2"));
        System.out.println(map.get("3") + " " + map.getKeyLastInsertionDate("3"));

        System.out.println(map.keySet());
        System.out.println(map.containsKey("2"));

        map.remove("2");
        System.out.println(map.keySet());
        System.out.println(map.containsKey("2"));
        System.out.println(map.get("2") + " " + map.getKeyLastInsertionDate("2"));

        map.put("4", "Fourth");
        System.out.println(map.keySet());
        System.out.println(map.containsKey("4"));
        System.out.println(map.get("4") + " " + map.getKeyLastInsertionDate("4"));
    }
}
