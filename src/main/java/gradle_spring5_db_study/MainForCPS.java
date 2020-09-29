package gradle_spring5_db_study;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import gradle_spring5_db_study.config.AppCtx;
import gradle_spring5_db_study.spring.ChangePasswordService;
import gradle_spring5_db_study.spring.Member;
import gradle_spring5_db_study.spring.MemberDao;
import gradle_spring5_db_study.spring.MemberNotFoundException;

public class MainForCPS {
	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
			MemberDao memberDao = ctx.getBean(MemberDao.class);
			Member newMember = new Member("test7@test.co.kr", "1234", "test", LocalDateTime.now());
			memberDao.insert(newMember);
			System.out.println("회원을 추가했습니다.\n");
			
			
			ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
			cps.changePassword("test8@test.co.kr", "1234", "new1234");
			System.out.println("암호를 변경했습니다.\n");
			
			Member member = memberDao.selectByEmail("test8@test.co.kr");
			memberDao.delete(member);
			System.out.println("회원을 삭제했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.err.println("존재하지 않는 이메일입니다.\n");
		} catch (EmptyResultDataAccessException e) {
			System.err.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}
}
