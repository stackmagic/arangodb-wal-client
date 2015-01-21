package net.swisstech.arangodb.model.idx;

/** see https://docs.arangodb.com/IndexHandling/Hash.html */
public class HashIndex extends AbstractIndex {

	@Override
	public String getType() {
		return IndexTypes.HASH;
	}
}
