package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Redefinable;

import Service.CouponService;

@WebServlet("/CouponReg")
public class CouponReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CouponReg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String meid = request.getParameter("id");
		String coupon = request.getParameter("coupon");
		
		CouponService cs = new CouponService();
		boolean result = cs.enterance(1, meid, coupon);
		
		if (result) {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 7);
			request.setAttribute("value", "응모에 성공했습니다.");
			rd.forward(request, response);
		}else {
			response.sendRedirect("isCouponApply");
		}
	}

}











