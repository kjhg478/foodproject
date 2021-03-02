package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.UserInfoBean;
import Service.Management;

/**
 * Servlet implementation class UserLevelUp
 */
@WebServlet("/UserLevelUp")
public class UserLevelUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserLevelUp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserInfoBean uib = new UserInfoBean();
		uib.setUserId(request.getParameter("id"));
		String page =request.getParameter("page");
		String value = request.getParameter("value");
		boolean result = false;
		Management mg = new Management();
		if (value.equals("down")) {
			uib.setRequestValue(value);
			result=mg.enterance(5, uib, null);	
		}else if (value.equals("up")) {
			uib.setRequestValue(value);
			result=mg.enterance(5, uib, null);	
		}
		
		
		if (result) {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 6);
			request.setAttribute("page", page);
			request.setAttribute("value", "업데이트가 완료되었습니다.");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 6);
			request.setAttribute("page", page);
			request.setAttribute("value", "업데이트를 실패하였습니다.");
			rd.forward(request, response);
		}
		
		
	}

}









