package me.anchora.inpaasmgr.entry.base;



public class SuperAppMonitDatas extends PageEntry {
	private Float percentOfMemory;
	private Float percentOfDisk;
	public Float getPercentOfMemory() {
		return percentOfMemory;
	}
	public void setPercentOfMemory(Float percentOfMemory) {
		this.percentOfMemory = percentOfMemory;
	}
	public Float getPercentOfDisk() {
		return percentOfDisk;
	}
	public void setPercentOfDisk(Float percentOfDisk) {
		this.percentOfDisk = percentOfDisk;
	}
	
}
