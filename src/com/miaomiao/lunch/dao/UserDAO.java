package com.miaomiao.lunch.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.UserEntity;
import com.miaomiao.lunch.util.BaseUtil;
import com.miaomiao.lunch.util.LaunchConstant;

public class UserDAO extends BaseDAO {
	public boolean insert(UserEntity user) {
		boolean result = false;
		if (null != user) {
			String tableName = LaunchConstant.USERS_TABLE;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(LaunchConstant.USERS_NAME, user.getUserName());
			map.put(LaunchConstant.USERS_PASSWORD, user.getPassword());
			map.put(LaunchConstant.USERS_LEFTOVER, user.getLeftOver());
			map.put(LaunchConstant.USERS_CREATED, user.getCreated());
			result = super.insert(tableName, map);
		}
		return result;
	}

	public boolean updateById(UserEntity user) {
		boolean result = false;
		String tableName = LaunchConstant.USERS_TABLE;
		Map<String, Object> value = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();

		value.put(LaunchConstant.USERS_NAME, user.getUserName());
		value.put(LaunchConstant.USERS_PASSWORD, user.getPassword());
		value.put(LaunchConstant.USERS_LEFTOVER, user.getLeftOver());
		condition.put(LaunchConstant.USERS_ID, user.getId());

		result = update(tableName, value, condition);
		return result;
	}

	public boolean delete(Integer id) {
		boolean result = false;

		result = super.deleteById(LaunchConstant.USERS_TABLE,
				LaunchConstant.USERS_ID, id);
		return result;
	}
	
	private List<UserEntity> convertToEntity(
			List<Map<String, Object>> resultList) throws ParseException {
		List<UserEntity> entityList = null;

		if (null != resultList && resultList.size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			entityList = new ArrayList<UserEntity>();
			UserEntity user;
			for (Map<String, Object> map : resultList) {
				user = new UserEntity();
				Object obj = map.get(LaunchConstant.USERS_ID);
				user.setId(null == obj ? null : Integer.parseInt(obj
						.toString()));
				obj = map.get(LaunchConstant.USERS_NAME);
				user.setUserName(null == obj ? null : obj.toString());
				obj = map.get(LaunchConstant.USERS_PASSWORD);
				user.setPassword(null == obj ? null : obj.toString());
				obj = map.get(LaunchConstant.USERS_LEFTOVER);
				user.setLeftOver(null == obj ? 0.0f : Float.parseFloat(obj.toString()));
				obj = map.get(LaunchConstant.USERS_CREATED);
				user.setCreated(null == obj ? null : df.parse(obj.toString()));

				entityList.add(user);
			}
		}
		return entityList;
	}
	
	public UserEntity searchById(Integer id) throws ParseException{
		UserEntity user = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.USERS_TABLE;
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.USER_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.USERS_ID, id);
		resultList = super.search(tableName, columnName, condition);
		if (resultList.size() <= 0) {
			return user;
		}

		List<UserEntity> entityList = new ArrayList<UserEntity>();
		entityList = convertToEntity(resultList);
		user = entityList.get(0);
		return user;
	}
	
	public UserEntity searchByName(String name) throws ParseException{
		UserEntity user = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.USERS_TABLE;
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.USER_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.USERS_NAME, name);
		resultList = super.search(tableName, columnName, condition);
		if (resultList.size() <= 0) {
			return user;
		}

		List<UserEntity> entityList = new ArrayList<UserEntity>();
		entityList = convertToEntity(resultList);
		user = entityList.get(0);
		return user;
	}
}