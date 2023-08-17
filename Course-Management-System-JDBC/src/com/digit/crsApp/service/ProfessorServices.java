package com.digit.crsApp.service;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class ProfessorServices {
	private Statement stmt;
	private ResultSet resultSet;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;

	String loginProfName;

	public void menu(String profName) {
		loginProfName = profName;
		System.out.println();
		System.out.println("Select Option:");
		System.out.println(
				"1. View All Students\n" + "2. Assign mark to Students\n" + "3. Grade Students\n"+ "4. View Students Grade\n"+ "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			viewAllStud();
			break;
		}
		case 2: {
			markStudents();
			break;
		}

		case 3: {
			Gradestudents();
			break;
		}
		case 4: {
			viewStudentGrades();
			break;
		}
		case 0: {
			System.out.println("-----Thank you For Using Digit Course Management System :) -----");
			break;
		}
		default:
			System.out.println("-----Thank you For Using Digit Course Management System :) -----");
			break;
		}
	}

	public void viewStudentGrades() {

		int crsIdOfProf = 1;
		try {
			System.out.println();
			System.out.println("Registered Students Grades under Your Course " + loginProfName);
			String crsOfProf = "select cid from professor where pname=?";
			pstmt = CRSApp.con.prepareStatement(crsOfProf);
			pstmt.setString(1, loginProfName);
			resultSet = pstmt.executeQuery();
			while (resultSet.next() == true) {
				crsIdOfProf = resultSet.getInt("cid");
			}
			String sql = "select * from Student where cid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, crsIdOfProf);
			resultSet = pstmt.executeQuery();
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Student ID: " + resultSet.getInt(1));
				System.out.println("Student Name: " + resultSet.getString(2));
				System.out.println("Student Mark: " + resultSet.getString(7));
				System.out.println("Student Grade: " + resultSet.getString(8));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu(loginProfName);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void Gradestudents() {
		String sql1 = "update student set grade=? where mark between ? and ?";

		try {

			pstmt1 = CRSApp.con.prepareStatement(sql1);

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter the total no of grades you want:");

			int n = sc.nextInt();

			for (int i = 1; i <= n; i++) {

				System.out.println("Enter grade for Range:");

				pstmt1.setString(1, sc.next());

				System.out.println("Enter lower mark Limit: ");

				pstmt1.setInt(2, sc.nextInt());

				System.out.println("Enter Upper mark Limit: ");
				pstmt1.setInt(3, sc.nextInt());

				pstmt1.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		menu(loginProfName);
	}

	public void markStudents() {
		int crsIdOfProf = 1;
		int mark_of_stud = -1;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println();

			String crsOfProf = "select cid from professor where pname=?";
			pstmt = CRSApp.con.prepareStatement(crsOfProf);
			pstmt.setString(1, loginProfName);
			resultSet = pstmt.executeQuery();
			while (resultSet.next() == true) {
				crsIdOfProf = resultSet.getInt("cid");
			}
			String sql = "select * from Student where cid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, crsIdOfProf);
			resultSet = pstmt.executeQuery();
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				if (resultSet.getInt(7) == 0) {
					System.out.println("Student ID: " + resultSet.getInt(1));
					System.out.println("Student Name: " + resultSet.getString(2));
					System.out.println("Student Email: " + resultSet.getString(3));
					mark_of_stud = resultSet.getInt(7);
					System.out.println("_-_-_-_-_-_-_-_-_-_-");
				}
			}
			boolean con = true;
			if (mark_of_stud == 0) {
				while (con != false) {
					System.out.println("Students to be graded under Your Course " + loginProfName);
					String sql3 = "update student set mark=? where sid=?";

					pstmt = CRSApp.con.prepareStatement(sql3);
					System.out.println();
					System.out.println("Enter the student Id to be graded:");
					pstmt.setInt(2, sc.nextInt());
					System.out.println("Enter Marks to be graded:");
					pstmt.setInt(1, sc.nextInt());
					int x = pstmt.executeUpdate();
					System.out.println("Press 1 to Grade Student:");
					System.out.println("Press 0 to Exit");
					int usr_res = sc.nextInt();
					if (usr_res == 0) {
						con = false;
					}
				}
				menu(loginProfName);
			} else if (mark_of_stud != 0) {
				System.out.println("All Students are already Graded");
				menu(loginProfName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewAllStud() {
		int crsIdOfProf = 1;
		try {
			System.out.println();
			System.out.println("Students under Your Course " + loginProfName);
			String crsOfProf = "select cid from professor where pname=?";
			pstmt = CRSApp.con.prepareStatement(crsOfProf);
			pstmt.setString(1, loginProfName);
			resultSet = pstmt.executeQuery();
			while (resultSet.next() == true) {
				crsIdOfProf = resultSet.getInt("cid");
			}
			String sql = "select * from Student where cid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setInt(1, crsIdOfProf);
			resultSet = pstmt.executeQuery();
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Student ID: " + resultSet.getInt(1));
				System.out.println("Student Name: " + resultSet.getString(2));
				System.out.println("Student Email: " + resultSet.getString(3));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu(loginProfName);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
