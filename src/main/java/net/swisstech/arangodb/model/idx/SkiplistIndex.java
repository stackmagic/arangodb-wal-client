package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Skiplist.html */
public class SkiplistIndex extends AbstractIndex {

	@Override
	public String getType() {
		return IndexTypes.SKIPLIST;
	}
}
