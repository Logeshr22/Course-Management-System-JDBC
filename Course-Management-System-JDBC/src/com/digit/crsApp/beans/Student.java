package com.digit.crsApp.beans;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class Student {
	static int sid;
	static String sname;
	String email;
	static String user_name;
	static String password;
	int crsoptd;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public Student(int sid, String sname, String email, String user_name,String password,int crsOptd) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.email = email;
		this.user_name = user_name;
		this.password=password;
		this.crsoptd=crsOptd;
	}
	public int getCrsoptd() {
		return crsoptd;
	}
	public void setCrsoptd(int crsoptd) {
		this.crsoptd = crsoptd;
	}
	public String getPassword() {
		return password;
	}
	public  void setPassword(String password) {
		Student.password = password;
	}
	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}
	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public static String Slogin() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("---Student Login---");
			System.out.println("Enter your user_name");
			user_name=sc.next();
			System.out.println("Enter your password:");
			password=sc.next();
			String sql = "select * from users where user_name=? and password=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			if(res.next()==true) {
				return user_name;
			}
			else {
				return "";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
