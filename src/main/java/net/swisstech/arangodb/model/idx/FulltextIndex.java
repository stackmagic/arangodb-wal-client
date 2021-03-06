package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Fulltext.html */
public class FulltextIndex extends AbstractIndex {

	private long minLength;

	@Override
	public String getType() {
		return IndexTypes.FULLTEXT;
	}

	public long getMinLength() {
		return minLength;
	}

	public void setMinLength(long minLength) {
		this.minLength = minLength;
	}
}
