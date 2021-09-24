package service;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.DAOClass;
import model.RegisterUser;

public class RegisterUserService {

	public boolean registerUser() {

		Scanner sc = new Scanner(System.in);
		RegisterUser user = new RegisterUser();
		DAOClass reg = new DAOClass();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"-------------------------------------------------- WELCOME NEW USER ----------------------------------------------------------");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");

		System.out.print("Enter your Name: ");
		user.setName(sc.next());

		System.out.print("Enter your Age: ");
		user.setAge(sc.nextInt());

		boolean emailStatus = true;
		while (emailStatus) {

			System.out.print("Enter your Email: ");
			user.setEmail(sc.next());
			String regex = "([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)";
			boolean status1 = Pattern.matches(regex, user.getEmail());
			if (!status1) {
				System.out.println();
				System.out.println("Given Email ID is Invalid.");
				System.out.println();
			} else {
				emailStatus = false;
			}
		}
		System.out.print("Enter your Contact Number: ");
		user.setContact(sc.next());

		System.out.print("Enter Password: ");
		user.setPass(sc.next());

		System.out.println("Note: Copy and Paste\nAadhar\nPAN\nDriving_License");
		System.out.print("Enter ID Type: ");
		user.setIdType(sc.next());

		System.out.print("Enter ID Number: ");
		user.setIdNum(sc.next());
		boolean status = reg.insertUserService(user);
		if (status) {
			System.out.println(
			    "------------------------------------------------ Registeration Success :) -----------------------------------------------");
			System.out.println();
			return true;
		}
		System.out.println(
				"-------------------------------------------- Registeration Failed :( -----------------------------------------------------");
		System.out.println();

		return false;
	}
}
