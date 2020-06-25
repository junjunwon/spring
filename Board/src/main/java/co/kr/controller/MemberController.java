package co.kr.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.service.MemberService;
import co.kr.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	MemberService service;
	
	
	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	//회원가입 get
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegister() throws Exception{
		logger.info("get register");
	}
	
	//회원가입 폼으로 이동할 때에는 GET메서드를 타고 회원가입 버튼을 눌렀을 때 POST메서드를 타게끔 작성한다.
	
	//회원가입 post
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception{
		
		logger.info("post register");
		int result=service.idChk(vo);
		
		try {
			System.out.println("service.register complete"+result);
			if(result==1) {
				return "/member/register";
			}else if(result==0) {
				service.register(vo);
				
			} 			
		}catch(Exception e) {
			throw new RuntimeException();
		}

		return "redirect:/";
	}
	
	//회원정보 수정
	@RequestMapping(value="/memberUpdateView", method=RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		logger.info("memberUpdateView");
		
		return "member/memberUpdateView";
	}
	//회원정보 수정 POST
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		/*
		 * 회원정보 수정 페이지에서 수정을 누르면 /memberUpdate 요청을 하고, 파라미터들을 service.memberUpdate(vo)에 넣어줘서 service로 보낸다.
		 * 그리고 session.invalidate()로 세션을 끊고 로그인 페이지로 redirect해준다.
		 */
		service.memberUpdate(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원탈퇴 GET
	@RequestMapping(value="/memberDeleteView", method=RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		logger.info("memberDeleteView");
		
		return "member/memberDelete";
	}
	
	//회원탈퇴 POST
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		//세션에 있는  member를 가져와 member 변수에 넣는다.
		MemberVO member=(MemberVO)session.getAttribute("member");
		//세션에 있는 비밀번호(로그인할때 저장된)
		String sessionPass=member.getUserPass();
		//vo로 들어오는 패스워드 (내가 입력하는)
		String voPass=vo.getUserPass();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			
			return "redirect:/member/memberDeleteView";
		}
		
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
	
	//id중복 체크
	@ResponseBody
	@RequestMapping(value="/idChk", method=RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception{
		int result=service.idChk(vo);
		return result;
	}
	
	//로그인 Post
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session=req.getSession();
		MemberVO login=service.login(vo);
		System.out.println(login); 
		if(login==null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		} else {
			session.setAttribute("member", login);

		}
		
		return "redirect:/";
		
	}
	
	
	//로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("logout");
		session.invalidate();
		
		return "redirect:/";
	}
}
