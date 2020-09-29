package gradle_spring5_db_study.spring.mapper;

import java.util.List;

import gradle_spring5_db_study.spring.Member;

public interface MemberMapper {
	
	// 결과가 1개일 경우
	public Member selectByEmail(String email);

	public int count();
	
	int insert(Member member);

	public void update(Member member);

	List<Member> selectAll();
	
	public void delete(Member member);
}
