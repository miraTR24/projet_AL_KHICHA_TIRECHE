package FacadeConsole;

import java.io.File;
import java.util.Scanner;

import FacadeConsole.Facade;
import FacadeConsole.IFacade;
import projetXml.TodoListImpl;

public class LauncherConsole {
	  public static void start() {
	        IFacade ifacade = new Facade();
	        Scanner scanner = new Scanner(System.in);
	        boolean running = true;
	        TodoListImpl toDoList= new TodoListImpl();

	        while (running) {
	            String msg = "#################### To Do List ##################\n" +
	                         "Load XML File    ===>  load filename.xml \n" +
	                         "Top five non-completed tasks    ===>  top5 filename.xml \n" +
	                         "Exit Program    ===>  exit \n" +
	                         "Please choose a valid choice!";
	            
	            System.out.println(msg); // Afficher le message d'accueil
	            System.out.print("Votre commande : ");
	           
	            String command = scanner.nextLine().trim(); // Lire l'entrée de l'utilisateur

	            String[] commandTab = command.split(" ");
	            System.out.println();
	            if (commandTab.length == 1) { // Vérifier si une seule commande a été entrée
	                String commandType = commandTab[0].toUpperCase();

	                if ("EXIT".equals(commandType)) {
	                    running = false; // Terminer la boucle
	                    System.out.println("Exiting the program...");
	                    System.out.println();
	                } else {
	                    System.out.println("Commande incorrecte, réessayez.");
	                    System.out.println();
	                }

	            } else if (commandTab.length == 2) { // Vérifier s'il y a deux parties à la commande
	                String commandType = commandTab[0].toUpperCase();
	                String fileName = commandTab[1];

	                if (!fileName.toLowerCase().endsWith(".xml")) {
	                    System.out.println("Erreur : le fichier doit avoir une extension .xml. Veuillez réessayer.");
	                    System.out.println();
	                    continue; // Recommencer la boucle
	                }

	                File file = new File(fileName);
	                if (!file.exists()) {
	                    System.out.println("Erreur : le fichier n'existe pas. Veuillez réessayer avec un fichier existant.");
	                    System.out.println();
	                    continue; // Recommencer la boucle si le fichier n'existe pas
	                }

	                switch (commandType) {
	                    case "LOAD":
	                        try {
	                            ifacade.readToDoList(fileName,toDoList); // Charger le fichier XML
	                            
	                            System.out.println("Fichier chargé avec succès.");
	                            System.out.println();
	                            System.out.println();
	                        } catch (Exception e) { // Gérer les exceptions potentielles
	                            System.err.println("Erreur lors du chargement du fichier : " + e.getMessage());
	                            System.out.println();
	                        }
	                        break;

	                    case "TOP5":
	                        try {
	                            ifacade.readTopFiveToDoList(fileName,toDoList); // Charger le top 5 des tâches
	                            System.out.println("Top 5 chargé avec succès.");
	                            System.out.println();
	                            System.out.println();
	                        } catch (Exception e) {
	                            System.err.println("Erreur lors du chargement du top 5 : " + e.getMessage());
	                            System.out.println();
	                        }
	                        break;

	                    default:
	                        System.out.println("Commande incorrecte, réessayez.");
	                        break;
	                }

	            } else {
	                System.out.println("Commande incorrecte, réessayez.");
	            }
	        }

	        scanner.close(); 
	    }
}
