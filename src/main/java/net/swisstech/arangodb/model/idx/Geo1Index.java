package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Geo.html */
public class Geo1Index extends AbstractGeoIndex {

	private boolean geoJson;

	@Override
	public String getType() {
		return IndexTypes.GEO1;
	}

	public boolean isGeoJson() {
		return geoJson;
	}

	public void setGeoJson(boolean geoJson) {
		this.geoJson = geoJson;
	}
}
