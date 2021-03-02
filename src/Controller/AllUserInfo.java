package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.BoardInfoBean;
import Dto.UserInfoBean;
import Dto.pageInfoBean;
import Service.Management;

@WebServlet("/AllUserInfo")
public class AllUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllUserInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ArrayList<UserInfoBean> list = new ArrayList<UserInfoBean>();
		
		Management mg = new Management();
		

		int page = 1;	// 처음 시작 페이지
		int limit = 10; 	// 한페이지에 표시되는 게시글의 수

		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}


		// DB에서 유저 총원 가져오기
		int listCount = mg.enterance(2,0,0,null);

		//1페이지 : page = 1 / startRow = 1 / endRow = 10
		//2페이지 : page = 2 / startRow = 11 / endRow = 20
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int)((double)listCount / limit + 0.9);

		int startPage = (((int)((double)page/10 + 0.9))-1) * 10 +1;

		// 현재페이지에 보여줄 끝페이지
		int endPage = startPage + 10 -1;

		// endPage가 maxPage보다 클경우 
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		mg.enterance(1,startRow,endRow,list);
		
		pageInfoBean paging = new pageInfoBean();

		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setListCount(listCount);

		RequestDispatcher rd = request.getRequestDispatcher("MemberList.jsp");
		request.setAttribute("list", list);
		request.setAttribute("paging",paging);
		rd.forward(request, response);
	}


}











