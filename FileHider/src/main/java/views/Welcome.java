package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import Service.GenerateOTP;
import Service.SendOTPService;
import Service.UserService;
import dao.UserDAO;
import model.User.User;

public class Welcome {

	public void welcomeScreen() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome To the App");
		System.out.println("Press 1 To login");
		System.out.println("Press 2 To SingUp");
		System.out.println("Press 0 To Exit ");
		int choice = 0;
		try {
			String input = br.readLine();
			if (input != null && !input.isEmpty()) {
				choice = Integer.parseInt(input);
			} else {
				System.out.print("Please Enter Valid Choice ");
			}

		} catch (IOException e) {
//			System.out.print("Please Enter Valid Choice ");
//			choice = 0;
			e.printStackTrace();
		} catch (NumberFormatException e) {
//			System.out.print("Please Enter Valid Choice ");
//			choice = 0;
			e.printStackTrace();
		}

		switch (choice) {
		case 1:
			login();
			break;

		case 2:
			signUp();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("Enter Valid Choice");
		}

	}

	private void signUp() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Name");
		String name = sc.nextLine();

		System.out.println("Enter Email");
		String email = sc.nextLine();

		String genOTP = GenerateOTP.getOTP();
		System.out.println(genOTP);
		SendOTPService.sendOTP(email, genOTP);
		System.out.println("Enter OTP");
		String otp = sc.nextLine();

		if (otp.equals(genOTP)) {
			User user = new User(name, email);
			if (UserService.saveUser(user) != 0) {
				System.out.println("User Resigtered ");
			} else {
				System.out.println("User is already Present");

			}

		} else {
			System.out.println("Wrong OTP ");
		}
	}

//		return;

	private void login() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Email");
		String email = sc.nextLine();
		try {
			if (UserDAO.isExists(email)) {
				String genOTP = GenerateOTP.getOTP();
				SendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter The OTP");
				String otp = sc.nextLine();
				if (otp.equals(genOTP)) {
					System.out.println("Welcome");
				} else {
					System.out.println("Wrong OTP");
				}

			} else {
				System.out.println("User Not Exist");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
