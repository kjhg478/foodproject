package Service;

import static Dao.DataAccessObject.*;
import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import Dao.DataAccessObject;
import Dto.EventInfoBean;
import Dto.UserInfoBean;

public class CouponService {

	DataAccessObject dao;
	Connection con;

	public CouponService() {
		dao = getInstance();
		con = getConnection();
		dao.setConnection(con);
	}

	public boolean enterance(int request,String id, String coupon) {
		boolean result = false;
		switch (request) {
		case 1:
			result = CouponReg(id,coupon);
		case 2:
			result = Couponcheck(id,coupon);
			break;
		}
		return result;
	}
	// 오버로딩
	public boolean enterance(int request, EventInfoBean eib, ArrayList<EventInfoBean> people) {
		boolean result = false;
		switch (request) {
		case 1:
			result = setCouponInfo(eib);
			break;
		case 2:
			result = getApllyInfo(eib);
			break;
		case 3:
			result = getDrawlots(eib, people);
			break;
		}
		return result;
	}


	private boolean getDrawlots(EventInfoBean eib, ArrayList<EventInfoBean> people) {
		boolean result = false;
		
		if(dao.getDrawlots(eib, people)) {
			for(EventInfoBean i : people) {
				result = dao.insertEvent(i.getEventMeid(), eib.getEventCode());
			}
			if(result) {
				if(dao.comDrawlots(eib)) {	
					result = false;
					if(dao.comCoupon(eib)) {	
						commit(con);
						result = true;
					}else {
						rollback(con);
					}				
				}
			}
		}
		close(con);
		return result;
	}

	private boolean getApllyInfo(EventInfoBean eib) {
		boolean result = false;
		if(dao.getEventInfo(eib)) {
			dao.getApllyInfo(eib);
		}	
		close(con);
		return result;
	}

	private boolean setCouponInfo(EventInfoBean eib) {

		boolean result = false;
		if (dao.isSetCoupon(eib)) {

			result=dao.setCouponInfo(eib);
			if (result) {

				commit(con);
			}else {
				rollback(con);
			}
		}
		close(con);
		return result;
	}
	
	public boolean Couponcheck(String id, String coupon) {
		boolean result = true;
		if (dao.CouponCheck(id,coupon)) {result = false;}
		close(con);
		return result;
	}

	public boolean CouponReg(String id, String coupon) {
		boolean result = false;
		if (dao.CouponCheck(id,coupon)) {
			result=dao.CouponReg(id,coupon);
			if (result) {
				commit(con);
			}else {
				rollback(con);
			}
		}
		return result;
	}
}








