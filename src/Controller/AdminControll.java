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

@WebServlet("/AdminControll")
public class AdminControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminControll() {
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
		sib.setStoreCode(request.getParameter("code"));
		int state = Integer.parseInt(request.getParameter("state"));
		//1이면 등록 2면 등록해제
		
		StoreService ss = new StoreService();
		int result = 0;
		result = ss.enterance(5, state, 0, sib, null);
		if (result>0) {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result",8);
			if (state==1) {
	            request.setAttribute("state", 2);   
	         }else if(state==2) {
	            request.setAttribute("state", 3);
	         }
			request.setAttribute("value","업데이트가 완료되었습니다.");
			rd.forward(request, response);

		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result",1);
			request.setAttribute("value","업데이트를 실패하였습니다.");
			rd.forward(request, response);
		}
		
		}
		
	}
	










