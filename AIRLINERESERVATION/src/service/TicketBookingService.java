package service;

import java.util.Scanner;

import dao.DAOClass;
import model.BookTickets;

public class TicketBookingService {

	public boolean ticketBooking() {
		Scanner sc = new Scanner(System.in);
		BookTickets tick = new BookTickets();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- TICKET BOOKING ------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		System.out.print("Enter Flight Code: ");
		tick.setFlight_code(sc.next());

		System.out.print("Enter Your User Name: ");
		tick.setUname(sc.next());

		System.out.print("How Many Tickets Need To Be Booked: ");
		tick.setBookedTickets(sc.nextInt());

		DAOClass bookTicket = new DAOClass();
		if (bookTicket.ticketBooking(tick)) {
			System.out.println(
					"--------------------------------- TICKET BOOKED SUCCESSFULLY :) --------------------------------");
			return true;
		} else {
			System.out.println(
					"--------------------------------- TICKET BOOKED FAILED :( ------------------------------------------");
		}

		return false;
	}
	
}
