package gradle_spring5_db_study;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring5_db_study.config.AppCtx;
import gradle_spring5_db_study.spring.Member;
import gradle_spring5_db_study.spring.MemberDao;

public class MainForDataSource {
	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
			DataSource ds = ctx.getBean(DataSource.class);
			System.out.println(ds);
			memberDao = ctx.getBean(MemberDao.class);
			
			
			selectAll();
			insertMember();
			selectAll();
			updateMember();
			selectAll();
			deleteMember();
			selectAll();
		}
	}

	private static void selectAll() {
		System.out.println("selectAll()");
		List<Member> members = memberDao.selectAll();
		for(Member member : members) {
			System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
		}
		
	}

	private static void selectByEmail() {
		System.out.println("selectByEmail()");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		System.out.printf("%d : %s : %s%n",member.getId(),member.getEmail(),member.getName());
	}
	
	private static void updateMember() {
		System.out.println("-------------Update Member");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);
		
		memberDao.update(member);
		System.out.println("암호변경 : " + oldPw + " > " + newPw);
		System.out.println(member.getId() + "데이터 삭제");
	}
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
	
	private static void insertMember() {
		System.out.println("------------- Insert Member");
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member("test3" + "@test.co.kr", "1111", "1111", LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + " 데이터 추가");
	}
	
	private static void deleteMember() {
		System.out.println("----------- Delete Member");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		memberDao.delete(member);
	}
}
