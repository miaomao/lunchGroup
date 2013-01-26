package com.miaomiao.lunch.dao;

import com.miaomiao.lunch.entities.ExpenseEntity;
import com.miaomiao.lunch.util.LaunchConstant;

public class ExpenseItemDAO extends BaseDAO {
	public boolean save(ExpenseEntity expense) {
		boolean result = false;

		if (null != expense) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ");
			sql.append(LaunchConstant.EXPENSE_ITEM_TABLE);
			sql.append("(");
			sql.append(LaunchConstant.EXPENSE_ITEM_ETYPEID);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_ACCOUNTID);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_CREATED);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_VALUE);
			sql.append(") VALUES (?, ?, ?, ? )");
			Object[] args = new Object[4];
			args[0] = expense.geteTypeId();
			args[1] = expense.getUserId();
			args[2] = expense.getCreated();
			args[3] = expense.getValue();
			System.out.println(sql);
			super.executeWithArgs(sql.toString(), args);
		}
		return result;
	}

	public boolean saveWithArgs(ExpenseEntity expense) {
		boolean result = false;

		if (null != expense) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO ");
			sql.append(LaunchConstant.EXPENSE_ITEM_TABLE);
			sql.append("(");
			sql.append(LaunchConstant.EXPENSE_ITEM_ETYPEID);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_ACCOUNTID);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_CREATED);
			sql.append(",");
			sql.append(LaunchConstant.EXPENSE_ITEM_VALUE);
			sql.append(") VALUES (?, ?, ?, ? )");
			Object[] args = new Object[4];
			args[0] = expense.geteTypeId();
			args[1] = expense.getUserId();
			args[2] = expense.getCreated();
			args[3] = expense.getValue();
			super.executeWithArgs(sql.toString(), args);
		}
		return result;
	}
}
