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
	
	//ȸ������ get
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegister() throws Exception{
		logger.info("get register");
	}
	
	//ȸ������ ������ �̵��� ������ GET�޼��带 Ÿ�� ȸ������ ��ư�� ������ �� POST�޼��带 Ÿ�Բ� �ۼ��Ѵ�.
	
	//ȸ������ post
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
	
	//ȸ������ ����
	@RequestMapping(value="/memberUpdateView", method=RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		logger.info("memberUpdateView");
		
		return "member/memberUpdateView";
	}
	//ȸ������ ���� POST
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		/*
		 * ȸ������ ���� ���������� ������ ������ /memberUpdate ��û�� �ϰ�, �Ķ���͵��� service.memberUpdate(vo)�� �־��༭ service�� ������.
		 * �׸��� session.invalidate()�� ������ ���� �α��� �������� redirect���ش�.
		 */
		service.memberUpdate(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	//ȸ��Ż�� GET
	@RequestMapping(value="/memberDeleteView", method=RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		logger.info("memberDeleteView");
		
		return "member/memberDelete";
	}
	
	//ȸ��Ż�� POST
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		//���ǿ� �ִ�  member�� ������ member ������ �ִ´�.
		MemberVO member=(MemberVO)session.getAttribute("member");
		//���ǿ� �ִ� ��й�ȣ(�α����Ҷ� �����)
		String sessionPass=member.getUserPass();
		//vo�� ������ �н����� (���� �Է��ϴ�)
		String voPass=vo.getUserPass();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			
			return "redirect:/member/memberDeleteView";
		}
		
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
	
	//id�ߺ� üũ
	@ResponseBody
	@RequestMapping(value="/idChk", method=RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception{
		int result=service.idChk(vo);
		return result;
	}
	
	//�α��� Post
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
	
	
	//�α׾ƿ�
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("logout");
		session.invalidate();
		
		return "redirect:/";
	}
}
