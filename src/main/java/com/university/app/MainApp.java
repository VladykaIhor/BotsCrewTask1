package com.university.app;

import com.university.app.entity.Department;
import com.university.app.entity.Lector;
import com.university.app.service.DepartmentService;
import com.university.app.service.impl.DepartmentServiceImpl;
import com.university.app.utils.ConsoleParsingUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainApp {

    public static String availableCommands = "Available commands: " + "\n" +
            "Who is head of department {department_name}" + "\n" +
            "Show statistics of {department_name} " + "\n" +
            "Show the average salary for department {department_name}" + "\n" +
            "Show count of employee for {department_name}" + "\n" +
            "Global search by {template}" + "\n" +
            "quit" + "\n" + "Enjoy!";

    public static void main(String[] args) throws SQLException, IOException {
        DepartmentService departmentService = new DepartmentServiceImpl();
        ConsoleParsingUtil consoleParsingUtil = new ConsoleParsingUtil();
        String input = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(availableCommands);
            input = reader.readLine();
            CommandParserView commandParserView = consoleParsingUtil.consoleCommandAvailabilityChecker(input);
            switch (commandParserView.commandIndex) {
                case 0:
                    Lector headOfDepartment = departmentService.getHeadOfDepartment(
                            departmentService.getDepartmentByName(commandParserView.parameter));
                    System.out.println("The head of department is " + headOfDepartment.getName() + " " +
                            headOfDepartment.getLastName());
                    break;
                case 1:
                    Department department = departmentService.getDepartmentByName(commandParserView.parameter);
                    Map map = departmentService.showDepartmentStatistics(department);
                    for (String key : new ArrayList<String>(map.keySet())) {
                        System.out.println(key + " - " + map.get(key));
                    }
                case 2:
                    String departmentName = commandParserView.parameter;
                    System.out.println("An average salary for department " + departmentName +
                            " is " + departmentService.showAverageSalaryForDepartment(
                            departmentService.getDepartmentByName(departmentName)));
                case 3:
                    Department department1 = departmentService.
                            getDepartmentByName(commandParserView.parameter);
                    System.out.println("There are " + departmentService.showEmployeeCountForDepartment(department1).toString() +
                            " employees in " + commandParserView.parameter + " department.");
                case 4:
                    List<Lector> strings = departmentService.searchBy(commandParserView.parameter);
                    for (Lector l : strings) {
                        System.out.print(l.getName() + " " + l.getLastName() + "\n");
                    }
            }
        } while (!input.equals("quit"));
    }
}
