package com.crsApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.crsApp.beans.Professor;
import com.digit.crsApp.beans.Student;
import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;
import com.digit.crsApp.service.ProfessorServices;
import com.digit.crsApp.service.StudentServices;

public class CRSApp {
	public static Connection con;

	public static void main(String[] args) throws Exception {
		System.out.println();
		System.out.println("----- Welcome to Digit Course Management System :)-----");
		System.out.println();
		System.out.println("Select the Type of User you are:");
		System.out.println("1. Admin\n" + "2. Professor\n" + "3. Student\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
//		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/crs";

		String user = "root";
		String pwd = "KKsahu@8763";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);

		switch (n) {
		case 1: {
			boolean b = Users.login();
			if (b == true) {
				System.out.println("Admin Login Success");
				AdminServices adsrv = new AdminServices();
				adsrv.menu();
			} 
			else {
				System.out.println("Admin Login Failed");
				main(null);
			}
			break;
		}

		case 2: {
			String b = Professor.plogin();
			if (b != "") {
				System.out.println("Professor Login Success");
				ProfessorServices pfsrv = new ProfessorServices();
				pfsrv.menu(b);
			} 
			else {
				System.out.println("professor Login Failed");
				main(null);
			}
			break;
		}
		case 3: {
			String  b = Student.Slogin();
			if (b != "") {
				System.out.println("Student Login Success");
				StudentServices stsrv = new StudentServices();
				stsrv.menu(b);
			} 
			else {
				System.out.println("Student Login Failed");
				main(null);
			}
			break;
		}
		default:
			System.out.println("-----Thank you For Using Digit Course Management System :) -----");
			break;
		}
	}
}
