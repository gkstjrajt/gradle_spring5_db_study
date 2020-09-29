package gradle_spring5_db_study.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement	//@Transaction 애노테이션이 붙은 메서드를 트랜잭션 범위에서 실행하는 기능을 활성화
public class ContextDataSource {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariDataSource dataSource = null;
		try {
			Properties prop = Resources.getResourceAsProperties("application.properties");
			HikariConfig cfg = new HikariConfig(prop);
			dataSource = new HikariDataSource(cfg);
			dataSource.setMinimumIdle(10);
			dataSource.setMaximumPoolSize(100);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
}
