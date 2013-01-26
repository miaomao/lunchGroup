package com.miaomiao.lunch.dao;

import com.miaomiao.lunch.entities.UserEntity;
import com.miaomiao.lunch.util.LaunchConstant;

public class UserDAO extends BaseDAO {

	public boolean saveWithArgs(UserEntity user) {
		boolean result = false;

		if (null != user) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ");
			sql.append(LaunchConstant.USERS_TABLE);
			sql.append("(");
			sql.append(LaunchConstant.USERS_NAME);
			sql.append(",");
			sql.append(LaunchConstant.USERS_PASSWORD);
			sql.append(",");
			sql.append(LaunchConstant.USERS_LEFTOVER);
			sql.append(",");
			sql.append(LaunchConstant.USERS_CREATED);
			sql.append(") VALUES (? , ? , ? , ?)");
			Object[] args = new Object[4];
			args[0] = user.getUserName();
			args[1] = user.getPassword();
			args[2] = user.getLeftOver();
			args[3] = user.getCreated();

			result = super.executeWithArgs(sql.toString(), args);
		}
		return result;
	}

	public boolean updateWithArgs(Integer id, String column, String value) {
		boolean result = false;

		if (null != id && null != column && null != value) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append(LaunchConstant.USERS_TABLE);
			sql.append(" SET ");
			sql.append(column);
			sql.append(" = ? WHERE ");
			sql.append(LaunchConstant.USERS_ID);
			sql.append(" = ? ");
			Object[] args = new Object[2];
			args[0] = value;
			args[1] = id;
			result = super.executeWithArgs(sql.toString(), args);
		}
		return result;
	}
	
	public boolean delete(Integer id) {
		boolean result = false;
		
		result = super.deleteById(LaunchConstant.USERS_TABLE, LaunchConstant.USERS_ID, id);
		return result;
	}
}