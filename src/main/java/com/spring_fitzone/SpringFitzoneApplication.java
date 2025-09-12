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

    private static final Logger logger = LoggerFactory.getLogger(SpringFitzoneApplication.class);
    private static final String NL = System.lineSeparator();

    private final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("*** Starting Application ***");
        SpringApplication.run(SpringFitzoneApplication.class, args);
        logger.info("*** Application Finished ***");
    }

    @Override
    public void run(String... args) throws Exception {
        fitZoneApp();
        console.close();
    }

    private void fitZoneApp() {
        boolean exit = false;

        while (!exit) {
            int option = showMenu();
            exit = executeOption(option);
            System.out.println();
        }
    }

    private int showMenu() {
        System.out.println("""
            *** Fit Zone Application ***
            1. List Clients
            2. Search Client
            3. Add Client
            4. Modify Client
            5. Delete Client
            6. Exit""");
        return readInt("Choose an option: ");
    }

    private boolean executeOption(int option) {
        boolean exit = false;

        switch (option) {
            case 1 -> {
                System.out.println(NL + "--- List of Clients ---");
                List<Client> clients = serviceClient.clientList();
                if (clients.isEmpty()) {
                    System.out.println("No clients found.");
                } else {
                    clients.forEach(System.out::println);
                }
            }
            case 2 -> {
                System.out.println(NL + "--- Search Client By ID ---");
                int clientId = readInt("Client ID to find: ");
                Client client = serviceClient.searchClientById(clientId);
                if (client != null) {
                    System.out.println("Client found: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 3 -> {
                System.out.println(NL + "--- Add Client ---");
                String name = readString("Name: ");
                String lastName = readString("Last Name: ");
                int membership = readInt("Membership: ");

                Client client = new Client();
                client.setClientName(name);
                client.setClientSecondName(lastName);
                client.setClientMembership(membership);
                serviceClient.saveClient(client);
                System.out.println("Client added successfully: " + client);
            }
            case 4 -> {
                System.out.println(NL + "--- Update Client ---");
                int clientId = readInt("Client ID to modify: ");
                Client client = serviceClient.searchClientById(clientId);
                if (client != null) {
                    String name = readString("Name: ");
                    String lastName = readString("Last Name: ");
                    int membership = readInt("Membership: ");

                    client.setClientName(name);
                    client.setClientSecondName(lastName);
                    client.setClientMembership(membership);
                    serviceClient.saveClient(client);
                    System.out.println("Client updated successfully: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 5 -> {
                System.out.println(NL + "--- Delete Client ---");
                int clientId = readInt("Client ID to delete: ");
                Client client = serviceClient.searchClientById(clientId);
                if (client != null) {
                    serviceClient.deleteClient(client);
                    System.out.println("Client deleted successfully: " + client);
                } else {
                    System.out.println("Client not found with ID: " + clientId);
                }
            }
            case 6 -> {
                System.out.println(NL + "See you soon! :D");
                exit = true;
            }
            default -> System.out.println("Invalid option selected: " + option);
        }
        return exit;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(console.nextLine());
    }
}