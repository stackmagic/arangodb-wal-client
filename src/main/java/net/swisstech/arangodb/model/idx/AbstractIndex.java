package net.swisstech.arangodb.model.idx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( //
use = JsonTypeInfo.Id.NAME, //
include = JsonTypeInfo.As.PROPERTY, //
property = "type" //
)
@JsonSubTypes({ //
@Type(value = CapIndex.class, name = IndexTypes.CAP), //
	@Type(value = FulltextIndex.class, name = IndexTypes.FULLTEXT), //
	@Type(value = Geo1Index.class, name = IndexTypes.GEO1), //
	@Type(value = Geo2Index.class, name = IndexTypes.GEO2), //
	@Type(value = HashIndex.class, name = IndexTypes.HASH), //
	@Type(value = SkiplistIndex.class, name = IndexTypes.SKIPLIST), //
})
public abstract class AbstractIndex {

	private String id;
	private boolean unique;
	private List<String> fields;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract String getType();

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Index of type " + getType();
	}
}
