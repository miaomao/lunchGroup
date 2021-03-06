package com.miaomiao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import com.miaomiao.lunch.dao.ExpenseTypeDAO;
import com.miaomiao.lunch.entities.ExpenseTypeEntity;

public class TestJDBC {

	/**
	 * @param args
	 * @throws ParseException
	 */

	public static void main(String[] args) throws ParseException {
//		List<String> accounts = new ArrayList<String>();
//		accounts.add("michaela3");
//		accounts.add("michaela2");
//		List<String> types = new ArrayList<String>();
//		types.add("eat");
//		types.add("play");
//		String from = "2013-02-11";
//		String to = "2013-02-25";
//		ExpenseItemDAO eDAO = new ExpenseItemDAO();
//		//eDAO.delete(accounts, types, from, to);
//		System.out.println(eDAO.update(accounts, types, from, to, 0.02f));
		
ExpenseTypeEntity e = new ExpenseTypeEntity();
e.setName("testadd2");
e.setAttr("+");
ExpenseTypeDAO ed = new ExpenseTypeDAO();

System.out.println( ed.insert(e));
		
        
        
		//
		// eDAO.deleteWithArgs(eType);

		// ExpenseTypeEntity eType = new ExpenseTypeEntity();
		// ExpenseTypeDAO eDAO = new ExpenseTypeDAO();
		// List<ExpenseTypeEntity> list = eDAO.searchByAttr("*");
		// for(ExpenseTypeEntity eType: list){
		// System.out.println("ID : " + eType.getId());
		// System.out.println("Name :" + eType.getName());
		// }

		// System.out.println(null != eType? eType.getName():null);
		//
		// UserDAO uDAO = new UserDAO();
		// uDAO.delete(1);
	}

	// public static void main(String[] args) {
	// BaseDAO bDAO = new BaseDAO();
	// String sql = "select * from users";
	// List<Map<String, Object>> results = bDAO.search(sql);
	// Map<String, Object> tempMap = null;
	// StringBuffer tempStr = null;
	// for(int i = 0; i < results.size(); i++) {
	// tempMap = results.get(i);
	// if(null != tempMap && !tempMap.isEmpty()) {
	// tempStr = new StringBuffer("");
	// for(String key : tempMap.keySet()) {
	// tempStr.append(key);
	// tempStr.append( ":");
	// tempStr.append(tempMap.get(key).toString());
	// tempStr.append("\t");
	// }
	// System.out.println(tempStr.toString());
	// }
	// }
	// }

	public static void testSearch() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/lunch";
		String user = "miaomiao";
		String password = "abc123_";

		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "select * from users";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void testSave() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/lunch";
		String user = "miaomiao";
		String password = "abc123_";

		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "insert into users (username,password,created) values('michaela2', 'abc123_', NOW());";
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
