package main;

import java.util.Scanner;

import service.CancelService;
import service.SearchFlightService;
import service.TicketBookingService;

public class DisplayOptions {

	public void displayOptions() throws Exception {
		DisplayOptions display = new DisplayOptions();

		Scanner sc = new Scanner(System.in);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 1. Search For A flight ----------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 2. Book A Ticket ----------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 3. cancel  -----------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 4. Exist -------------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("Please select the serial number to be operated:");
		int opt = sc.nextInt();

		switch (opt) {
		case 1:
			SearchFlightService searchFlight = new SearchFlightService();
			if (searchFlight.searchFlight()) {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- HERE YOUR FLIGHTS :O ----------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			} else {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- NO FLIGHTS FOUND :( -----------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			}
			break;
		case 2:
			TicketBookingService ticket = new TicketBookingService();
			boolean status = ticket.ticketBooking();
			if (status) {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- TICKETS BOOKING CONFIRMED :) ----------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			} else {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- TICKETS BOOKING FAILED :( -------------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			}
			break;
		case 3:
			CancelService cancel = new CancelService();
			cancel.getAllTicket();
			boolean status1 = cancel.deleteTicket();
			if (status1) {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- TICKET CANCELLED :) ------------------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			} else {
				System.out.println();
				System.out.println(
						"-------------------------------------------------- TICKET CANCELATION FAILED :( ----------------------------------------------------------");
				System.out.println();
				display.displayOptions();
			}

			break;
		case 4:
			System.out.println();
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"-------------------------------------------------- THANK YOU FOR BOOKING VISIT AGAIN:) ----------------------------------------------------------");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			break;
		case 5:
			System.out.println("not implemented");
			break;
		default:
			System.out.println("Choose Correctly");
			display.displayOptions();
		}

	}
}
