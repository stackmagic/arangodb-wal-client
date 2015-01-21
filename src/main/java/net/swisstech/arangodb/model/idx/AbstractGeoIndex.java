package net.swisstech.arangodb.model.idx;

/** see: https://docs.arangodb.com/IndexHandling/Geo.html */
public abstract class AbstractGeoIndex extends AbstractIndex {

	private boolean constraint;
	private boolean ignoreNull;

	public boolean isConstraint() {
		return constraint;
	}

	public void setConstraint(boolean constraint) {
		this.constraint = constraint;
	}

	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	public void setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
	}
}
