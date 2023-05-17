package com.pluralcamp.daw.ui.console;

import java.util.List;
import java.util.Scanner;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.entities.core.Event;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.ColorDAOJDBCImpl;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.EventDAOJDBCImpl;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        ColorDAOJDBCImpl colorDAO = new ColorDAOJDBCImpl();
        EventDAOJDBCImpl eventDAO = new EventDAOJDBCImpl();

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
        
        // Obtener lista de colors
        try {
            List<Color> colors = colorDAO.getColors();
            
            for (Color color : colors) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();


        // Obtener 4 colores a partir de id 3
        try {
            List<Color> colors = colorDAO.getColors(2,4);
            
            for (Color color : colors) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        // Obtener colores que tengan en el nombre Pink
        try {
            List<Color> colors = colorDAO.getColors("Pink");
            
            for (Color color : colors) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        } 
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        // Obtener 2 colores que tengan en el nombre Light 
        // a partir de segunda coincidencia
        try {
            List<Color> colors = colorDAO.getColors("Light", 1, 2);
            
            for (Color color : colors) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

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

        // Obtener lista de eventos
        try {
            List<Event> events = eventDAO.getEvents();
            
            for (Event color : events) {
                System.out.println(color.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        // Obtener 2 eventos a partir de id 2
        try {
            List<Event> events = eventDAO.getEvents(1,2);
            
            for (Event event : events) {
                System.out.println(event.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();
       
        // Obtener eventos que tengan en el nombre un 5
        try {
            List<Event> events = eventDAO.getEvents("5");
            
            for (Event event : events) {
                System.out.println(event.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();
        
        // Obtener 1 evento que tengan en el nombre un 2 
        // a partir de segunda coincidencia
        try {
            List<Event> events = eventDAO.getEvents("2", 1, 1);
            
            for (Event event : events) {
                System.out.println(event.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        sc.nextLine();

        // Close scanner
        sc.close();
    }
}
