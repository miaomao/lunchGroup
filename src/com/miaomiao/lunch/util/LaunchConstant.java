package com.miaomiao.lunch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LaunchConstant {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/lunch";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "abc123_";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final String USER_TABLE = "users";
	public static final String USER_ID = "id";
	public static final String USER_NAME = "username";
	public static final String USER_PASSWORD = "password";
	public static final String USER_LEFTOVER = "leftover";
	public static final String USER_CREATED = "created";
	public static final String USER_UPDATED = "updated";
	public static final String USER_LAST_LOGIN = "lastlogin";
	public static final String USER_DELETED = "deleted";
	public static final String[] USER_COLUMNS = { USER_ID, USER_NAME,
			USER_PASSWORD, USER_LEFTOVER, USER_CREATED, USER_UPDATED,
			USER_LAST_LOGIN, USER_DELETED };

	public static final String EXPENSE_TYPE_TABLE = "expense_types";
	public static final String EXPENSE_TYPE_ID = "id";
	public static final String EXPENSE_TYPE_NAME = "name";
	public static final String EXPENSE_TYPE_ATTR = "attr";
	public static final String EXPENSE_TYPE_CREATED = "created";
	public static final String EXPENSE_TYPE_DELETED = "deleted";
	public static final String[] EXPNSE_TYPE_COLUMNS = { EXPENSE_TYPE_ID,
			EXPENSE_TYPE_NAME, EXPENSE_TYPE_ATTR, EXPENSE_TYPE_CREATED,
			EXPENSE_TYPE_DELETED };

	public static final String EXPENSE_ITEM_TABLE = "expense_items";
	public static final String EXPENSE_ITEM_ETYPEID = "eType_id";
	public static final String EXPENSE_ITEM_ACCOUNTID = "account_id";
	public static final String EXPENSE_ITEM_CREATED = "created";
	public static final String EXPENSE_ITEM_VALUE = "value";
}
