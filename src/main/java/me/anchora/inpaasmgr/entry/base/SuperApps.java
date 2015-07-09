package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.entry.ccdb.Stacks;

public class SuperApps extends PageEntry {
    private String url;
    private Organizations organizations;
    private Spaces spaces;
    private Stacks stacks;

    public Organizations getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Organizations organizations) {
        this.organizations = organizations;
    }

    public Spaces getSpaces() {
        return spaces;
    }

    public void setSpaces(Spaces spaces) {
        this.spaces = spaces;
    }

    public Stacks getStacks() {
        return stacks;
    }

    public void setStacks(Stacks stacks) {
        this.stacks = stacks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
