package gradle_spring5_db_study.spring;

import org.springframework.stereotype.Component;

@Component
public class MemberPrinter {
	public void print(Member member) {
		System.out.printf("ȸ������:���̵�=%d,�̸���=%s,�̸�=%s,�����=%tF\n", member.getId(), member.getEmail(), member.getName(),
				member.getRegisterDateTime());
	}
}
