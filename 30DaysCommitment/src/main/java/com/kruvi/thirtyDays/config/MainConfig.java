/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kruvi.thirtyDays.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * Main configuration class for the application.
 * Turns on @Component scanning, loads externalized application.properties, and sets up the database.
 * @author Keith Donald
 */
@Configuration
@ComponentScan(basePackages = "com.kruvi.thirtyDays", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:application.properties")
@Import(value = {SocialConfig.class, WebMvcConfig.class})
public class MainConfig {

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseName("spring-social-quickstart");
		factory.setDatabaseType(EmbeddedDatabaseType.H2);
		factory.setDatabasePopulator(databasePopulator());
		return factory.getDatabase();
	}

	// internal helpers

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}
}
