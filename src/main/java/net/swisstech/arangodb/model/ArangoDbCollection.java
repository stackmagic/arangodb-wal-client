package net.swisstech.arangodb.model;

import java.util.List;

import net.swisstech.arangodb.model.idx.AbstractIndex;

public class ArangoDbCollection {

	private CollectionParameters parameters;
	private List<AbstractIndex> indexes;

	public CollectionParameters getParameters() {
		return parameters;
	}

	public void setParameters(CollectionParameters parameters) {
		this.parameters = parameters;
	}

	public List<AbstractIndex> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<AbstractIndex> indexes) {
		this.indexes = indexes;
	}

	@Override
	public String toString() {
		String name = "<null>";
		if (parameters != null) {
			name = parameters.getName();
		}
		return "Collection " + name;
	}
}
