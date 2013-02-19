package com.miaomiao.lunch.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.ExpenseItemEntity;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseItemDAO extends BaseDAO {
	public boolean insert(ExpenseItemEntity eItem) {
		boolean result = false;
		if (null != eItem) {
			String tableName = LaunchConstant.EXPENSE_ITEM_TABLE;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(LaunchConstant.EXPENSE_ITEM_ACCOUNTID, eItem.getUserId());
			map.put(LaunchConstant.EXPENSE_ITEM_ETYPEID, eItem.geteTypeId());
			map.put(LaunchConstant.EXPENSE_ITEM_VALUE, eItem.getValue());
			map.put(LaunchConstant.EXPENSE_ITEM_CREATED, eItem.getCreated());
			result = super.insert(tableName, map);
		}
		return result;
	}

	public boolean update(ExpenseItemEntity eItem) throws ParseException {
		boolean result = false;

		String tableName = LaunchConstant.EXPENSE_ITEM_TABLE;
		Map<String, Object> value = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();

		value.put(LaunchConstant.EXPENSE_ITEM_VALUE, eItem.getValue());
		condition.put(LaunchConstant.EXPENSE_ITEM_ACCOUNTID, eItem.getUserId());
		condition.put(LaunchConstant.EXPENSE_ITEM_ETYPEID, eItem.geteTypeId());
		condition.put(LaunchConstant.EXPENSE_ITEM_CREATED, eItem.getCreated());

		result = update(tableName, value, condition);
		return result;
	}

	public boolean delete(ExpenseItemEntity eItem) {
		boolean result = false;

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(LaunchConstant.EXPENSE_ITEM_ACCOUNTID, eItem.getUserId());
		condition.put(LaunchConstant.EXPENSE_ITEM_ETYPEID, eItem.geteTypeId());
		result = super.deleteByCondition(LaunchConstant.EXPENSE_ITEM_TABLE,
				condition);
		return result;
	}

	private List<ExpenseItemEntity> convertToEntity(
			List<Map<String, Object>> resultList) throws ParseException {
		List<ExpenseItemEntity> entityList = null;

		if (null != resultList && resultList.size() > 0) {
			entityList = new ArrayList<ExpenseItemEntity>();
			ExpenseItemEntity eItem = new ExpenseItemEntity();
			for (Map<String, Object> map : resultList) {
//				eItem = new ExpenseItemEntity();
				Object obj = map.get(LaunchConstant.EXPENSE_ITEM_ACCOUNTID);
				eItem.setUserId(null == obj ? null
						: Integer.parseInt(obj.toString()));
				obj = map.get(LaunchConstant.EXPENSE_ITEM_ETYPEID);
				eItem.seteTypeId(null == obj ? null : Integer.parseInt(obj.toString()));
				obj = map.get(LaunchConstant.EXPENSE_ITEM_CREATED);
				eItem.setCreated(null == obj ? null : LaunchConstant.DATE_FORMAT.parse(obj.toString()));
				obj = map.get(LaunchConstant.EXPENSE_ITEM_VALUE);
				eItem.setValue(null == obj ? 0.0f : Float.parseFloat(obj
						.toString()));

				entityList.add(eItem);
			}
		}
		return entityList;
	}
	
	public List<ExpenseItemEntity> searchByUserDateType(Integer accountId, Date from, Date to, Integer typeId) {
		List<ExpenseItemEntity> entityList = null;
//		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
//		ArrayList<String> columnName = BaseUtil
//				.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
//		Map<String, Object> condition = new HashMap<String, Object>();
//
//		condition.put(LaunchConstant.EXPENSE_TYPE_ATTR, attr);
//		resultList = super.search(tableName, columnName, condition);
//		entityList = convertToEntity(resultList);
		return entityList;
	}
}