package gradle_spring5_db_study;

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
			
			selectByEmail();
			selectAll();
		}
	}

	private static void selectAll() {
		System.out.println("selectAll()");
		Collection<Member> member = memberDao.selectAll();
		System.out.println(member);
		
	}

	private static void selectByEmail() {
		System.out.println("selectByEmail()");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		System.out.printf("%d : %s : %s%n",member.getId(),member.getEmail(),member.getName());
	}
}
