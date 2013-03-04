package com.miaomiao.lunch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.ExpenseItemEntity;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseItemDAO extends BaseDAO {
	private String dbEITable = LaunchConstant.EXPENSE_ITEM_TABLE;
	private String dbEITypeId = LaunchConstant.EXPENSE_ITEM_ETYPEID;
	private String dbEIAccountId = LaunchConstant.EXPENSE_ITEM_ACCOUNTID;
	private String dbEICreated = LaunchConstant.EXPENSE_ITEM_CREATED;
	private String dbEIValue = LaunchConstant.EXPENSE_ITEM_VALUE;

	private String dbETTable = LaunchConstant.EXPENSE_TYPE_TABLE;
	private String dbETId = LaunchConstant.EXPENSE_TYPE_ID;
	private String dbETName = LaunchConstant.EXPENSE_TYPE_NAME;

	private String dbUTable = LaunchConstant.USER_TABLE;
	private String dbUId = LaunchConstant.USER_ID;
	private String dbUName = LaunchConstant.USER_NAME;

	/*
	 * convert resultlist to entitylist
	 */
	private List<ExpenseItemEntity> convertToEntity(
			List<Map<String, Object>> resultList) throws ParseException {
		List<ExpenseItemEntity> entityList = null;

		if (null != resultList && resultList.size() > 0) {
			entityList = new ArrayList<ExpenseItemEntity>();
			ExpenseItemEntity eItem;
			for (Map<String, Object> map : resultList) {
				eItem = new ExpenseItemEntity();
				Object obj = map.get(dbETName);
				eItem.seteTypeName(null == obj ? null : obj.toString());
				obj = map.get(dbUName);
				eItem.setAccountName(null == obj ? null : obj.toString());
				obj = map.get(dbEICreated);
				eItem.setCreated(null == obj ? null
						: LaunchConstant.DATE_FORMAT.parse(obj.toString()));
				obj = map.get(dbEIValue);
				eItem.setValue(null == obj ? 0.0f : Float.parseFloat(obj
						.toString()));

				entityList.add(eItem);
			}
		}
		return entityList;
	}

	/*
	 * generate table list
	 */
	private StringBuffer getTableList() {
		StringBuffer tableUnion = new StringBuffer("");
		tableUnion.append(" JOIN " + dbETTable);
		tableUnion.append(" ON ");
		tableUnion.append(dbEITable + "." + dbEITypeId + " = " + dbETTable
				+ "." + dbETId);
		tableUnion.append(" JOIN " + dbUTable);
		tableUnion.append(" ON ");
		tableUnion.append(dbEITable + "." + dbEIAccountId + " = " + dbUTable
				+ "." + dbUId);

		return tableUnion;
	}

	private StringBuffer getWhereCondition(List<String> accounts,
			List<String> types, String from, String to) {
		StringBuffer whereCondition = new StringBuffer("");
		whereCondition.append(" WHERE ");
		whereCondition.append(dbUTable + "." + dbUName + " IN (");
		for (String account : accounts) {
			whereCondition.append("'" + account + "' , ");
		}
		whereCondition.setCharAt(whereCondition.lastIndexOf(","), ' ');
		whereCondition.append(") AND ");
		whereCondition.append(dbETTable + "." + dbETName + " IN (");
		for (String type : types) {
			whereCondition.append("'" + type + "' , ");
		}
		whereCondition.setCharAt(whereCondition.lastIndexOf(","), ' ');
		whereCondition.append(") AND ");
		whereCondition.append(dbEITable + "." + dbEICreated + " BETWEEN '"
				+ from + "' AND '" + to + "'");

		return whereCondition;
	}

	public boolean insert(ExpenseItemEntity eItem) {
		boolean result = false;
		if (null != eItem) {
			Connection connection = getConnection();
			PreparedStatement statement = null;
			try {
				StringBuffer insertSql = new StringBuffer("INSERT INTO ");
				insertSql.append(dbEITable);
				insertSql.append(" SELECT ");
				insertSql.append(dbETTable + "." + dbETId + ", " + dbUTable
						+ "." + dbUId + ", ");
				insertSql.append(" ?, ? ");
				insertSql.append(" FROM ");
				insertSql.append(dbETTable + " JOIN " + dbUTable);
				insertSql.append(" WHERE ");
				insertSql.append(dbETTable + "." + dbETName + " = ");
				insertSql.append(" ? ");
				insertSql.append(" AND ");
				insertSql.append(dbUTable + "." + dbUName + " = ");
				insertSql.append(" ? ");

				statement = connection.prepareStatement(insertSql.toString());
				statement.setObject(1, eItem.getCreated());
				statement.setObject(2, eItem.getValue());
				statement.setObject(3, eItem.geteTypeName());
				statement.setObject(4, eItem.getAccountName());
				result = statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(statement, connection);
			}
		}
		return result;
	}

	/*
	 * default return value is -1. Either (1) the row count for SQL Data
	 * Manipulation Language (DML) statements or (2) 0 for SQL statements that
	 * return nothing
	 */
	public Integer update(List<String> accounts, List<String> types,
			String from, String to, Float value) throws ParseException {
		Integer result = -1;

		if (null == accounts || null == types || null == from || null == to) {
			return result;
		}

		Connection connection = getConnection();
		PreparedStatement statement = null;
		StringBuffer updateSql = new StringBuffer("");
		StringBuffer tables = getTableList();
		StringBuffer whereCondition = getWhereCondition(accounts, types, from,
				to);

		try {
			updateSql.append("UPDATE " + dbEITable);
			updateSql.append(tables);
			updateSql.append(" SET ");
			updateSql.append(dbEITable + "." + dbEIValue + " = ?");
			updateSql.append(whereCondition);
			System.out.println(updateSql.toString());
			statement = connection.prepareStatement(updateSql.toString());
			statement.setObject(1, value);
			result = statement.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void delete(List<String> accounts, List<String> types, String from,
			String to) {
		if (null == accounts || null == types || null == from || null == to) {
			return;
		}

		Connection connection = getConnection();
		Statement statement = null;
		StringBuffer deleteSql = new StringBuffer("");
		StringBuffer tables = getTableList();
		StringBuffer whereCondition = getWhereCondition(accounts, types, from,
				to);

		try {
			deleteSql.append("DELETE " + dbEITable + " FROM " + dbEITable);
			deleteSql.append(tables);
			deleteSql.append(whereCondition);
			statement = connection.createStatement();
			statement.executeUpdate(deleteSql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ExpenseItemEntity> searchByUserDateType(List<String> accounts,
			List<String> types, String from, String to) throws ParseException {
		List<ExpenseItemEntity> entityList = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ResultSet rs;
		ResultSetMetaData md;
		Connection connection = getConnection();
		Statement statement = null;

		if (null == accounts || null == types || null == from || null == to) {
			return entityList;
		}

		try {
			StringBuffer querySql = new StringBuffer("SELECT ");
			StringBuffer tables = getTableList();
			StringBuffer whereCondition = getWhereCondition(accounts, types,
					from, to);
			querySql.append(dbETTable + "." + dbETName + ", " + dbUTable + "."
					+ dbUName + ", " + dbEITable + "." + dbEICreated + ", "
					+ dbEITable + "." + dbEIValue);
			querySql.append(" FROM " + dbEITable);
			querySql.append(tables);
			querySql.append(whereCondition);
			System.out.println(querySql.toString());
			statement = connection.createStatement();
			rs = statement.executeQuery(querySql.toString());
			md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			Map<String, Object> rowData;
			while (rs.next()) {
				rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				resultList.add(rowData);
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}

		entityList = convertToEntity(resultList);
		return entityList;
	}
}