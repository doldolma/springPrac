package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        System.out.println("member = " + member);
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
