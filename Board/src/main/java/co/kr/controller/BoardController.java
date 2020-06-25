package co.kr.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.service.BoardService;
import co.kr.service.ReplyService;
import co.kr.vo.BoardVO;
import co.kr.vo.PageMaker;
import co.kr.vo.ReplyVO;
import co.kr.vo.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	ReplyService replyService;
	
	//게시판 글 작성 화면
	
	@RequestMapping(value="/board/writeView", method=RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("writeView");
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception{
		logger.info("write");
		
		service.write(boardVO);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list");
		
		model.addAttribute("list", service.list(scri));
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
		
	}
	//URL은 /list로 정하고 오라클 > 다오 > 서비스 > 컨트롤러로 가져온 데이터들을 jsp에 뿌려주는 작업을 해야한다.
	//model은 데이터를 담을 그릇이고 addAttribute("list", service.list())는 service.list()에 담긴 데이터를 "list"라는 이름으로 담을 것이다.
	
	@RequestMapping(value="/readView", method= RequestMethod.GET)
	public String read(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("read", service.read(boardVO.getBno()));
		model.addAttribute("scri", scri);
		
		//댓글 조회
		List<ReplyVO> replyList=replyService.readReply(boardVO.getBno());
		model.addAttribute("replyList", replyList);
		//댓글은 게시물에 종속되어 있기 때문에 같은 controller안에 readView에서 함께 불러온다.
		
		return "board/readView";
	}
	
	//게시판 수정뷰
	@RequestMapping(value="/updateView", method= RequestMethod.GET)
	public String updateView(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri,Model model) throws Exception{
		logger.info("updateView");
		model.addAttribute("update", service.read(boardVO.getBno()));
		model.addAttribute("scri", scri);
		
		return "board/updateView";
	}
	
	//게시판 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("update");
		
		service.update(boardVO);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	//게시판 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("delete");
		service.delete(boardVO.getBno());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	//댓글 작성
	@RequestMapping(value="/replyWrite", method=RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		
		logger.info("replyWrite");
		
		replyService.writeReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
		
	}
	
	//댓글 수정 GET - 수정 페이지에 접근하기 위한 컨트롤러
	@RequestMapping(value="/replyUpdateView", method=RequestMethod.GET)
	public String replyUpdateView(ReplyVO vo, SearchCriteria scri, Model model) throws Exception{
		logger.info("replyUpdateView");
		
		model.addAttribute("replyUpdate", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);
		
		return "board/replyUpdateView";
	}
	
	//댓글 수정 POST - 수정한 값을 전송할 수 있는 컨트롤러
	@RequestMapping(value="/replyUpdate", method=RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("replyUpdate");
		
		replyService.updateReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
	
	//댓글 삭제 GET
	@RequestMapping(value="/replyDeleteView", method=RequestMethod.GET)
	public String replyDeleteView(ReplyVO vo, SearchCriteria scri, Model model) throws Exception{
		
		logger.info("replyDeleteView");
		
		model.addAttribute("replyDelete", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);
		
		return "/board/replyDeleteView";
	}
	
	//댓글 삭제 POST
	@RequestMapping(value="/replyDelete", method=RequestMethod.POST)
	public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		
		logger.info("replyDelete");
		
		replyService.deleteReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
}
