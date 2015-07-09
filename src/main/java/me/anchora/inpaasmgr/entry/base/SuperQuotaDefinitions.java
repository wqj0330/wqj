package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitions;

public class SuperQuotaDefinitions extends PageEntry {
    private QuotaDefinitions quotaDefinitions;

    public QuotaDefinitions getQuotaDefinitions() {
        return quotaDefinitions;
    }

    public void setQuotaDefinitions(QuotaDefinitions quotaDefinitions) {
        this.quotaDefinitions = quotaDefinitions;
    }
    
}
