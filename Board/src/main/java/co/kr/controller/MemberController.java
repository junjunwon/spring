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

	// ȸ������ get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister(MemberVO vo) throws Exception {
		logger.info("get registerView");
		// int name_result=service.nameChk(vo);
		// System.out.println(name_result);
	}

	// ȸ������ ������ �̵��� ������ GET�޼��带 Ÿ�� ȸ������ ��ư�� ������ �� POST�޼��带 Ÿ�Բ� �ۼ��Ѵ�.

	// ȸ������ post
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
		System.out.println("register->confirmemailview�� ������ �ѱ涧 vo getUserId : "+vo.getUserId());
		String getUserId=vo.getUserId();
		/*
		 * ��Ű�� �����ϴ��� ���� Ȯ�� -> ������ �����. userid�� �׻� �迭 0���� �� �� �ְ�.
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

	// ȸ������ ����
	@RequestMapping(value = "/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		logger.info("memberUpdateView");

		return "member/memberUpdateView";
	}

	// ȸ������ ���� POST
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception {
		/*
		 * ȸ������ ���� ���������� ������ ������ /memberUpdate ��û�� �ϰ�, �Ķ���͵��� service.memberUpdate(vo)��
		 * �־��༭ service�� ������. �׸��� session.invalidate()�� ������ ���� �α��� �������� redirect���ش�.
		 */
		logger.info("memberUpdate post");
		System.out.println("������ ������ ��й�ȣ : "+vo.getUserPass());
		String hashedPw=BCrypt.hashpw(vo.getUserPass(), BCrypt.gensalt());
		vo.setUserPass(hashedPw);
		service.memberUpdate(vo);
		session.invalidate();

		return "redirect:/member/login";
	}


	// ȸ��Ż�� GET
	@RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception {
		logger.info("memberDeleteView");

		return "member/memberDelete";
	}
	
	static String temp="";
	//ȸ�� confirmPassword ������
	@RequestMapping(value="/confirmPasswordView", method=RequestMethod.GET)
	public String confirmPasswordDelete(HttpServletRequest request,String pageName, 
					@ModelAttribute("memberVO") MemberVO memberVO) throws Exception{
		logger.info("confirmPasswordView");
		//String page=request.getServletPath().toString();
		temp=request.getParameter("flag").toString();
		/*
		 * ȸ��Ż��, ���� �б⸦ ���� flag�� deletebtn(delete), updatebtn(update)���κ��� �����´�. 
		 */
		System.out.println(temp);
		
		request.setAttribute("test", temp);
		return "member/confirmPasswordView";
	}
	
	 
	
	  //�ּ�ó�� Ǫ�� �� ctrl+shift+\
	  //ȸ��Ż�� confirmPassword Post
	  
	
	  @RequestMapping(value="/confirmPassword", method=RequestMethod.POST) 
	  public String confirmPassword(HttpServletRequest request, MemberVO vo, HttpSession session, Model model) throws
	  Exception{ 
		  logger.info("confirmPassword Post");
		  String result=request.getParameter("result");
		  System.out.println("flag���� "+result);
		  
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
	 
	 
	
	// ȸ��Ż�� POST
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {

		// ���ǿ� �ִ� member�� ������ member ������ �ִ´�.
		MemberVO member = (MemberVO) session.getAttribute("member");
		// ���ǿ� �ִ� ��й�ȣ(�α����Ҷ� �����)
		String sessionPass = member.getUserPass();
		// vo�� ������ �н����� (���� �Է��ϴ�)
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

	// id�ߺ� üũ
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		return result;
	}
	
	// �г��� �ߺ� üũ
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
	
	
	
	//email ������ post
	@RequestMapping(value="/sendEmail", method= RequestMethod.POST)
	public String sendEmail(MemberVO vo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println(vo.getUserEmail()+"=========="+vo.getUserId());
		System.out.println("vo ��ü �� : "+vo);
		vo.setUserEmail(vo.getUserEmail());
		vo.setUserId(vo.getUserId());
		String key=mailsender.mailSendWithUserKey(vo, request);
		model.addAttribute("modelKey", key);
		System.out.println("service�κ��� Ű���� �޾ƿ� : "+key);
		
		Cookie myCookie=new Cookie("modelKey", key);
		response.addCookie(myCookie);
		
		
		return "member/confirmEmailView";
	}
	
	@ResponseBody
	@RequestMapping(value="/authKey", method=RequestMethod.POST)
	public int authKey(MemberVO vo, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Cookie[] cookies=request.getCookies();
		int index=0;
		int result=0; //0�̸� ������ȣ ����, 1�̸� ������ȣ ����.
		for(int i=0; i<cookies.length; i++) {
			System.out.println("������ �ƴ� �׽�Ʈ �� : "+cookies[i].getName());
			if(cookies[i].getName().equals("modelKey")) {
				System.out.println("���� : "+cookies[i].getValue());
				index=i;
			}
			
		}
		System.out.println("����� �Է°� : "+ vo.getAuthKey()); 
		String serverKey=cookies[index].getValue();//������ ������ȣ
		String userKey=vo.getAuthKey();//������ �Է��� ������ȣ
		
		if(serverKey.equals(userKey)) {
			result=1;
				if(cookies != null){ // ��Ű�� �Ѱ��� ������ ����
					for(int i=0; i<cookies.length; i++) {
						cookies[i].setMaxAge(0); // ��ȿ�ð��� 0���� ����
						response.addCookie(cookies[i]); // ���� ����� �߰�
					}
			}
		}
		System.out.println("result is : "+result);

		return result;
	}
	
	//e-mail ���� ��Ʈ�ѷ�
	@RequestMapping(value="/keyAlter", method=RequestMethod.POST)
	public void key_alterConfirm(MemberVO vo) throws Exception{
		
		logger.info("keyAlter ina");
		logger.info("vo : "+vo);
		mailsender.keyAlterConfirm(vo);		
	}

	// �α��� ������
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("memberVO") MemberVO memberVO) {
		return "/member/login";
	}

	// �α��� Post
	/*
	 * ���� �������� ������ �Ϸ�Ǿ����� ���� ��ȣȭ �۾�, ���ͼ�Ʈ ������ �ؾ��Ѵ�. �� �� ���� �߿��� ���� ��Ʈ�ѷ�����
	 * HttpSession ��ü�� ó���� ������, ���ͼ��Ϳ��� HttpSession�� ó���� ������ ���ϴ� ��! ������ MVC�� ��Ʈ�ѷ�����
	 * �ʿ��� ��� �ڿ��� �Ķ���Ϳ��� �����ؼ� ó���ϱ� ������ HttpServletRequest�� HttpSession�� ���� �ڿ��� ����
	 * �Ķ���ͷ� ó���ص� ������ ����. �׷��� ��Ʈ�ѷ������� �ǵ��� �����ϰ� �����͸� �����µ� �����ϰ�, ���ͼ��͸� �̿���
	 * HttpSession�� ó���ϵ��� �ۼ��Ѵ�.
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
		 * �����ڵ� else { session.setAttribute("member", db_login); return
		 * "redirect:/board/list";
		 * 
		 * }
		 * 
		 * return "redirect:/";
		 */
		
		/*
		 * loginPost ó������ ȭ�����κ��� ���� ������(ȸ�����̵�, ��й�ȣ)�߿��� ���̵� ���� select�� ȸ�� ������ ����
		 * memberVO�� ��´�. memberVO�� null�̰ų� ��й�ȣ�� BCrypt.checkpw()�� ���� �����ؼ� ���������� �޼ҵ带
		 * �����Ų��. ��й�ȣ�� ��ġ�ϸ� model�� memberVO�� member�� �̸��� ������ �����Ѵ�.
		 */
	}

	// �α׾ƿ�
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("logout");
		session.invalidate();

		return "redirect:/member/login";
	}

}
