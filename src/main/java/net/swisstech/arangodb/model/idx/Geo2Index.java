package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Geo.html */
public class Geo2Index extends AbstractGeoIndex {

	@Override
	public String getType() {
		return IndexTypes.GEO2;
	}
}
