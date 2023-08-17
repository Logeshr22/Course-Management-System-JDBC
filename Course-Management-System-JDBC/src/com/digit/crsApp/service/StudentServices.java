package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class StudentServices {
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	String loginStudName;
	public void menu(String studName ) {
		loginStudName=studName;
		System.out.println();
		System.out.println("Select Option:");
		System.out.println("1. View Mark\n" + "2. View Score Card\n" + "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			try {
				viewMarks();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 2:
		{
			try {
				viewScoreCard();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		case 0: {
			break;
		}
		default:
			System.out.println("-----Thank you For Using Digit Course Management System :) -----");
			break;
		}
	}

	public void viewScoreCard() throws SQLException {
//		int crsIdOfStud = 1;
//		String crsOfProf = "select sid from student where sname=?";
//		pstmt = CRSApp.con.prepareStatement(crsOfProf);
//		pstmt.setString(1, loginStudName);
//		resultSet = pstmt.executeQuery();
//		while (resultSet.next() == true) {
//			crsIdOfStud = resultSet.getInt("sid");
//		}
		String sql = "select * from Student where user_name=?";
		try {
//			System.out.println(loginStudName);
			System.out.println("Your ScoreCard "+loginStudName);
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1,loginStudName);
			resultSet = pstmt.executeQuery();
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				if(loginStudName.equals(resultSet.getString(4)) )
				{
					System.out.println("Student ID: "+ resultSet.getInt(1));
					System.out.println("Student Name: "+ resultSet.getString(2));
					System.out.println("Student Email: "+ resultSet.getString(3));
					System.out.println("Student User_Name: "+ resultSet.getString(4));
					System.out.println("Mark secured: "+ resultSet.getInt(7));
					System.out.println("Grade Secured:"+resultSet.getString(8));
					System.out.println("_-_-_-_-_-_-_-_-_-_-");
				}
				
			}
			
			menu(loginStudName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void viewMarks() throws SQLException {
//		int crsIdOfStud = 1;
//		String crsOfProf = "select sid from student where sname=?";
//		pstmt = CRSApp.con.prepareStatement(crsOfProf);
//		pstmt.setString(1, loginStudName);
//		resultSet = pstmt.executeQuery();
//		while (resultSet.next() == true) {
//			crsIdOfStud = resultSet.getInt("sid");
//		}
		String sql = "select * from Student where user_name=?";
		try {
//			System.out.println(loginStudName);
			System.out.println("Your Mark Scored "+loginStudName);
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1,loginStudName);
			resultSet = pstmt.executeQuery();
			System.out.println("_-_-_-_-_-_-_-_-_-_-");
			while (resultSet.next() == true) {
				if(loginStudName.equals(resultSet.getString(4)) )
				{
					System.out.println("Student ID: "+ resultSet.getInt(1));
					System.out.println("Student Name: "+ resultSet.getString(2));
					System.out.println("Mark secured: "+ resultSet.getInt(7));
					System.out.println("_-_-_-_-_-_-_-_-_-_-");
				}
				
			}
			
			menu(loginStudName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
