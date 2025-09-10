package com.spring_fitzone;

import com.spring_fitzone.entity.Client;
import com.spring_fitzone.service.ServiceClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringFitzoneApplication implements CommandLineRunner {

    @Autowired
    private ServiceClient serviceClient;

    private static final Logger logger =
            LoggerFactory.getLogger(SpringFitzoneApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("*** Starting Application ***");
        SpringApplication.run(SpringFitzoneApplication.class, args);
        logger.info("*** Application Finished ***");
    }

    @Override
    public void run(String... args) throws Exception {
        fitZoneApp();
    }

    private void fitZoneApp(){
        var exit = false;
        var consoleMenu = new Scanner(System.in);

        while (!exit){
            var option = showMenu(consoleMenu);
            exit = executeOption(consoleMenu, option);
            System.out.println();
        }
    }

    private int showMenu(Scanner console){
        System.out.println("""
            *** Fit Zone Application ***
            1. List Clients
            2. Search Client
            3. Add Client
            4. Modify Client
            5. Delete Client
            6. Exit""");
        System.out.print("Choose an option: ");
        return Integer.parseInt(console.nextLine());
    }


    private boolean executeOption(Scanner console, int option){
        var exit = false;

        switch (option){
            case 1 -> {
                System.out.println(nl + "--- List of Clients ---");
                List<Client> clients = serviceClient.clientList();
                if (clients.isEmpty()) {
                    System.out.println("No clients found.");
                } else {
                    clients.forEach(client -> System.out.println(client.toString()));
                }
            }
            case 2 -> {
                System.out.println(nl + "--- Search Client By ID ---");
                System.out.print("Client ID to find: ");
                var clientId = Integer.parseInt(console.nextLine());
                Client client = serviceClient.searchClientById(clientId);
                if (client != null){
                    System.out.println("Client found: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 3 -> {
                System.out.println(nl + "--- Add Client ---");
                System.out.print("Name: ");
                var clientName = console.nextLine();
                System.out.print("Last Name: ");
                var clientSecondName = console.nextLine();
                System.out.print("Membership: ");
                var clientMembership = Integer.parseInt(console.nextLine());

                var client = new Client();
                client.setClientName(clientName);
                client.setClientSecondName(clientSecondName);
                client.setClientMembership(clientMembership);
                serviceClient.saveClient(client);
                System.out.println("Client added successfully: " + client);
            }
            case 4 -> {
                System.out.println(nl + "--- Update Client ---");
                System.out.print("Client ID to modify: ");
                var clientId = Integer.parseInt(console.nextLine());
                Client client = serviceClient.searchClientById(clientId);
                if (client != null){
                    System.out.print("Name: ");
                    var clientName = console.nextLine();
                    System.out.print("Last Name: ");
                    var clientSecondName = console.nextLine();
                    System.out.print("Membership: ");
                    var clientMembership = Integer.parseInt(console.nextLine());

                    client.setClientName(clientName);
                    client.setClientSecondName(clientSecondName);
                    client.setClientMembership(clientMembership);
                    serviceClient.saveClient(client);
                    System.out.println("Client updated successfully: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 5 -> {
                System.out.println(nl + "--- Delete Client ---");
                System.out.print("Client ID to delete: ");
                var clientId = Integer.parseInt(console.nextLine());
                var client = serviceClient.searchClientById(clientId);
                if (client != null){
                    serviceClient.deleteClient(client);
                    System.out.println("Client deleted successfully: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 6 -> {
                System.out.println(nl + "See you soon! :D");
                exit = true;
            }
            default -> System.out.println("Invalid option selected: " + option);
        }
        return exit;
    }
}