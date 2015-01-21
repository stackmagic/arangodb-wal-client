package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Geo.html */
public class Geo1Index extends AbstractGeoIndex {

	private boolean geoJson;

	public boolean isGeoJson() {
		return geoJson;
	}

	public void setGeoJson(boolean geoJson) {
		this.geoJson = geoJson;
	}
}
