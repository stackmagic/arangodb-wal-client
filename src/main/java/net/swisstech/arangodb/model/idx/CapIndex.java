package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Cap.html */
public class CapIndex extends AbstractIndex {

	private long size;
	private long byteSize;

	@Override
	public String getType() {
		return IndexTypes.CAP;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getByteSize() {
		return byteSize;
	}

	public void setByteSize(long byteSize) {
		this.byteSize = byteSize;
	}
}
