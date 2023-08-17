	package com.digit.crsApp.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class Professor {
	static int pid;
	static String Password;
	static String pname;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	int exp;
	int crsOptd;
	public Professor(int pid, String pname, int exp, String password,int crsOptd) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
		this.Password= password;
		this.crsOptd = crsOptd;
	}
	public int getCrsOpt() {
		return crsOptd;
	}
	public void setCrsOpt(int crsOptd) {
		this.crsOptd = crsOptd;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public static String plogin() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("---Professor Login---");
			System.out.println("Enter your Name");
			pname=sc.next();
			System.out.println("Enter your password:");
			Password = sc.next();
			String sql = "select * from users where user_name=? and password=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			pstmt.setString(1, pname);
			pstmt.setString(2, Password);
			res = pstmt.executeQuery();
			if(res.next()==true) {
				return pname;
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
