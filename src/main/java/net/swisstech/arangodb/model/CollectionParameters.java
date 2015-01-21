package net.swisstech.arangodb.model;

public class CollectionParameters {

	private long version;
	private CollectionType type;
	private String cid;
	private String planId;
	private boolean deleted;
	private boolean doCompact;
	private long maximalSize;
	private String name;
	private boolean isVolatile;
	private boolean waitForSync;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public CollectionType getType() {
		return type;
	}

	public void setType(CollectionType type) {
		this.type = type;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean getDoCompact() {
		return doCompact;
	}

	public void setDoCompact(boolean doCompact) {
		this.doCompact = doCompact;
	}

	public long getMaximalSize() {
		return maximalSize;
	}

	public void setMaximalSize(long maximalSize) {
		this.maximalSize = maximalSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsVolatile() {
		return isVolatile;
	}

	public void setIsVolatile(boolean isVolatile) {
		this.isVolatile = isVolatile;
	}

	public boolean isWaitForSync() {
		return waitForSync;
	}

	public void setWaitForSync(boolean waitForSync) {
		this.waitForSync = waitForSync;
	}
}
