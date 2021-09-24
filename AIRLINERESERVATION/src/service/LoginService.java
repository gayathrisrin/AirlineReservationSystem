package service;
import java.util.Scanner;

	import dao.DAOClass;
	import model.LoginUser;

	public class LoginService {

		public boolean checkUserExists() {
			System.out.println("");
			System.out.println(
					"####################################### Please Enter Your Login Details ########################################");
			System.out.println();

			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			LoginUser loginUser = new LoginUser();
			System.out.print("Enter Your User Name: ");
			loginUser.setUname(sc.next());
			System.out.print("Enter Your Password: ");
			loginUser.setPass(sc.next());

			DAOClass login = new DAOClass();
			boolean status = login.checkUserLogin(loginUser);
			if (status) {
				System.out.println("--------------------------------- Login Success :) --------------------------------");
				System.out.println("--------------------------------- WELCOME "+loginUser.getUname()+" --------------------------------");
				System.out.println();
				return true;
			}
			System.out.println("--------------------------------- Login Failed :( --------------------------------");
			System.out.println();
			return false;
		}
	

		

	}


