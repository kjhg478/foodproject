package Service;

import static Dao.DataAccessObject.*;
import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import Dao.DataAccessObject;
import Dto.EventInfoBean;
import Dto.UserInfoBean;

public class Events {

	DataAccessObject dao;
	Connection con;

	public Events() {
		dao = getInstance();
		con = getConnection();
		dao.setConnection(con);
	}

	public boolean enterance(int request,EventInfoBean eib,ArrayList<EventInfoBean> list) {
		boolean result = false;
		switch (1) {
		case 1:
			GetCouponInfo(eib,list);
			break;
		default:
			break;
		}
		return result;
	}
	private void GetCouponInfo(EventInfoBean eib,ArrayList<EventInfoBean> list) {
		dao.getCouponInfo(eib.getEventMeid(),list);
		dao.getEventInfo(eib);
		close(con);
	}
}










