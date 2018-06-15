package ru.job4j.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentsBuilder {
    public Departments getDepartments(String[] input) {
        List<String> departments = new ArrayList<>(Arrays.asList(input));
        List<String> missingDepartments = new ArrayList<>();
        for (String currentDepartment : departments) {
            while (true) {
                int i = currentDepartment.lastIndexOf(Departments.SEPARATOR);
                if (i == -1) {
                    break;
                } else {
                    String upperDepartment = currentDepartment.substring(0, i);
                    if (!departments.contains(upperDepartment)
                        && !missingDepartments.contains(upperDepartment)) {
                        missingDepartments.add(upperDepartment);
                    }
                    currentDepartment = upperDepartment;
                }
            }
        }
        departments.addAll(missingDepartments);
        return new Departments(departments);
    }
}
