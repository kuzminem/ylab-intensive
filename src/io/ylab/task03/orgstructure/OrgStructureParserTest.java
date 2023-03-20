package io.ylab.task03.orgstructure;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrgStructureParserTest {
    public static void main(String[] args) throws IOException {
        File csvFile = new File("src/io/ylab/task03/orgstructure/resources/OrgStructure.csv");
        OrgStructureParser orgStructureParser = new OrgStructureParserImpl();
        Employee boss = orgStructureParser.parseStructure(csvFile);
        System.out.println(show(boss));

        List<Employee> firstLevelSubordinates = boss.getSubordinate();
        for (Employee i : firstLevelSubordinates) {
            System.out.println(show(i));
            List<Employee> secondLevelSubordinates = i.getSubordinate();
            for (Employee j : secondLevelSubordinates) {
                System.out.println(show(j));
            }
        }
    }

    private static String show(Employee employee) {
        return "id: " + employee.getId()
                + ", Boss id: " + employee.getBossId()
                + ", Name: " + employee.getName()
                + ", Position: " + employee.getPosition()
                + ", Boss: " + employee.getBoss()
                + ", Subordinate: " + employee.getSubordinate();
    }
}
