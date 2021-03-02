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
import Dto.StoreInfoBean;
import Dto.pageInfoBean;
import Service.StoreService;
@WebServlet("/GetStoreInfo")
public class GetStoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetStoreInfo() {
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
		
		int requests = Integer.parseInt(request.getParameter("request"));
		StoreService ss = new StoreService();
		
		int page = 1;	// 처음 시작 페이지
		int limit = 5; 	// 한페이지에 표시되는 게시글의 수
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));}
		
		int StoreCount = 0;
		
		if (requests==2) {
			StoreCount = ss.enterance(4,requests,0,null, null);
		}else if (requests==3) {
			StoreCount = ss.enterance(4,requests,0,null, null);
		}

		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int)((double)StoreCount / limit + 0.9);
		
		int startPage = (((int)((double)page/10 + 0.9))-1) * 10 +1;
		
		int endPage = startPage + 10 -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pageInfoBean paging = new pageInfoBean();
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setListCount(StoreCount);
		
		if (requests==2) { // 
			ArrayList<StoreInfoBean> Rlist = new ArrayList<StoreInfoBean>();
			//STATE가 R인것만 가져오기
			ss.enterance(2,startRow,endRow,null,Rlist);
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("paging",paging);
			request.setAttribute("list", Rlist);
			request.setAttribute("request", "Register");
			request.setAttribute("value", "Ready");
			request.setAttribute("state", 1);
			rd.forward(request, response);
		}
		else if (requests==3) {
			ArrayList<StoreInfoBean> Llist = new ArrayList<StoreInfoBean>();
			//STATE가 L인것만 가져오기
			ss.enterance(3,startRow,endRow, null,Llist);
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("paging",paging);
			request.setAttribute("list", Llist);
			request.setAttribute("value", "Launch");
			request.setAttribute("request", "DeRegister");
			request.setAttribute("state", 2);
			rd.forward(request, response);
		}
			
			
		}
	}














