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
import Dto.FoodInfoBean;
import Service.CouponService;
import Service.Events;
import Service.FoodsService;

@WebServlet("/getTopFoodsInfo")
public class getTopFoodsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getTopFoodsInfo() {
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
		FoodsService fs = new FoodsService();
		ArrayList<FoodInfoBean> foods = new ArrayList<FoodInfoBean>();

		HttpSession hs =  request.getSession();
		String id = (String)hs.getAttribute("id");

		fs.enterance(3, foods, id);
		boolean my = false;
		boolean all = false;
		for (FoodInfoBean i : foods) {
			if(i.getFoState().equals("M"))my=true;
			if(i.getFoState().equals("T"))all=true;
		}
		request.setAttribute("my", my);
		request.setAttribute("all", all);
		request.setAttribute("foods", foods);
		request.getRequestDispatcher("StatisticsRank.jsp").forward(request, response);
		return;

	}
}











