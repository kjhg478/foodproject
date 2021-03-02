package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.json.simple.JSONObject;

import Dto.EventInfoBean;
import Service.CouponService;
import Service.Events;

@WebServlet("/getApplicant")
public class getApplicant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getApplicant() {
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
		
		CouponService cs = new CouponService();
		EventInfoBean eib = new EventInfoBean();
		cs.enterance(2 , eib, null);
		
		request.setAttribute("people", eib.getPeople());
		request.setAttribute("code", eib.getEventCode());
		
		request.getRequestDispatcher("DrawlotsCoupon.jsp").forward(request, response);
		}
}











