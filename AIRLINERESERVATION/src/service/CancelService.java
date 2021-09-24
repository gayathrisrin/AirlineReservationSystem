package service;

import java.util.List;
import java.util.Scanner;

import dao.DAOClass;
import model.BookTickets;

public class CancelService {

	DAOClass getTickets = new DAOClass();

	public void getAllTicket() {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- FLIGHTS LIST------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		List<BookTickets> tickets = getTickets.getAllTickets();
		if (!tickets.isEmpty()) {

			for (BookTickets tickets1 : tickets) {
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                                ID : " + tickets1.getTick_id());
				System.out.println("                                       FLIGHT CODE : " + tickets1.getFlight_code());
				System.out.println("                                             UNAME : " + tickets1.getUname());
				System.out.println("                                            AMOUNT : " + tickets1.getAmount());
				System.out
						.println("                                   BOOKED TICKETS  : " + tickets1.getBookedTickets());
				System.out.println("                                       BOOKED DATE : " + tickets1.getBookedDate());
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
			}

		}

	}

	public boolean deleteTicket() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Ticket ID: ");
		int tick_id = sc.nextInt();
		boolean deletedStatus = getTickets.CancelService(tick_id);
		if (deletedStatus) {
			return true;
		}
		return false;
	}

}
