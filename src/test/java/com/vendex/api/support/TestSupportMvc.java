package com.vendex.api.support;

public abstract class TestSupportMvc extends TestSupport {

	@Override
	public void init() {
		start();
	}

	public abstract void start();

}
