package com.vendex.api.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public abstract class TestSupportWithPublisher extends TestSupport {

	private static final String USER_EMAIL = "vendex@gmail.com";

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("com.vendex.api.templates");
	}

	@BeforeEach
	public void setUpTest() {

		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

		Mockito.clearAllCaches();

		this.init();
	}

}
