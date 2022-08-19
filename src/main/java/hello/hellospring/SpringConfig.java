package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    //스프링이 뜰 때 Configuration을 읽고 @Bean으로 스프링 빈에 등록하라고 인식
    //MemberService는 memberRepository를 사용함

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
    //스프링이 뜰 때 @Bean으로 memberService, memberRepository를 스프링 빈에 등록을 함
    //스프링 빈에 등록되어 있는 memberRepository를 멤버 서비스에 넣어줌

}
