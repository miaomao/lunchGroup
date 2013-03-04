package com.miaomiao.lunch.dao;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.ExpenseTypeEntity;
import com.miaomiao.lunch.util.BaseUtil;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseTypeDAO extends BaseDAO {
	private String dbETable = LaunchConstant.EXPENSE_TYPE_TABLE;
	private String dbEId = LaunchConstant.EXPENSE_TYPE_ID;
	private String dbEName = LaunchConstant.EXPENSE_TYPE_NAME;
	private String dbEAttr = LaunchConstant.EXPENSE_TYPE_ATTR;
	private String dbECreated = LaunchConstant.EXPENSE_TYPE_CREATED;
	private String dbEDeleted = LaunchConstant.EXPENSE_TYPE_DELETED;
	private String[] dbEColumns = LaunchConstant.EXPNSE_TYPE_COLUMNS;
	private DateFormat df = LaunchConstant.DATE_FORMAT;

	/*
	 * default return value is -1. Either (1) the row count for SQL Data
	 * Manipulation Language (DML) statements or (2) 0 for SQL statements that
	 * return nothing
	 */
	public Integer insert(ExpenseTypeEntity eType) {
		Integer result = -1;
		if (null != eType) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(dbEName, eType.getName());
			map.put(dbEAttr, eType.getAttr());
			map.put(dbECreated, eType.getCreated());
			result = super.insert(dbETable, map);
		}
		return result;
	}

	public Integer updateById(ExpenseTypeEntity eType) {
		Integer result = -1;
		Map<String, Object> value = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();

		value.put(LaunchConstant.EXPENSE_TYPE_NAME, eType.getName());
		value.put(LaunchConstant.EXPENSE_TYPE_ATTR, eType.getAttr());
		condition.put(LaunchConstant.EXPENSE_TYPE_ID, eType.getId());

		result = update(dbETable, value, condition);
		return result;
	}

	public boolean deleteWithArgs(Integer id) {
		boolean result = false;

		result = super.deleteById(LaunchConstant.EXPENSE_TYPE_TABLE,
				LaunchConstant.EXPENSE_TYPE_ID, id);
		return result;
	}

	private List<ExpenseTypeEntity> convertToEntity(
			List<Map<String, Object>> resultList) {
		List<ExpenseTypeEntity> entityList = null;

		if (null != resultList && resultList.size() > 0) {
			entityList = new ArrayList<ExpenseTypeEntity>();
			ExpenseTypeEntity eType;
			for (Map<String, Object> map : resultList) {
				eType = new ExpenseTypeEntity();
				Object obj = map.get(LaunchConstant.EXPENSE_TYPE_ID);
				eType.setId(null == obj ? null : Integer.parseInt(obj
						.toString()));
				obj = map.get(LaunchConstant.EXPENSE_TYPE_NAME);
				eType.setName(null == obj ? null : obj.toString());
				obj = map.get(LaunchConstant.EXPENSE_TYPE_ATTR);
				eType.setAttr(null == obj ? null : obj.toString());

				entityList.add(eType);
			}
		}
		return entityList;
	}

	public List<ExpenseTypeEntity> searchByAttr(String attr) {
		List<ExpenseTypeEntity> entityList = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ATTR, attr);
		resultList = super.search(dbETable, columnName, condition);
		entityList = convertToEntity(resultList);
		return entityList;
	}

	public ExpenseTypeEntity searchById(Integer id) {
		ExpenseTypeEntity eType = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ID, id);
		resultList = super.search(dbETable, columnName, condition);
		if (resultList.size() <= 0) {
			return eType;
		}

		List<ExpenseTypeEntity> entityList = new ArrayList<ExpenseTypeEntity>();
		entityList = convertToEntity(resultList);
		eType = entityList.get(0);
		return eType;
	}

	public ExpenseTypeEntity searchByName(String name) {
		ExpenseTypeEntity eType = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_NAME, name);
		resultList = super.search(dbETable, columnName, condition);
		if (resultList.size() <= 0) {
			return eType;
		}

		List<ExpenseTypeEntity> entityList = new ArrayList<ExpenseTypeEntity>();
		entityList = convertToEntity(resultList);
		eType = entityList.get(0);
		return eType;
	}
}