package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dto.EventInfoBean;
import Dto.StoreInfoBean;
import Service.CouponService;
import Service.StoreService;

@WebServlet("/CreateCoupon")
public class CreateCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateCoupon() {
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
		
		int size = 10 * 1024 * 1024; 
		String savePath = "D:/Project/newFolderProject/WebContent/images"; 
		
		MultipartRequest multi = new MultipartRequest(
				request,	
				savePath,	
				size,		
				"UTF-8",	
				new DefaultFileRenamePolicy()
		);
		
		EventInfoBean eib = new EventInfoBean();
		
		
		eib.setEventCode(multi.getParameter("code"));
		eib.setEventfunction(multi.getParameter("coupons"));
		eib.setEventcomment(multi.getParameter("comment"));
		eib.setEventImg((multi.getOriginalFileName((String)multi.getFileNames().nextElement())));

	
		CouponService cs = new CouponService();
		
		if (cs.enterance(1, eib,null)) {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "쿠폰 등록 요청을 성공했습니다.");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
			request.setAttribute("result", 1);
			request.setAttribute("value", "쿠폰 코드가 중복되어 쿠폰 등록 요청이 실패했습니다.");
			rd.forward(request, response);
		}
	}

}













