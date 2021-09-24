package main;

import java.util.Scanner;
import java.util.regex.*;
import service.LoginService;
import service.RegisterUserService;

public class Login {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);

		int opt;

		System.out.println("");
		System.out.println(
				"####################################### Welcome to the Flight Ticket Booking System! ########################################");
		System.out.println();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 1. Login -------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- 2. Registration -------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("Please select the serial number to be operated: ");
		opt = sc.nextInt();

		switch (opt) {
		case 1:
			LoginService login = new LoginService();
			boolean isUserExists = login.checkUserExists();
			if (isUserExists) {
				DisplayOptions display = new DisplayOptions();
				display.displayOptions();
			} else {
				main(null);
			}
			break;

		case 2:
			RegisterUserService register = new RegisterUserService();
			boolean status = register.registerUser();
			if (status) {
				main(null);
				
			} else {
				System.out.println();
				System.out.println(
					"-------------------------------------------------- Something Went Wrong :( --------------------------------------------------------------------");
				System.out.println();
			}
			break;
		default:
			System.out.println();
			System.out.println(
					"-------------------------------------------------- You Have Chosed Wrong Number :D -------------------------------------------------------------");
			System.out.println();
			main(null);
		}
	}

}
