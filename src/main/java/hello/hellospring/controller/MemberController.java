package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //컨트롤러가 있으면 스프링 컨테이너가 관리를 함
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //Autowired가 있으면 스프링이 스프링 컨테이너에서 멤버서비스를 가져와 연결시켜줌
    //멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 멤버 서비스 객체를 가져와서 넣어줌
    //  -> DI 라고 함 의존 관계 주입

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // form.getName()으로 해주는 이유
        //홈 화면에서 회원가입 기능(멤버 생성)

        memberService.join(member);     //회원가입을 할 때 중복된 회원이 있을 수 있으므로 join으로 넘김
        return "redirect:/";        //회원가입을 끝내고 홈 화면으로 돌아감
    }

    @GetMapping("/members") //회원 조회
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); //모든 멤버를 불러옴
        model.addAttribute("members", members);
        //model의 키 값: members에 List로 모든 members를 담아둠
        //members의 모든 리스트들을 model에 담아서 view에 넘김
        return "members/memberList";
    }

}
