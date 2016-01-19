package br.com.rrodovalho.gcm_example_server;

import br.com.rrodovalho.gcm_example_server.domain.User;
import br.com.rrodovalho.gcm_example_server.service.UserService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
public class GcmExampleServerApplication{// implements CommandLineRunner {

	public final static String SENDER_API_KEY="AIzaSyDszwo1XOan77b6HQ4cHCoPq0l-BEGIeSs";
	public final static String GCM_EXAMPLE_B2C_PACKAGE="br.com.rrodovalho.gcm_exampleb2c";

	public static void main(String[] args) {
		SpringApplication.run(GcmExampleServerApplication.class, args);
	}

	/*@Bean
	SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return factoryBean;
	}


	@Bean
	EmbeddedDatabaseFactoryBean dataSource() {
		EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
		factoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
		factoryBean.setDatabaseName("spring-boot-sample");
		factoryBean.setDatabasePopulator(new ResourceDatabasePopulator(
				new ClassPathResource("database/schema.sql"),
				new ClassPathResource("database/dataload.sql")
		));
		return factoryBean;
	}

	@Autowired
	private UserService userService;


	@Override
	public void run(String... args) {
		System.out.println("Adding User");
		int userId = userService.addUser(new User("dude@dude.com", "thedude"));
		System.out.println(userId);
		System.out.println("Getting User");
		//User user = userService.getUser(userId);
		//System.out.println("Got User: " + user.getName());
	}*/
}
