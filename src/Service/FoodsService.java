package Service;

import static Dao.DataAccessObject.*;
import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import Dao.DataAccessObject;
import Dto.FoodInfoBean;
import Dto.StoreInfoBean;

public class FoodsService {
	DataAccessObject dao;
	Connection con;
	public FoodsService() {
		dao = getInstance();
		con = getConnection();
		dao.setConnection(con);
	}
	public boolean enterance(int request, ArrayList<FoodInfoBean> list, String Code) {
		boolean result = false;
		switch (request) {
		case 1:
			getFoodsInfo(list,Code);
			break;
		case 2:
			result = getRandomFoods(list);
			break;
		case 3:
			result = getTopFoods(list,Code);
			break;
		default:
			break;
		}
		return result;
	}
	

	// overLoading
	public void enterance(int request, ArrayList<StoreInfoBean> storeList, String foodCode, String userId) {
		switch (request) {
		case 1:
			setFoodRank(storeList, foodCode, userId);
			break;
		default:
			break;
		}
	}
	// 통계 푸드정보 가져오기
	private boolean getTopFoods(ArrayList<FoodInfoBean> list, String id) {
		boolean result = false;
		dao.getMyfoodsInfo(list, id);
		dao.GetFoodsInfo(list);
		close(con);
		return result;
	}

	private void getFoodsInfo(ArrayList<FoodInfoBean> list, String foodCode) {
		
		dao.GetFoodsInfo(list,foodCode);
		close(con);
	}
	
	private void setFoodRank(ArrayList<StoreInfoBean> storeList, String foodCode, String userId) {
		if(dao.setFoodRank(foodCode, userId)>0) {
			String add = "ICND%";
			dao.getRecommendRst(storeList, add,foodCode);
			commit(con);
			
		}else {
			rollback(con);
		}
		close(con);
	}
	
	private boolean getRandomFoods(ArrayList<FoodInfoBean> list) {
		boolean result = false;
		if(dao.getRandomFoods(list))result = true;
		close(con);
		return result;
	}
	
	
	
}
