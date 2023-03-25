package io.ylab.intensive.lesson03.orgstructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrgStructureParserImpl implements OrgStructureParser {
    @Override
    public Employee parseStructure(File csvFile) throws IOException {
        Employee result = new Employee();

        try (FileInputStream fileInputStream = new FileInputStream(csvFile);
             Scanner scanner = new Scanner(fileInputStream)) {
            String firstLine = scanner.nextLine();
            if (!firstLine.equals("id;boss_id;name;position")) {
                throw new IllegalArgumentException("The first line should have a header");
            }

            Map<Long, Employee> employeeMap = new HashMap<>();
            while (scanner.hasNextLine()) {
                String stringFromFile = scanner.nextLine();
                String[] line = stringFromFile.split(";");

                Employee employee = new Employee();
                employee.setId(Long.parseLong(line[0]));
                try {
                    employee.setBossId(Long.parseLong(line[1]));
                } catch (NumberFormatException e) {
                    employee.setBossId(null);
                    result = employee;
                }
                employee.setName(line[2]);
                employee.setPosition(line[3]);

                employeeMap.put(employee.getId(), employee);
            }

            for (Long id : employeeMap.keySet()) {
                Employee employee = employeeMap.get(id);
                if (employee == result) {
                    continue;
                }
                Employee boss = employeeMap.get(employee.getBossId());
                employee.setBoss(boss);
                boss.getSubordinate().add(employee);
            }
        }
        return result;
    }
}
