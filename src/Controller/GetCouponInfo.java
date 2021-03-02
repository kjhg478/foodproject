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

import Dto.EventInfoBean;
import Service.CouponService;
import Service.Events;

@WebServlet("/GetCouponInfo")
public class GetCouponInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCouponInfo() {
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
		
		ArrayList<EventInfoBean> list = new ArrayList<EventInfoBean>();
		
		HttpSession hs = request.getSession();
		EventInfoBean eib = new EventInfoBean();
		eib.setEventMeid((String)hs.getAttribute("id"));
		
		Events ev = new Events();
		ev.enterance(1,eib,list);
		
		if(eib.getEventCode() == null) {
			request.setAttribute("list", list);
			request.setAttribute("code", eib.getEventCode());
			request.setAttribute("is", false);
			request.getRequestDispatcher("Event.jsp").forward(request, response);
			return;
		}else {
			CouponService cs = new CouponService();
			if(cs.enterance(2, eib.getEventMeid(), eib.getEventCode())) {
				request.setAttribute("list", list);
				request.setAttribute("code", eib.getEventCode());
				request.setAttribute("is", true);
				request.getRequestDispatcher("Event.jsp").forward(request, response);
				return;
			}else {
				request.setAttribute("list", list);
				request.setAttribute("code", eib.getEventCode());
				request.setAttribute("comment", eib.getEventcomment());
				request.setAttribute("is", false);
				request.getRequestDispatcher("Event.jsp").forward(request, response);
				return;
			}
		}
		
		
		
		
		}
}











