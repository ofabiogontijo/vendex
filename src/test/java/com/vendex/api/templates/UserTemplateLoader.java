package com.vendex.api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.vendex.api.user.User;

import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;

public class UserTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(User.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", UUID.randomUUID());
				add("name", random("John Doe", "Jane Smith", "Alice Johnson"));
				add("email", random("john.doe@example.com", "jane.smith@example.com", "alice.johnson@example.com"));
				add("password", regex("[a-zA-Z0-9]{8,12}"));
			}
		});
	}

}
