package com.miaomiao.lunch.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.UserEntity;
import com.miaomiao.lunch.util.BaseUtil;
import com.miaomiao.lunch.util.LaunchConstant;

public class UserDAO extends BaseDAO {
	private String dbUTable = LaunchConstant.USER_TABLE;
	private String dbUId = LaunchConstant.USER_ID;
	private String dbUName = LaunchConstant.USER_NAME;
	private String dbUPassword = LaunchConstant.USER_PASSWORD;
	private String dbULeftover = LaunchConstant.USER_LEFTOVER;
	private String dbUCreated = LaunchConstant.USER_CREATED;
	private String dbUUpdated = LaunchConstant.USER_UPDATED;
	private String dbULastLogin = LaunchConstant.USER_LAST_LOGIN;
	private String dbUDeleted = LaunchConstant.USER_DELETED;
	private String[] dbUColumns = LaunchConstant.USER_COLUMNS;
	private DateFormat df = LaunchConstant.DATE_FORMAT;

	/*
	 * default return value is -1. Either (1) the row count for SQL Data
	 * Manipulation Language (DML) statements or (2) 0 for SQL statements that
	 * return nothing
	 */
	public Integer insert(UserEntity user) {
		Integer result = -1;

		if (null != user) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(dbUName, user.getUserName());
			map.put(dbUPassword, user.getPassword());
			map.put(dbUCreated, user.getCreated());
			map.put(dbULeftover, user.getLeftOver());
			map.put(dbUUpdated, user.getUpdated());
			map.put(dbULastLogin, user.getLastLogin());

			result = super.insert(dbUTable, map);
		}

		return result;
	}

	public Integer updateUserName(String name, Integer id) {
		Integer result = -1;

		if (null != name || 0 <= id) {
			Map<String, Object> value = new HashMap<String, Object>();
			Map<String, Object> condition = new HashMap<String, Object>();
			value.put(dbUName, name);
			value.put(dbUUpdated, new Date());
			condition.put(dbUId, id);

			result = update(dbUTable, value, condition);
		}

		return result;
	}

	public Integer updatePassword(String password, Integer id) {
		Integer result = -1;

		if (null != password || 0 <= id) {
			Map<String, Object> value = new HashMap<String, Object>();
			Map<String, Object> condition = new HashMap<String, Object>();
			value.put(dbUPassword, password);
			value.put(dbUUpdated, new Date());
			condition.put(dbUId, id);

			result = update(dbUTable, value, condition);
		}

		return result;
	}

	public Integer updateLastLogin(Integer id) {
		Integer result = -1;

		if (0 <= id) {
			Map<String, Object> value = new HashMap<String, Object>();
			Map<String, Object> condition = new HashMap<String, Object>();
			value.put(dbULastLogin, new Date());
			condition.put(dbUId, id);

			result = update(dbUTable, value, condition);
		}

		return result;
	}

	public Integer delete(Integer id) {
		Integer result = -1;

		if (0 <= id) {
			Map<String, Object> value = new HashMap<String, Object>();
			Map<String, Object> condition = new HashMap<String, Object>();
			value.put(dbUDeleted, 1);
			value.put(dbUUpdated, new Date());
			condition.put(dbUId, id);

			result = update(dbUTable, value, condition);
		}

		return result;
	}

	private List<UserEntity> convertToEntity(
			List<Map<String, Object>> resultList) throws ParseException {
		List<UserEntity> entityList = null;

		if (null != resultList && resultList.size() > 0) {
			entityList = new ArrayList<UserEntity>();
			UserEntity user;
			for (Map<String, Object> map : resultList) {
				user = new UserEntity();
				Object obj = map.get(dbUId);
				user.setId(null == obj ? null
						: Integer.parseInt(obj.toString()));
				obj = map.get(dbUName);
				user.setUserName(null == obj ? null : obj.toString());
				obj = map.get(dbUPassword);
				user.setPassword(null == obj ? null : obj.toString());
				obj = map.get(dbULeftover);
				user.setLeftOver(null == obj ? 0.0f : Float.parseFloat(obj
						.toString()));
				obj = map.get(dbUCreated);
				user.setCreated(null == obj ? null : df.parse(obj.toString()));
				obj = map.get(dbUUpdated);
				user.setUpdated(null == obj ? null : df.parse(obj.toString()));
				obj = map.get(dbULastLogin);
				user.setLastLogin(null == obj ? null : df.parse(obj.toString()));
				obj = map.get(dbUDeleted);
				user.setDeleted(null == obj ? null : Integer.parseInt(obj
						.toString()));

				entityList.add(user);
			}
		}
		return entityList;
	}

	public UserEntity searchById(Integer id) throws ParseException {
		UserEntity user = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnName = BaseUtil.convertToList(dbUColumns);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(dbUId, id);
		resultList = super.search(dbUTable, columnName, condition);

		if (resultList.size() > 0) {
			List<UserEntity> entityList = new ArrayList<UserEntity>();
			entityList = convertToEntity(resultList);
			user = entityList.get(0);
		}

		return user;
	}

	public UserEntity searchByName(String name) throws ParseException {
		UserEntity user = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnName = BaseUtil.convertToList(dbUColumns);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(dbUName, name);
		resultList = super.search(dbUTable, columnName, condition);

		if (resultList.size() > 0) {
			List<UserEntity> entityList = new ArrayList<UserEntity>();
			entityList = convertToEntity(resultList);
			user = entityList.get(0);
		}

		return user;
	}
}