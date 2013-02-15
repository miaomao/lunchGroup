package com.miaomiao.lunch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.util.LaunchConstant;

public class BaseDAO {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(LaunchConstant.DB_DRIVER);
			conn = DriverManager.getConnection(LaunchConstant.DB_URL,
					LaunchConstant.DB_USER, LaunchConstant.DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Statement smt, Connection conn) {
		try {
			if (null != smt) {
				smt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean insert(String tableName, Map<String, Object> map) {
		boolean result = false;
		if (null == map || map.isEmpty() || null == tableName
				|| "".equals(tableName)) {
			return result;
		}
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			StringBuffer insertSql = new StringBuffer("INSERT INTO ");
			StringBuffer valueSql = new StringBuffer(" VALUES ( ");
			insertSql.append(tableName);
			insertSql.append(" (");
			for (String key : map.keySet()) {
				insertSql.append(key + ", ");
				valueSql.append("?, ");
			}
			insertSql.setCharAt(insertSql.lastIndexOf(","), ')');
			valueSql.setCharAt(valueSql.lastIndexOf(","), ')');
			insertSql.append(valueSql);

			statement = connection.prepareStatement(insertSql.toString());
			int i = 1;
			for (String key : map.keySet()) {
				statement.setObject(i, map.get(key));
				i++;
			}
			result = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return result;
	}

	public boolean update(String tableName, Map<String, Object> value,
			Map<String, Object> condition) {
		boolean result = false;
		if (null == tableName || "".equals(tableName) || null == value
				|| value.isEmpty()) {
			return result;
		}
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			StringBuffer updateSql = new StringBuffer("UPDATE ");
			updateSql.append(tableName);
			updateSql.append(" SET ");
			for (String key : value.keySet()) {
				updateSql.append(key + " = " + "?" + " , ");
			}
			updateSql.setCharAt(updateSql.lastIndexOf(","), ' ');
			if (null != condition && !condition.isEmpty()) {
				updateSql.append("WHERE ");
				for (String key : condition.keySet()) {
					updateSql.append(key + " = " + "?" + " AND ");
				}
				updateSql.replace(updateSql.lastIndexOf("AND"),
						updateSql.lastIndexOf(" "), "");
			}
			statement = connection.prepareStatement(updateSql.toString());

			int i = 1;
			for (String key : value.keySet()) {
				statement.setObject(i, value.get(key));
				i++;
			}
			for (String key : condition.keySet()) {
				statement.setObject(i, condition.get(key));
				i++;
			}

			result = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return result;
	}

	public boolean excute(String sql) {
		boolean result = false;

		Connection connection = getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute(sql);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return result;
	}

	public boolean executeWithArgs(String sql, Object[] args) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		if (null != sql && !"".equals(sql)) {
			conn = getConnection();
			try {
				pst = conn.prepareStatement(sql);
				if (null != args) {
					int i = 1;
					for (Object arg : args) {
						pst.setObject(i, arg);
						i++;
					}
				}
				result = pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Map<String, Object>> search(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet rs;
		ResultSetMetaData md;

		Connection connection = getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			Map<String, Object> rowData;
			while (rs.next()) {
				rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return list;
	}

	public List<Map<String, Object>> search(String tableName,
			ArrayList<String> columnName, Map<String, Object> condition) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet rs;
		ResultSetMetaData md;

		if (null == tableName || "".equals(tableName) || null == columnName
				|| columnName.isEmpty()) {
			return list;
		}

		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			StringBuffer querySql = new StringBuffer("SELECT ");
			for (String column : columnName) {
				querySql.append(column + ",");
			}
			querySql.setCharAt(querySql.lastIndexOf(","), ' ');
			querySql.append(" FROM " + tableName);
			if (null != condition && !condition.isEmpty()) {
				querySql.append(" WHERE ");

				for (String key : condition.keySet()) {
					querySql.append(key + " = " + "?" + " AND ");
				}
				querySql.replace(querySql.lastIndexOf("AND"),
						querySql.lastIndexOf(" "), "");
			}
			statement = connection.prepareStatement(querySql.toString());

			int i = 1;
			for (String key : condition.keySet()) {
				statement.setObject(i, condition.get(key));
				i++;
			}

			rs = statement.executeQuery();
			md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			Map<String, Object> rowData;
			while (rs.next()) {
				rowData = new HashMap<String, Object>();
				for (int j = 1; j <= columnCount; j++) {
					rowData.put(md.getColumnName(j), rs.getObject(j));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}

		return list;
	}

	public boolean deleteById(String tableName, String columnName, Integer id) {
		boolean result = false;

		if (null != tableName && null != columnName && null != id) {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM ");
			sql.append(tableName);
			sql.append(" WHERE ");
			sql.append(columnName);
			sql.append(" = " + " ? ");
			System.out.println(sql);
			Object[] args = new Object[1];
			args[0] = id;
			result = executeWithArgs(sql.toString(), args);
		}
		return result;
	}
}