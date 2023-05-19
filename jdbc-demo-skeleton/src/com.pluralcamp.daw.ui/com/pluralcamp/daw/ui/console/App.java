package com.pluralcamp.daw.ui.console;

import java.util.List;
import java.util.Scanner;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.entities.core.Employee;
import com.pluralcamp.daw.entities.core.Event;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.ColorDAOJDBCImpl;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.EmployeeDAOJDBCImpl;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.EventDAOJDBCImpl;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ColorDAOJDBCImpl colorDAO = new ColorDAOJDBCImpl();
        EventDAOJDBCImpl eventDAO = new EventDAOJDBCImpl();
        EmployeeDAOJDBCImpl employeeDAO = new EmployeeDAOJDBCImpl();

        /* *** COLORES *** */
        // Obtener color con id 5
        try {
            Color c = colorDAO.getColorById(5);

            if (c != null) {
                System.out.println(c.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();
        
        try {
            // Obtener lista de colors
            List<Color> colors = colorDAO.getColors();
            
            // Obtener 4 colores a partir de id 3
            List<Color> colors2 = colorDAO.getColors(2, 4);
            
            // Obtener colores que tengan en el nombre Pink
            List<Color> colors3 = colorDAO.getColors("Pink");
            
            // Obtener 2 colores que tengan en el nombre Light
            // a partir de segunda coincidencia
            List<Color> colors4 = colorDAO.getColors("Light", 1, 2);
            
            for (Color color : colors) {
                System.out.println(color.toString());
            }

            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Color color : colors2) {
                System.out.println(color.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Color color : colors3) {
                System.out.println(color.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Color color : colors4) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();


        /* *** EVENTOS *** */
        // Obtener evento con id 5
        try {
            Event e = eventDAO.getEventById(5);

            if (e != null) {
                System.out.println(e.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        try {
            // Obtener lista de eventos
            List<Event> events = eventDAO.getEvents();
            // Obtener 2 eventos a partir de id 2
            List<Event> events2 = eventDAO.getEvents(1, 2);
            // Obtener eventos que tengan en el nombre un 5
            List<Event> events3 = eventDAO.getEvents("5");
            // Obtener 1 evento que tengan en el nombre un 2
            // a partir de segunda coincidencia
            List<Event> events4 = eventDAO.getEvents("2", 1, 1);

            for (Event event : events) {
                System.out.println(event.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Event event : events2) {
                System.out.println(event.toString());
            }

            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Event event : events3) {
                System.out.println(event.toString());
            }

            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Event event : events4) {
                System.out.println(event.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }     

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        /* *** EMPLEADOS *** */
        // Obtener empleado con id 2
        try {
            Employee em = employeeDAO.getEmployeeById(2);

            if (em != null) {
                System.out.println(em.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();
        
        // Obtener lista de empleados
        try {

            List<Employee> employees = employeeDAO.getEmployees();
            // Obtener 2 empleados a partir del empleado 2
            List<Employee> employees2 = employeeDAO.getEmployees(1, 2);
            // Obtener empleados que tengan en el code la letra S
            List<Employee> employees3 = employeeDAO.getEmployees("S");
            // Obtener 1 empleado que tenga en el code la letra S a partir del segundo
            List<Employee> employees4 = employeeDAO.getEmployees("S", 1, 1);
            
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Employee employee : employees2) {
                System.out.println(employee.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Employee employee : employees3) {
                System.out.println(employee.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();

            for (Employee employee : employees4) {
                System.out.println(employee.toString());
            }
            
            System.out.println("Pulsa una tecla para continuar");
            sc.nextLine();
            
        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        // Close scanner
        sc.close();
    }
}
