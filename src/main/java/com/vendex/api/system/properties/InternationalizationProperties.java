package com.vendex.api.system.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@RequiredArgsConstructor
public class InternationalizationProperties {

	@Value("${internationalization.country}")
	public String country;

	@Value("${internationalization.language}")
	public String language;

	@Value("${internationalization.timezone}")
	public String timezone;

}