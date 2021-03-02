package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.StoreInfoBean;
import Service.StoreService;

@WebServlet("/StoreDel")
public class StoreDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StoreDel() {
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
		
		StoreInfoBean sib = new StoreInfoBean();
		String code = request.getParameter("code");
		
		StoreService ss = new StoreService();
		int result = ss.enterance(7, 0, 0, sib, null);
		
		if (result>0) {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "정상적으로 삭제되었습니다.");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "가게를 삭제하는데 실패했습니다.");
			rd.forward(request, response);
		}
		
	}

}
