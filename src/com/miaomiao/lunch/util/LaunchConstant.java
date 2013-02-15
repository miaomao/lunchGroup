package com.miaomiao.lunch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LaunchConstant {

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/lunch";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "abc123_";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"YYYY-MM-dd");

	public static final String USERS_TABLE = "users";
	public static final String USERS_ID = "id";
	public static final String USERS_NAME = "username";
	public static final String USERS_PASSWORD = "password";
	public static final String USERS_LEFTOVER = "leftover";
	public static final String USERS_CREATED = "created";
	public static final String[] USER_COLUMNS = {USERS_ID, USERS_NAME, USERS_PASSWORD, USERS_LEFTOVER, USERS_CREATED};
	
	public static final String EXPENSE_TYPE_TABLE = "expense_types";
	public static final String EXPENSE_TYPE_ID = "id";
	public static final String EXPENSE_TYPE_NAME = "name";
	public static final String EXPENSE_TYPE_ATTR = "attr";
	public static final String[] EXPNSE_TYPE_COLUMNS = {EXPENSE_TYPE_ID, EXPENSE_TYPE_NAME, EXPENSE_TYPE_ATTR};

	public static final String EXPENSE_ITEM_TABLE = "expense_items";
	public static final String EXPENSE_ITEM_ETYPEID = "eType_id";
	public static final String EXPENSE_ITEM_ACCOUNTID = "account_id";
	public static final String EXPENSE_ITEM_CREATED = "created";
	public static final String EXPENSE_ITEM_VALUE = "value";

}
