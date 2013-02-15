package com.miaomiao.lunch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.ExpenseTypeEntity;
import com.miaomiao.lunch.util.BaseUtil;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseTypeDAO extends BaseDAO {
	public boolean insert(ExpenseTypeEntity eType) {
		boolean result = false;
		if (null != eType) {
			String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(LaunchConstant.EXPENSE_TYPE_NAME, eType.getName());
			map.put(LaunchConstant.EXPENSE_TYPE_ATTR, eType.getAttr());
			result = super.insert(tableName, map);
		}
		return result;
	}

	public boolean updateById(ExpenseTypeEntity eType) {
		boolean result = false;
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		Map<String, Object> value = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();

		value.put(LaunchConstant.EXPENSE_TYPE_NAME, eType.getName());
		value.put(LaunchConstant.EXPENSE_TYPE_ATTR, eType.getAttr());
		condition.put(LaunchConstant.EXPENSE_TYPE_ID, eType.getId());

		result = update(tableName, value, condition);
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
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ATTR, attr);
		resultList = super.search(tableName, columnName, condition);
		entityList = convertToEntity(resultList);
		return entityList;
	}

	public ExpenseTypeEntity searchById(Integer id) {
		ExpenseTypeEntity eType = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ID, id);
		resultList = super.search(tableName, columnName, condition);
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
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil
				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_NAME, name);
		resultList = super.search(tableName, columnName, condition);
		if (resultList.size() <= 0) {
			return eType;
		}

		List<ExpenseTypeEntity> entityList = new ArrayList<ExpenseTypeEntity>();
		entityList = convertToEntity(resultList);
		eType = entityList.get(0);
		return eType;
	}
}