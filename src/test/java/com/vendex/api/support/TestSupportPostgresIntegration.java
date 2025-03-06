package com.vendex.api.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.vendex.api.system.properties.InternationalizationProperties;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@EnableConfigurationProperties(InternationalizationProperties.class)
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=none", "spring.flyway.enabled=false",
		"monkey.flyway.enabled=false", "internationalization.country=BR", "internationalization.language=pt",
		"internationalization.timezone=GMT-3",
		"spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
		"spring.datasource.url=jdbc:tc:postgresql:11-alpine:uaigraos_test?TC_INITSCRIPT=file:src/test/resources/db/schema.sql" })
public abstract class TestSupportPostgresIntegration {

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.tiib.seed.seedtiib.templates");
	}

	@Before
	public void setUpTest() {
		this.init();
	}

	public abstract void init();

}
