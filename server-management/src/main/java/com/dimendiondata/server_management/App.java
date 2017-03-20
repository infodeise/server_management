package com.dimendiondata.server_management;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dimendiondata.server_management.dao.ServerDao;
import com.dimendiondata.server_management.model.Server;
import com.dimendiondata.server_management.util.XMLConverter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		printHelp();
		String action = scanner.nextLine();

		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ServerDao serverDAO = context.getBean(ServerDao.class);
		
		XMLConverter converter = (XMLConverter) context.getBean("XMLConverter");
		

		while (!action.equals("exit")) {

			if (action.equals("?")) {
				printHelp();
			} else if (action.equals("add")) {
				Server server = new Server();
				System.out.println("Server name:\n");
				server.setName(scanner.nextLine());
				System.out.println("Server description:\n");
				server.setDescription(scanner.nextLine());
				serverDAO.insert(server);
				
				System.out.println("Server inserted with successfully");

			} else if (action.equals("edit")) {
				Server server = new Server();
				System.out.println("Server id:\n");
				try {
					server.setId(scanner.nextInt());
				} catch (Exception e) {
					System.out.println("Invalid Id");
				}

				System.out.println("Server name:\n");
				server.setName(scanner.nextLine());
				
				System.out.println("Server description:\n");
				server.setDescription(scanner.nextLine());
				
				serverDAO.update(server);
				System.out.println("Server updated with successfully");

			} else if (action.equals("delete")) {
				System.out.println("Server id:\n");
				try {
					serverDAO.delete(scanner.nextInt());
					System.out.println("Server deleted with successfully");
				} catch (Exception e) {
					System.out.println("Invalid Id");
				}

			} else if (action.equals("list")) {
				List<Server> serverList = serverDAO.list();
				for (Server item : serverList) {
					System.out.println(item.toString());
				}
			} else if (action.equals("count")) {
				System.out.println("The amount of the server(s) is: " + serverDAO.count());
			} else if (action.equals("add -xml")) {
				Server server;
				System.out.println("XML path:\n");
				String xmlPath = scanner.nextLine();
				try {
					server = (Server)converter.convertFromXMLToObject(xmlPath);
					serverDAO.insert(server);
					System.out.println("Server inserted with successfully");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("list -p")) {
				try {
					System.out.println("First value to pagination:\n");
					int first = scanner.nextInt();
					System.out.println("Limit to pagination:\n");
					int limit = scanner.nextInt();
					List<Server> serverPagingList = serverDAO.listPaging(first, limit);
					for (Server item : serverPagingList) {
						System.out.println(item.toString());
					}
				} catch (Exception e) {
					System.out.println("One or more parameters was invalid");
				}
			} else if (action.equals("list -f")) {
				try {
					System.out.println("Query:\n");
					String query = scanner.nextLine();
					System.out.println("First value to pagination:\n");
					int first = scanner.nextInt();
					System.out.println("Limit to pagination:\n");
					int limit = scanner.nextInt();
					List<Server> serverPagingList = serverDAO.listFiltering(first, limit, query);
					for (Server item : serverPagingList) {
						System.out.println(item.toString());
					}
				} catch (Exception e) {
					System.out.println("One or more parameters was invalid");
				}
			} else {
				System.out.println("Invalid Command.");
			}

			scanner = new Scanner(System.in);
			printHelp();
			action = scanner.nextLine();
		}

		context.close();
	}

	private static void printHelp() {
		String help = "Command Help:\n" 
				+ "- add (in order to add a new server)\n"
				+ "- add -xml (in order to add a new server from xml file)"
				+ "- edit (in order to edit a existing server)\n"
				+ "- delete (in order to delete the specified server)\n"
				+ "- list (in order to return the server list)\n" + "- count (in order to return the count of servers)"
				+ "- exit (in order to quit the system";
		System.out.println(help);

	}
}
