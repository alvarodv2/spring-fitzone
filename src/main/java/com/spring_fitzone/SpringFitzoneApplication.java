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
        logger.info("*** Iniciando Aplicación ***");
		SpringApplication.run(SpringFitzoneApplication.class, args);
        logger.info("*** Aplicación Finalizada ***");
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
            exit = optionExecute(consoleMenu, option);
            logger.info(nl);
        }
    }

    private int showMenu(Scanner console){
        logger.info("""
        *** Aplicación Fit Zone ***
        1. List Client
        2. Search Client
        3. Add Client
        4. Modify Client
        5. Delete Client
        6. Exit
        Choose a option:\s""");
        return Integer.parseInt(console.nextLine());
    }

    private boolean optionExecute(Scanner console, int option){
        var exit = false;

        switch (option){
            case 1 -> {
                logger.info(nl + "--- List of Clients ---");
                List<Client> clients = serviceClient.clientList();
                clients.forEach(client -> logger.info(client.toString() + nl));
            }
            case 2 -> {
                logger.info(nl, "--- Search Cient By ID ---");
                logger.info("Client ID to find: ");
                var clientId = Integer.parseInt(console.nextLine());
                Client client = serviceClient.searchClientById(clientId);
                if (client != null){
                    logger.info("Client founded: " + client + nl);
                } else {
                    logger.info("Client not founded: " + client + nl);
                }
            }
            case 3 -> {
                logger.info("--- Add Client ----" + nl);
                logger.info("Name: ");
                var clientName = console.nextLine();
                logger.info("Second Name: ");
                var clientSecondName = console.nextLine();
                logger.info("Membership: ");
                var clientMembership = Integer.parseInt(console.nextLine());
                var client = new Client();
                client.setClientName(clientName);
                client.setClientSecondName(clientSecondName);
                client.setClientMembership(clientMembership);
                serviceClient.saveClient(client);
                logger.info("Added Client successfully: " + client + nl);
            }
            case 4 -> {
                logger.info("--- Update Client ----" + nl);
                logger.info("Id Client to modify: ");
                var clientId = Integer.parseInt(console.nextLine());
                Client client = serviceClient.searchClientById(clientId);
                if (client != null){
                    logger.info("Name: ");
                    var clientName = console.nextLine();
                    logger.info("Second Name: ");
                    var clientSecondName = console.nextLine();
                    logger.info("Membership: ");
                    var clientMembership = Integer.parseInt(console.nextLine());
                    client.setClientName(clientName);
                    client.setClientSecondName(clientSecondName);
                    client.setClientMembership(clientMembership);
                    serviceClient.saveClient(client);
                    logger.info("Updated Client: " + client + nl);
                } else {
                    logger.info("Client not founded: " + client + nl);
                }
            }
            case 5 -> {
                logger.info("--- Delete Client ---" + nl);
                logger.info("Client ID to delete: " );
                var clientId = Integer.parseInt(console.nextLine());
                var client = serviceClient.searchClientById(clientId);
                if (client != null){
                    serviceClient.deleteClient(client);
                    logger.info("Deleted Client: " + client + nl);
                } else {
                    logger.info("Client not founded: " + client + nl);
                }
            }
            case 6 -> {
                logger.info("See u soon :D" + nl + nl);
                exit = true;
            }
            default -> logger.info("Failed to execute the selected option: " + option + nl);
        }
        return exit;
    }


}
