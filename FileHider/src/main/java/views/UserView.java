package views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data.Data;

public class UserView {
	private String email;

	UserView(String email) {
		this.email = email;
	}

	public void home() {
		do {
			System.out.println("Welcome " + email);
			System.out.println("press 1 to Show Hidden Files ");
			System.out.println("press 2 to hide a new File");
			System.out.println("press 3 to unhide a  File");
			System.out.println("press 0 Exit");
			Scanner sc = new Scanner(System.in);
			int ch = Integer.parseInt(sc.nextLine());
			switch (ch) {

			case 1:
				try {
					List<Data> files = DataDAO.getAllFiles(email);
					System.out.println("ID - File Name");
					for (Data file : files) {
						System.out.println(file.getId() + " - " + file.getFileName());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			case 2:
				System.out.print("Enter the file Path");
				String path = sc.nextLine();
				File f = new File(path);
				Data file = new Data(0, this.email, f.getPath(), f.getName());
				try {
					DataDAO.hideFile(file);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			case 3:
				List<Data> files = DataDAO.getAllFiles(email);
				try {
					System.out.println("ID - File Name");
					for (Data file1 : files) {
						System.out.println(file1.getId() + " - " + file1.getFileName());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Enter The id to Unhide ");
				int id = Integer.parseInt(sc.nextLine());
				boolean isValidId = false;
//				Data[] files;
				for (Data file1 : files) {
					if (file1.getId() == id) {
						isValidId = true;
						break;
					}
				}
				if (isValidId == true) {
					try {
						DataDAO.unhide(id);
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("Wrong Id");
				}

			case 0:
				System.exit(0);

			}

		} while (true);
	}
}
