package me.anchora.inpaasmgr.entry.base;

public class BaseSql {
    protected String orderByClause;

    protected boolean distinct;

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

}
