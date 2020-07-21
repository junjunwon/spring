package co.kr.controller;


import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.service.MemberService;
import co.kr.service.UserMailSendService;
import co.kr.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	MemberService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister(MemberVO vo) throws Exception {
		logger.info("get registerView");
		// int name_result=service.nameChk(vo);
		// System.out.println(name_result);
	}

	// 회원가입 폼으로 이동할 때에는 GET메서드를 타고 회원가입 버튼을 눌렀을 때 POST메서드를 타게끔 작성한다.

	// 회원가입 post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {

		logger.info("post register");
		int id_result = service.idChk(vo);
		int name_result = service.nameChk(vo);
		System.out.println("vo.getUserPass() : " + vo.getUserPass());
		String hashedPw = BCrypt.hashpw(vo.getUserPass(), BCrypt.gensalt());
		vo.setUserPass(hashedPw);

		System.out.println("hashedPw is : " + hashedPw);

		try {
			System.out.println("service.register complete" + id_result);
			if (id_result == 1 || name_result == 1) {

				return "/member/register";
			} else if (id_result == 0 && name_result == 0) {
				service.register(vo);

			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		System.out.println("register->confirmemailview로 데이터 넘길때 vo getUserId : "+vo.getUserId());
		String getUserId=vo.getUserId();
		/*
		 * 쿠키가 존재하는지 여부 확인 -> 있으면 지우기. userid가 항상 배열 0으로 올 수 있게.
		 */
		Cookie[] cookies=request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			cookies[i].setMaxAge(0);
		}
		Cookie myCookie=new Cookie("userId", getUserId);
		response.addCookie(myCookie);
		
		System.out.println();
		return "redirect:/member/confirmEmailView";
	}

	// 회원정보 수정
	@RequestMapping(value = "/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		logger.info("memberUpdateView");

		return "member/memberUpdateView";
	}

	// 회원정보 수정 POST
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception {
		/*
		 * 회원정보 수정 페이지에서 수정을 누르면 /memberUpdate 요청을 하고, 파라미터들을 service.memberUpdate(vo)에
		 * 넣어줘서 service로 보낸다. 그리고 session.invalidate()로 세션을 끊고 로그인 페이지로 redirect해준다.
		 */
		logger.info("memberUpdate post");
		System.out.println("유저가 변경한 비밀번호 : "+vo.getUserPass());
		String hashedPw=BCrypt.hashpw(vo.getUserPass(), BCrypt.gensalt());
		vo.setUserPass(hashedPw);
		service.memberUpdate(vo);
		session.invalidate();

		return "redirect:/member/login";
	}


	// 회원탈퇴 GET
	@RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception {
		logger.info("memberDeleteView");

		return "member/memberDelete";
	}
	
	static String temp="";
	//회원 confirmPassword 페이지
	@RequestMapping(value="/confirmPasswordView", method=RequestMethod.GET)
	public String confirmPasswordDelete(HttpServletRequest request,String pageName, 
					@ModelAttribute("memberVO") MemberVO memberVO) throws Exception{
		logger.info("confirmPasswordView");
		//String page=request.getServletPath().toString();
		temp=request.getParameter("flag").toString();
		/*
		 * 회원탈퇴, 수정 분기를 나눌 flag를 deletebtn(delete), updatebtn(update)으로부터 가져온다. 
		 */
		System.out.println(temp);
		
		request.setAttribute("test", temp);
		return "member/confirmPasswordView";
	}
	
	 
	
	  //주석처리 푸는 법 ctrl+shift+\
	  //회원탈퇴 confirmPassword Post
	  
	
	  @RequestMapping(value="/confirmPassword", method=RequestMethod.POST) 
	  public String confirmPassword(HttpServletRequest request, MemberVO vo, HttpSession session, Model model) throws
	  Exception{ 
		  logger.info("confirmPassword Post");
		  String result=request.getParameter("result");
		  System.out.println("flag값은 "+result);
		  
		  System.out.println("vo : "+vo);
		  System.out.println("vo.getuserpass : "+vo.getUserPass()); 
		  MemberVO member=(MemberVO)session.getAttribute("member"); 
		  System.out.println("member : "+member);
		  String sessionPass=member.getUserPass();
		  System.out.println("sessionPass : "+sessionPass);
		  System.out.println(temp);
		  if(sessionPass==null || !BCrypt.checkpw(vo.getUserPass(), sessionPass)) 
		  {
			  System.out.println("password is wrong123"); 
			  return "/member/confirmPasswordView"; 
		  }
		  
		  System.out.println("password correct");
		  if(result.equals("update")) {
			  return "redirect:/member/memberUpdateView";
		  }
		return "redirect:/member/memberDeleteView";
		
		  
		  
	  
	  }
	 
	 
	
	// 회원탈퇴 POST
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {

		// 세션에 있는 member를 가져와 member 변수에 넣는다.
		MemberVO member = (MemberVO) session.getAttribute("member");
		// 세션에 있는 비밀번호(로그인할때 저장된)
		String sessionPass = member.getUserPass();
		// vo로 들어오는 패스워드 (내가 입력하는)
		String voPass = vo.getUserPass();
		if(!BCrypt.checkpw(voPass, sessionPass)) {
			rttr.addFlashAttribute("msg", false);

			return "redirect:/member/memberDeleteView";
		}
		System.out.println("vo : "+vo);
		System.out.println("voPass : "+voPass);
		System.out.println("session : "+sessionPass);
		System.out.println("member : "+member);
		service.memberDelete(member);
		session.invalidate();
		return "redirect:/member/login";
	}

	// id중복 체크
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		return result;
	}
	
	// 닉네임 중복 체크
	@ResponseBody
	@RequestMapping(value = "/nameChk", method = RequestMethod.POST)
	public int nameChk(MemberVO vo) throws Exception {
		int result = service.nameChk(vo);
		return result;
	}
	
	@Autowired
	private UserMailSendService mailsender;
	
	//email view get
	@RequestMapping(value="/confirmEmailView", method=RequestMethod.GET)
	public void confirmEmail(HttpServletRequest request, Model model) throws Exception{
		logger.info("get confirmEmailView");
		Cookie[] myCookies=request.getCookies();
		System.out.println(myCookies[0].getValue());

		String userId=myCookies[0].getValue();
		model.addAttribute("userId", userId);
	}
	
	
	
	//email 보내기 post
	@RequestMapping(value="/sendEmail", method= RequestMethod.POST)
	public String sendEmail(MemberVO vo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println(vo.getUserEmail()+"=========="+vo.getUserId());
		System.out.println("vo 전체 값 : "+vo);
		vo.setUserEmail(vo.getUserEmail());
		vo.setUserId(vo.getUserId());
		String key=mailsender.mailSendWithUserKey(vo, request);
		model.addAttribute("modelKey", key);
		System.out.println("service로부터 키값을 받아옴 : "+key);
		
		Cookie myCookie=new Cookie("modelKey", key);
		response.addCookie(myCookie);
		
		
		return "member/confirmEmailView";
	}
	
	@ResponseBody
	@RequestMapping(value="/authKey", method=RequestMethod.POST)
	public int authKey(MemberVO vo, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Cookie[] cookies=request.getCookies();
		int index=0;
		int result=0; //0이면 인증번호 오류, 1이면 인증번호 맞음.
		for(int i=0; i<cookies.length; i++) {
			System.out.println("정답이 아닌 테스트 값 : "+cookies[i].getName());
			if(cookies[i].getName().equals("modelKey")) {
				System.out.println("정답 : "+cookies[i].getValue());
				index=i;
			}
			
		}
		System.out.println("사용자 입력값 : "+ vo.getAuthKey()); 
		String serverKey=cookies[index].getValue();//생성된 인증번호
		String userKey=vo.getAuthKey();//유저가 입력한 인증번호
		
		if(serverKey.equals(userKey)) {
			result=1;
				if(cookies != null){ // 쿠키가 한개라도 있으면 실행
					for(int i=0; i<cookies.length; i++) {
						cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
						response.addCookie(cookies[i]); // 응답 헤더에 추가
					}
			}
		}
		System.out.println("result is : "+result);

		return result;
	}
	
	//e-mail 인증 컨트롤러
	@RequestMapping(value="/keyAlter", method=RequestMethod.POST)
	public void key_alterConfirm(MemberVO vo) throws Exception{
		
		logger.info("keyAlter ina");
		logger.info("vo : "+vo);
		mailsender.keyAlterConfirm(vo);		
	}

	// 로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("memberVO") MemberVO memberVO) {
		return "/member/login";
	}

	// 로그인 Post
	/*
	 * 서비스 계층까지 구현이 완료되었으면 이제 암호화 작업, 인터셉트 적용을 해야한다. 이 때 가장 중요한 것은 컨트롤러에서
	 * HttpSession 객체를 처리할 것인지, 인터셉터에서 HttpSession을 처리할 것인지 정하는 것! 스프링 MVC는 컨트롤러에서
	 * 필요한 모든 자원을 파라미터에서 수집해서 처리하기 때문에 HttpServletRequest나 HttpSession과 같은 자원들 역시
	 * 파라미터로 처리해도 문제가 없다. 그래서 컨트롤러에서는 되도록 순수하게 데이터를 만들어내는데 집중하고, 인터셉터를 이용해
	 * HttpSession을 처리하도록 작성한다.
	 */
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(MemberVO vo, HttpServletRequest req, HttpSession session, RedirectAttributes rttr, Model model) throws Exception {
		logger.info("post login");

		session = req.getSession();
		MemberVO memberVO = service.login(vo);
		System.out.println("session : "+session);
		System.out.println("memberVO : " + memberVO);
		System.out.println("vo : " + vo);

		if (memberVO == null || !BCrypt.checkpw(vo.getUserPass(), memberVO.getUserPass())) {
			System.out.println("password is wrong");
			
			 //session.setAttribute("member", null); 
			 //rttr.addFlashAttribute("msg", false);
			 //response.sendRedirect("/member/login");
			 return;
		}

		model.addAttribute("member", memberVO);
		System.out.println("password correct");
		
		/*
		 * 기존코드 else { session.setAttribute("member", db_login); return
		 * "redirect:/board/list";
		 * 
		 * }
		 * 
		 * return "redirect:/";
		 */
		
		/*
		 * loginPost 처리과정 화면으로부터 받은 데이터(회원아이디, 비밀번호)중에서 아이디를 통해 select한 회원 정보를 변수
		 * memberVO에 담는다. memberVO가 null이거나 비밀번호를 BCrypt.checkpw()를 통해 검증해서 맞지않으면 메소드를
		 * 종료시킨다. 비밀번호가 일치하면 model에 memberVO를 member란 이름의 변수에 저장한다.
		 */
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("logout");
		session.invalidate();

		return "redirect:/member/login";
	}

}
