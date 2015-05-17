package net.swisstech.arangodb.model.idx;

/** see https://docs.arangodb.com/IndexHandling/Hash.html */
public class HashIndex extends AbstractIndex {

	private boolean sparse;
	private long selectivityEstimate;

	@Override
	public String getType() {
		return IndexTypes.HASH;
	}

	public boolean isSparse() {
		return sparse;
	}

	public void setSparse(boolean sparse) {
		this.sparse = sparse;
	}

	public long getSelectivityEstimate() {
		return selectivityEstimate;
	}

	public void setSelectivityEstimate(long selectivityEstimate) {
		this.selectivityEstimate = selectivityEstimate;
	}
}
