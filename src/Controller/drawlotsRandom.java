package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dto.EventInfoBean;
import Dto.StoreInfoBean;
import Service.CouponService;
import Service.StoreService;

@WebServlet("/drawlotsRandom")
public class drawlotsRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public drawlotsRandom() {
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
		
		ArrayList<EventInfoBean> people = new ArrayList<EventInfoBean>();
		EventInfoBean eib = new EventInfoBean();
		
		eib.setEventCode(request.getParameter("code"));
		eib.setPeople(Integer.parseInt(request.getParameter("people")));
		
		
		JSONObject json = new JSONObject();

		
		if(cs.enterance(3 , eib, people)) {
		System.out.println("성공");
		for (EventInfoBean i : people) {
			json.put(i.getEventMeid(),i.getEventName());
		}
		
		String total = json.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(total);
		return;
		}else {
			System.out.println("실패");
			json.put("Can not Drawlots "," Doesn't have any coupon information or No one apply yet!");
			String total = json.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(total);
			return;
		}
		
		
	
	}

}













