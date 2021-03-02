package Service;

import static Dao.DataAccessObject.*;
import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.If;

import Dao.DataAccessObject;
import Dto.TotalInfoBean;
import Dto.UserInfoBean;

public class Management {
	DataAccessObject dao;
	Connection con;
	public Management() {
		dao = getInstance();
		con = getConnection();
		dao.setConnection(con);
	}

	public boolean enterance(int request,UserInfoBean uib,TotalInfoBean tib) {
		boolean result = false;
		switch (request) {
		case 1:
			result = userReg(uib);
			break;
		case 2:
			getTotalData(tib);
			break;
		case 3:
			result= passCheck(uib);
			break;
		case 4:
			result= userMod(uib);
			break;
		case 5:
			result= UserLevelUp(uib);
			break;
		case 6:
			result = UserDel(uib);
			break;
		}
		return result;
	}
	
	private boolean UserDel(UserInfoBean uib) {
		boolean result = false;
		result=dao.UserDel(uib);
		if (result) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return result;
	}

	// 오버로딩
		public int enterance(int request,int startrow,int endrow,ArrayList<UserInfoBean> list) {
			int result = 0;
			switch (request) {
			case 1:
				AllUserInfo(list,startrow,endrow);
				break;
			case 2:
				result=UserInfoCount();
				break;
			}
			return result ;
		}
		
	private boolean UserLevelUp(UserInfoBean uib) {
		boolean result = false;
		result=dao.UserLevelUp(uib);
		if (result) {
			commit(con);
		}else {
			rollback(con);
		}
			close(con);
		
		return result;
	}

	private int UserInfoCount() {
		int result = 0;
		result=dao.UserInfoCount();
		
		return result;
	}

	private void AllUserInfo(ArrayList<UserInfoBean> list,int startrow,int endrow) {
		dao.AllUserInfo(list,startrow,endrow);
		close(con);
	}

	private boolean userReg(UserInfoBean uib) {
		boolean result = false;
		if (dao.GetUesrId(uib)) { // 아이디 중복체크
			if(dao.UesrReg(uib)) { // 회원등록
				result=true;
				commit(con);
				dao.MemberCoupon(uib);
				commit(con);
			}else {
				uib.setRequestValue("2");
				rollback(con);
			}
		}else {
			uib.setRequestValue("3");
		}

		close(con);
		return result;
	}
	private void getTotalData(TotalInfoBean tib) {
		dao.TodayLogin(tib);
		dao.TodayGames(tib);
		dao.TotalStores(tib);
		dao.TotalReviews(tib);
		
		close(con);
	}
	
	private boolean passCheck(UserInfoBean uib) {
		boolean result = false;
		
		result = dao.CheckPwd(uib)==1?true:false;
		
		close(con);
		return result;
	}

	private boolean userMod(UserInfoBean uib) {
		boolean result=false;
		result=dao.UserMod(uib);
		if (result) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

}


















