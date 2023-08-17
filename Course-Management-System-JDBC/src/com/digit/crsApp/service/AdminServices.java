package com.digit.crsApp.service;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;
import com.digit.crsApp.beans.Course;
import com.digit.crsApp.beans.Professor;
import com.digit.crsApp.beans.Student;

public class AdminServices {

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;

	public void menu() {
		System.out.println();
		System.out.println("Select any of the following Options to perform specific operation:");
		System.out.println();
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "4. Remove Course\n"
				+ "5. Remove Professor\n" + "6. Remove Student\n" + "7. View All Students\n" + "8. View All Courses\n"
				+ "9. View All Professors\n" + "10. View All Users\n" + "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;
		}
		case 2:
		{
			addStudent();
			break;
		}
		case 3:{
			addProfessor();
			break;
		}
		case 4: {
			rmvCourse();
			break;
		}
		case 5:{
			rmvProfessor();
			break;
		}
		case 6:
		{
			rmvStudent();
			break;
		}
		case 7:
		{
			viewAllStudents();
			break;
		}
		case 8: {
			viewAllCourses();
			break;
		}
		case 9:
		{
			viewAllProfessors();
			break;
		}
		case 10:
		{
			viewAllUsers();
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

//	public void addUser()
//	{
//		
//	}

	public void addCourse() {
		try {
			int id;
			String crsName;
			int fees;
			int duration;
			System.out.println();
			String sql = "insert into course values(?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("To insert New Course:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the course id:");
			id = sc.nextInt();
			System.out.println("Enter the course name:");
			crsName = sc.next();
			System.out.println("Enter the course fees:");
			fees = sc.nextInt();
			System.out.println("Enter the course duration in months:");
			duration = sc.nextInt();
			Course c = new Course(id, crsName, fees, duration);
			pstmt.setInt(1, c.getCid());
			pstmt.setString(2, c.getCname());
			pstmt.setInt(3, c.getFees());
			pstmt.setInt(4, c.getDur_months());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Course Added is :" + c.getCname());
				menu();
			}
			else {
				System.out.println("Course addition failed");
				menu();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addStudent() {
		try {
			int id;
			String name;
			String email;
			String user_name;
			String password;
			int crsOptd;
			System.out.println();
			String sql = "insert into student values(?,?,?,?,?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("To insert New Student:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Student's id:");
			id = sc.nextInt();
			System.out.println("Enter the Student's name:");
			name = sc.next();
			System.out.println("Enter the Studen's Email:");
			email = sc.next();
			System.out.println("Enter Student's username:");
			user_name = sc.next();
			System.out.println("Enter your Password:");
			password = sc.next();
			String avlCrs = "select * from course";
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(avlCrs);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Course ID: "+ resultSet.getInt(1));
				System.out.println("Course Name: "+ resultSet.getString(2));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			System.out.println("Enter the course Id you wantto opt:");
			crsOptd = sc.nextInt();
			Student  s = new Student(id, name, email, user_name,password,crsOptd);
			int intMark=0;
			pstmt.setInt(1, s.getSid());
			pstmt.setString(2, s.getSname());
			pstmt.setString(3, s.getEmail());
			pstmt.setString(4, s.getUser_name());
			pstmt.setString(5, s.getPassword());
			pstmt.setInt(6, s.getCrsoptd());
			pstmt.setInt(7, 0);
			pstmt.setString(8, "NA");
			
			int x = pstmt.executeUpdate();
			
			String sql1 = "insert into users values(?,?)";
			pstmt = CRSApp.con.prepareStatement(sql1);
			pstmt.setString(1, s.getUser_name());
			pstmt.setString(2, s.getPassword());
			
			x = pstmt.executeUpdate();
			
			if (x > 0) {
				System.out.println("Student  Added is :" + s.getSname());
				menu();
			}
			else {
				System.out.println("Course addition failed");
				menu();
		}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void addProfessor() {
		try {
			int id;
			String name;
			int exp;
			String password;
			System.out.println();
			String sql = "insert into professor values(?,?,?,?,?)";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("To insert New Professor:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Professor's id:");
			id = sc.nextInt();
			System.out.println("Enter professor's name:");
			name = sc.next();
			System.out.println("Enter professor's experience:");
			exp = sc.nextInt();
			System.out.println("Enter your Password:");
			password = sc.next();
			String avlCrs = "select * from course";
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(avlCrs);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Course ID: "+ resultSet.getInt(1));
				System.out.println("Course Name: "+ resultSet.getString(2));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			System.out.println("Enter the course Id you want to Teach:");
			int crsOptd = sc.nextInt();
			Professor  p = new Professor(id, name, exp,password,crsOptd);
			pstmt.setInt(1, p.getPid());
			pstmt.setString(2, p.getPname());
			pstmt.setInt(3, p.getExp());
			pstmt.setString(4, p.getPassword());
			pstmt.setInt(5, p.getCrsOpt());
			
			int x = pstmt.executeUpdate();
			String sql1 = "insert into users values(?,?)";
			pstmt = CRSApp.con.prepareStatement(sql1);
			pstmt.setString(1, p.getPname());
			pstmt.setString(2, p.getPassword());
			
			x = pstmt.executeUpdate();
			
			if (x > 0) {
				System.out.println("Student  Added is :" + p.getPname());
				menu();
			}
			else {
				System.out.println("Course addition failed");
				menu();
		}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	

	public void rmvCourse() {
		String sql = "delete from course where cid=?";
		try {
			System.out.println("To delete existing course:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter the course id to be deleted:");
			pstmt.setInt(1, sc.nextInt());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Course Deleted");
				menu();
			} else {
				System.out.println("Data Deletion failed");
				menu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rmvStudent() {
		
		try {
			String sql = "delete from student where sid=?";
			System.out.println("To delete existing Student:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter the Student id to be deleted:");
			pstmt.setInt(1, sc.nextInt());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Student deleted");
				menu();
			} else {
				System.out.println("Data Deletion failed");
				menu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rmvProfessor() {
		String sql = "delete from professor where pid=?";
		try {
			System.out.println("To delete existing Professor:");
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			Scanner sc = new Scanner(System.in);
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("Enter the Professor id to be deleted:");
			pstmt.setInt(1, sc.nextInt());

			int x = pstmt.executeUpdate();
			if (x > 0) {
				System.out.println("Professor deleted");
				menu();
			} else {
				System.out.println("Data Deletion failed");
				menu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewAllCourses() {
		System.out.println();
		System.out.println("Details of all courses present:");
		String sql = "select * from course";
		try {
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Course ID: "+ resultSet.getInt(1));
				System.out.println("Course Name: "+ resultSet.getString(2));
				System.out.println("Course Fees: "+ resultSet.getInt(3));
				System.out.println("Courde Duration: "+ resultSet.getInt(4));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewAllStudents() {
		System.out.println();
		System.out.println("Details of all Students present:");
		String sql = "select * from student";
		try {
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Student ID: "+ resultSet.getInt(1));
				System.out.println("Student Name: "+ resultSet.getString(2));
				System.out.println("Student Email: "+ resultSet.getString(3));
				System.out.println("Student User_Name: "+ resultSet.getString(4));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void viewAllProfessors() {
		System.out.println();
		System.out.println("Details of all Professors present:");
		String sql = "select * from professor";
		try {
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("Professor ID: "+ resultSet.getInt(1));
				System.out.println("Professor Name: "+ resultSet.getString(2));
				System.out.println("Professor Exp: "+ resultSet.getInt(3));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewAllUsers() {
		System.out.println();
		System.out.println("Details of all Users present:");
		String sql = "select * from users";
		try {
			stmt = CRSApp.con.createStatement();
			resultSet = stmt.executeQuery(sql);
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				System.out.println("User Name: "+ resultSet.getString(1));
				System.out.println("_-_-_-_-_-_-_-_-_-_-");
			}
			menu();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
