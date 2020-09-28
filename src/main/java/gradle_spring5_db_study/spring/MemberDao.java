package gradle_spring5_db_study.spring;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 결과가 1개일 경우
	public Member selectByEmail(String email) {
		String sql = "SELECT ID, EMAIL, PASSWORD, NAME, REGDATE FROM MEMBER WHERE EMAIL = ?";
		return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), email);
	}

	public void insert(Member member) {
		
	}

	public void update(Member member) {
		
	}

	public Collection<Member> selectAll() {
		String sql = "SELECT ID, EMAIL, PASSWORD, NAME, REGDATE FROM MEMBER";
		return (Collection<Member>) jdbcTemplate.query(sql, new MemberRowMapper());
	}

}
