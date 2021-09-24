package service;

import java.util.List;
import java.util.Scanner;

import dao.DAOClass;
import model.FlightList;

public class SearchFlightService {

	public boolean searchFlight() {

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- SEARCH FLIGHTS ------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		System.out.print("From: ");
		String from = sc.next();

		System.out.print("To: ");
		String to = sc.next();

		DAOClass getFlights = new DAOClass();
		List<FlightList> flights = getFlights.searchFlight(from, to);
		if (!flights.isEmpty()) {

			for (FlightList flight : flights) {
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                               ID :                  " + flight.getId());
				System.out.println("                               FLIGHT CODE :         " + flight.getFlightCode());
				System.out.println("                               FROM :                " + flight.getFrom());
				System.out.println("                               TO :                  " + flight.getTo());
				System.out.println("                               DEPARTURE TIME :      " + flight.getDeparture());
				System.out.println("                               ARRIVAL TIME :        " + flight.getArrival());
				System.out.println("                               PRICE :               " + flight.getPrice());
				System.out.println("                               AVAILABLE TICKETS :   " + flight.getAvailTickets());
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
			}
			return true;
		}

		return false;
	}
}
