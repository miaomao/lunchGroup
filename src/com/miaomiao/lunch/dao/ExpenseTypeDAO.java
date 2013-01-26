package com.miaomiao.lunch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miaomiao.lunch.entities.ExpenseTypeEntity;
import com.miaomiao.lunch.util.BaseUtil;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseTypeDAO extends BaseDAO {
	public boolean save(ExpenseTypeEntity eType) {
		boolean result = false;

		if (null != eType) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append("(");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(") VALUES ('");
			sql.append(eType.getName());
			sql.append("','");
			sql.append(eType.getAttr());
			sql.append("')");
			result = super.excute(sql.toString());
		}
		return result;
	}

	public boolean saveWithArgs(ExpenseTypeEntity eType) {
		boolean result = false;

		if (null != eType) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append("(");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(") VALUES (? , ?");
			sql.append(")");
			Object[] args = new Object[2];
			args[0] = eType.getName();
			args[1] = eType.getAttr();
			result = super.executeWithArgs(sql.toString(), args);
		}
		return result;
	}

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

	public boolean updateWithArgs(ExpenseTypeEntity eType) {
		boolean result = false;

		if (null != eType) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append(" SET ");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(" = ? , ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(" = ? WHERE ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ID);
			sql.append(" = " + " ? ");
			Object[] args = new Object[3];
			args[0] = eType.getName();
			args[1] = eType.getAttr();
			args[2] = eType.getId();
			result = super.executeWithArgs(sql.toString(), args);
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

	public ExpenseTypeEntity searchById(Integer id) {
		ExpenseTypeEntity eType = null;

		if (null != id) {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(" , ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(" FROM ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append(" WHERE ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ID);
			sql.append(" = " + id);

			List<Map<String, Object>> list = super.search(sql.toString());
			if (null != list && list.size() > 0) {
				eType = new ExpenseTypeEntity();
				Map<String, Object> map = list.get(0);
				eType.setId(id);
				Object obj = map.get(LaunchConstant.EXPENSE_TYPE_NAME);
				eType.setName(null == obj ? null : obj.toString());
				obj = map.get(LaunchConstant.EXPENSE_TYPE_ATTR);
				eType.setAttr(null == obj ? null : obj.toString());
			}
		}
		return eType;
	}

	public ExpenseTypeEntity searchByName(String name) {
		ExpenseTypeEntity eType = null;

		if (null != name) {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ID);
			sql.append(" , ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(" FROM ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append(" WHERE ");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(" = '" + name + "'");

			List<Map<String, Object>> list = super.search(sql.toString());
			if (null != list && list.size() > 0) {
				eType = new ExpenseTypeEntity();
				Map<String, Object> map = list.get(0);
				eType.setName(name);
				Object obj = map.get(LaunchConstant.EXPENSE_TYPE_ID);
				eType.setName(null == obj ? null : obj.toString());
				obj = map.get(LaunchConstant.EXPENSE_TYPE_ATTR);
				eType.setAttr(null == obj ? null : obj.toString());
			}
		}
		return eType;
	}

	public List<ExpenseTypeEntity> searchByAttr(String attr) {
		List<ExpenseTypeEntity> list = null;
		if (null != attr && !"".equals(attr)) {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ID);
			sql.append(" , ");
			sql.append(LaunchConstant.EXPENSE_TYPE_NAME);
			sql.append(" FROM ");
			sql.append(LaunchConstant.EXPENSE_TYPE_TABLE);
			sql.append(" WHERE ");
			sql.append(LaunchConstant.EXPENSE_TYPE_ATTR);
			sql.append(" = '" + attr + "'");

			List<Map<String, Object>> tempList = super.search(sql.toString());
			if (null != tempList && tempList.size() > 0) {
				list = new ArrayList<ExpenseTypeEntity>();
				ExpenseTypeEntity eType;
				for (Map<String, Object> map : tempList) {
					eType = new ExpenseTypeEntity();
					eType.setAttr(attr);
					Object obj = map.get(LaunchConstant.EXPENSE_TYPE_ID);
					eType.setId(null == obj ? null : Integer.parseInt(obj
							.toString()));
					obj = map.get(LaunchConstant.EXPENSE_TYPE_NAME);
					eType.setName(null == obj ? null : obj.toString());

					list.add(eType);
				}
			}
		}
		return list;
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

	public List<ExpenseTypeEntity> searchByAttr2(String attr) {
		List<ExpenseTypeEntity> entityList = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ATTR, attr);
		resultList = super.search(tableName, columnName, condition);
		entityList = convertToEntity(resultList);
		return entityList;
	}
	
	public ExpenseTypeEntity searchById2(Integer id) {
		ExpenseTypeEntity eType = null;
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_ID, id);
		resultList = super.search(tableName, columnName, condition);
		if (resultList.size() <= 0){
			return eType;
		}
		
		List<ExpenseTypeEntity> entityList = new ArrayList<ExpenseTypeEntity>();
		entityList = convertToEntity(resultList);
		eType = entityList.get(0);
		return eType;
	}
	
	public ExpenseTypeEntity searchByName2(String name) {
		ExpenseTypeEntity eType = null;
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String tableName = LaunchConstant.EXPENSE_TYPE_TABLE;
		ArrayList<String> columnName = BaseUtil.convertToList(LaunchConstant.EXPNSE_TYPE_COLUMNS);
		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(LaunchConstant.EXPENSE_TYPE_NAME, name);
		resultList = super.search(tableName, columnName, condition);
		if (resultList.size() <= 0){
			return eType;
		}
		
		List<ExpenseTypeEntity> entityList = new ArrayList<ExpenseTypeEntity>();
		entityList = convertToEntity(resultList);
		eType = entityList.get(0);
		return eType;
	}
}