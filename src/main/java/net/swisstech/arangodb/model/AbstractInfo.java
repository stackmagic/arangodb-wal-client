package net.swisstech.arangodb.model;


public abstract class AbstractInfo extends AbstractResponse {

	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
