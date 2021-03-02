package Service;

import static Dao.DataAccessObject.*;
import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import Dao.DataAccessObject;
import Dto.StoreInfoBean;

public class StoreService {

	DataAccessObject dao;
	Connection con;
	public StoreService() {
		dao = getInstance();
		con = getConnection();
		dao.setConnection(con);
	}
	
	public int enterance(int request,int start,int end,StoreInfoBean sib,ArrayList<StoreInfoBean> Rlist) {
		int result = 0;
		switch (request) {
		case 1:// 가게 등록
			result=StoreReg(sib);
			break;
		case 2:// 요청중인 가게 정보
			RStoreInfo(Rlist,start,end);
			break;
		case 3:// 등록된 가게 정보
			LStoreInfo(Rlist,start,end);
			break;
		case 4:// 가게 갯수 가져오기
			result=StoreCount(start);
			break;
		case 5:// 가게 등록,해제 하기
			result=StateUpdate(start,sib);
			break;
		case 6:// 가게 상세보기
			StoreInfo(sib);
			break;
		case 7:// 가게 삭제 가게 메뉴랑 같이 삭제
			result = StoreDel(sib);
			break;
		}
		return result;
	}
	
	private int StoreDel(StoreInfoBean sib) {
		 int result=0;
		 dao.StoreMenuDel(sib);
		 result = dao.StoreDel(sib);
		if (result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	private void StoreInfo(StoreInfoBean sib) {
		   dao.getStoreInfo(sib);
		   close(con);
	}

	private int StateUpdate(int start, StoreInfoBean sib) {
		   int result=0;
		   result = dao.StateUpdate(start,sib);
		   if (result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		   close(con);
		return result;
	}

	private int StoreCount(int start) {
		   int result=0;
		   result=dao.StoreCount(start);
		   // 다음 작업 있어서 con 끄면안됨
		   return result;
	}

	private void LStoreInfo(ArrayList<StoreInfoBean> Llist,int start,int end) {
		   dao.getLtoreInfo(Llist,start,end);
		   close(con);
	}

	private void RStoreInfo(ArrayList<StoreInfoBean> rlist,int start,int end) {
		   dao.getRStoreInfo(rlist,start, end);
		   close(con);
	}

	private int StoreReg(StoreInfoBean sib) {
		      int result = 0;
		      
		      String zip = "ICND%";
		      String currentStoreCode = dao.getStoreCode(zip);
		      
		      String code = currentStoreCode.substring(0,4);
		      String number = currentStoreCode.substring(4);
		      
		      number = (Integer.parseInt(number)+1)+"";
		      
		      for (int i = number.length(); i < 3; i++) {
		         number = "0" + number;
		      }
		      sib.setStoreCode(code+number);
		      
		      result=dao.StoreReg(sib)==true?1:0;
		      if (result==1) {
				commit(con);
			}else {
				rollback(con);
			}
		      close(con);
		      return result;
		   }
}















