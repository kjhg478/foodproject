package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dto.UserInfoBean;
import Service.Management;

@WebServlet("/UserDel")
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDel() {
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
		UserInfoBean uib = new UserInfoBean();
		
		uib.setUserId(request.getParameter("id"));
		Management mg = new Management();
		
		if (mg.enterance(6, uib, null)) {
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		request.setAttribute("result", 3);
		request.setAttribute("value", "정상적으로 탈퇴되었습니다. 이용해주셔서 감사했습니다.^0^");
		HttpSession hs = request.getSession();
		hs.invalidate();
		rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "아이디를 다시 한번 확인해주세요.");
			rd.forward(request, response);
		}
		
	}
	

}
