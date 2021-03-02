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

@WebServlet("/OneStoreInfo")
public class OneStoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OneStoreInfo() {
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
		
		StoreService ss = new StoreService();
		ss.enterance(6, 0, 0, sib, null);
		
		if (sib.getStoreCategory()!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("StoreDetail.jsp");
			request.setAttribute("sib", sib);
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "데이터를 불러올 수 없습니다.");
			rd.forward(request, response);
		}
	}

}









