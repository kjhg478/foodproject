package Dao;

import static Db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.Closeable;

import Dao.DataAccessObject;
import Dto.UserInfoBean;
import Dto.pageInfoBean;
import Dto.BoardInfoBean;
import Dto.EventInfoBean;
import Dto.FoodInfoBean;
import Dto.StoreInfoBean;
import Dto.TotalInfoBean;

public class DataAccessObject {
	private static DataAccessObject dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// getInstance메소드
	public static DataAccessObject getInstance() {
		if (dao == null) {
			dao = new DataAccessObject();
		}
		return dao;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int CheckId(UserInfoBean uib) {
		//아이디 비교
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ME_ID=?";
		int result = 0;
		try {pstmt = con.prepareStatement(sql);

		pstmt.setString(1, uib.getUserId());

		rs = pstmt.executeQuery();
		if (rs.next()) {
			result=rs.getInt(1);
		}

		if (result<0) {
			pstmt.close();
			rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int CheckPwd(UserInfoBean uib) {
		// 아이디 비밀번호 동시 비교
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ME_ID=? AND ME_PWD=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uib.getUserId());
			pstmt.setString(2, uib.getUserPwd());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result=rs.getInt(1);
			}

			if (result<0) {
				pstmt.close();
				rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return result;

	}

	public void GetUserInfo(UserInfoBean uib) {
		//로그인 성공시 정보 가져오기
		String sql = "SELECT ME_NAME, ME_ADD, ME_LEVEL FROM MEMBER WHERE ME_ID=? AND ME_PWD=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uib.getUserId());
			pstmt.setString(2, uib.getUserPwd());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				uib.setUserNickname(rs.getString(1));
				uib.setUserAdd(rs.getString(2));
				uib.setUserLevel(rs.getInt(3));

			}
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean UesrReg(UserInfoBean uib) {
		String sql= "INSERT INTO MEMBER	VALUES(?,?,?,?,?)";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uib.getUserId());
			pstmt.setString(2, uib.getUserPwd());
			pstmt.setString(3, uib.getUserAdd());
			pstmt.setString(4, uib.getUserNickname());
			pstmt.setInt(5, 1);// 회원 레벨 1

			result = pstmt.executeUpdate()==1?true:false;
			if (result==false) {
				pstmt.close();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean GetUesrId(UserInfoBean uib) {
		String sql= "SELECT COUNT(*) FROM MEMBER WHERE ME_ID=?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uib.getUserId());

			rs= pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1)==1?false:true;
			}
			if (result==false) {
				pstmt.close();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	public int CommunityCount() {
		String sql = "SELECT COUNT(*) FROM COMMUNITY";
		int listCount = 0;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	public void CommunityList(int startRow, int endRow,ArrayList<BoardInfoBean> list) {
		String sql = "SELECT * FROM COMMUNITYTHUMBS WHERE RN BETWEEN ? AND ?";
		BoardInfoBean board = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardInfoBean();
				
				board.setThumbs(rs.getInt("TOP"));
				board.setNum(rs.getInt("CO_NUM"));
				board.setTitle(rs.getString("CO_TITLE"));
				
				board.setContent(rs.getString("CO_COMMENT"));
				board.setDate(rs.getString("CO_DATE"));
				board.setFile(rs.getString("CO_FILE"));
				board.setWriter(rs.getString("CO_MEID"));
				

				list.add(board);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

	}
	public void OneCommunityView(int bNum,BoardInfoBean board) {
		String sql = "SELECT * FROM  COMMUNITYTHUMBS WHERE CO_NUM = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				board.setThumbs(rs.getInt("TOP"));
				board.setNum(rs.getInt("CO_NUM"));
				board.setWriterId(rs.getString("ME_ID"));
				board.setWriter(rs.getString("CO_MEID"));
				board.setTitle(rs.getString("CO_TITLE"));
				board.setContent(rs.getString("CO_COMMENT"));
				board.setDate(rs.getString("CO_DATE"));
				board.setFile(rs.getString("CO_FILE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	}
	
	public int CommunityMod(BoardInfoBean bib) { //게시판 수정
		String sql = "UPDATE COMMUNITY SET CO_TITLE=?, CO_COMMENT=?, CO_FILE=? WHERE CO_NUM=?";
		int proResult = 0;
		System.out.println(bib.getTitle());
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bib.getTitle());
			pstmt.setString(2, bib.getContent());
			pstmt.setString(3, bib.getFile());
			pstmt.setInt(4, bib.getNum());

			proResult = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return proResult;
	}

	public int CommunityDel(int bNum) {
		int delResult = 0;
		String sql = "DELETE FROM COMMUNITY WHERE CO_NUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			delResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return delResult;
	}

	public int CommunityWrite(BoardInfoBean board) {
		String sql = "INSERT INTO COMMUNITY VALUES(?,CO_NUM.NEXTVAL,SYSDATE,?,?,?)";
		int writeResult = 0;

		try {pstmt = con.prepareStatement(sql);

		pstmt.setString(1, board.getWriter());
		pstmt.setString(2, board.getContent());
		pstmt.setString(3, board.getFile());
		pstmt.setString(4, board.getTitle());

		writeResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return writeResult;
	}

	public void GetTop3(ArrayList<BoardInfoBean> list) {
		String sql="   SELECT * FROM(SELECT * FROM COMMUNITYTHUMBS ORDER BY TOP DESC) WHERE TOP IS NOT NULL AND ROWNUM BETWEEN 1 AND 3";
		boolean result = false; 
		BoardInfoBean board;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardInfoBean();

				board.setThumbs(rs.getInt("TOP"));
				board.setNum(rs.getInt("CO_NUM"));
				board.setTitle(rs.getString("CO_TITLE"));
				
				board.setContent(rs.getString("CO_COMMENT"));
				board.setDate(rs.getString("CO_DATE"));
				board.setFile(rs.getString("CO_FILE"));
				board.setWriter(rs.getString("CO_MEID"));

				list.add(board);

			}
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void GetFoodsInfo(ArrayList<FoodInfoBean> list, String foodCode) {
		String sql = "SELECT * \r\n"
				+ "FROM(SELECT FO_CODE,FO_ENNAME,FO_FILENAME,FO_COMMENT, CA_TITLE \r\n"
				+ "FROM FOODS INNER JOIN CATEGORY ON FO_CACODE = CA_CODE \r\n"
				+ "WHERE FO_CACODE LIKE ? AND FO_FILENAME IS NOT NULL \r\n"
				+ "ORDER BY DBMS_RANDOM.VALUE)\r\n"
				+ "WHERE ROWNUM <= 16";
		FoodInfoBean fib; 
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, foodCode);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				fib = new FoodInfoBean();

				fib.setFoCode(rs.getString(1));;
				fib.setFoName(rs.getString(2));
				fib.setFoFileName(rs.getString(3));
				fib.setFoComment(rs.getString(4));
				fib.setFoCategory(rs.getString(5));

				list.add(fib);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}

	}

	public void TodayLogin(TotalInfoBean tib) {
		String sql="SELECT COUNT(*) FROM USERLOG WHERE TO_CHAR(UL_DATE,'YYYYMMDD')= TO_CHAR(SYSDATE,'YYYYMMDD') AND UL_STATE=1";
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				tib.setTodayLogin(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void TodayGames(TotalInfoBean tib) {
		String sql="SELECT COUNT(*) FROM HISTORY WHERE TO_CHAR(HI_RANKDATE,'YYYYMMDD')= TO_CHAR(SYSDATE,'YYYYMMDD')";
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				tib.setTodayGames(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void TotalStores(TotalInfoBean tib) {
		// 등록되면 L 요청중이면 R
		String sql="SELECT COUNT(*) FROM STORE WHERE ST_STATE= 'L'";
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				tib.setTotalStores(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void TotalReviews(TotalInfoBean tib) {
		String sql="SELECT COUNT(*) FROM COMMUNITY";
		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				tib.setTotalReviews(rs.getString(1));
			}

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean UserMod(UserInfoBean uib) {
		boolean result = false;
		String sql = "UPDATE MEMBER SET ME_PWD=?,ME_ADD=?,ME_NAME=? WHERE ME_ID=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uib.getUserPwd());
			pstmt.setString(2, uib.getUserAdd());
			pstmt.setString(3, uib.getUserNickname());
			pstmt.setString(4, uib.getUserId());

			result=pstmt.executeUpdate()==1?true:false;

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getStoreCode(String zip) {
		String code = null;
		String sql="SELECT MAX(ST_CODE) FROM STORE WHERE ST_CODE LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, zip);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				code = rs.getString(1);
			}

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;   
	}

	public boolean StoreReg(StoreInfoBean sib) {
		boolean result = false;
		String sql = "INSERT INTO STORE VALUES(?,?,?,?,?,?,'R',?)";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, sib.getStoreCode());
			pstmt.setString(2, sib.getCaCode());
			pstmt.setString(3, sib.getStoreName());
			pstmt.setString(4, sib.getStoreAdd());
			pstmt.setString(5, sib.getStoreComment());
			pstmt.setString(6, sib.getStoreTel());
			pstmt.setString(7, sib.getFileName());

			result = pstmt.executeUpdate()==1?true:false;
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void SetLogin(UserInfoBean uib,String id) {
		String sql ;
		if (id!=null) {
			sql = "INSERT INTO USERLOG VALUES(?,-1,SYSDATE)";	
		}else {
			sql = "INSERT INTO USERLOG VALUES(?,1,SYSDATE)";
		}

		try {
			pstmt = con.prepareStatement(sql);
			if (id!=null) {
				pstmt.setString(1, id);	
			}else {
				pstmt.setString(1, uib.getUserId());
			}

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	// 랭크 등록하기
	public int setFoodRank(String foodCode, String userId) {
		String sql = "INSERT INTO HISTORY VALUES(?,SYSDATE,?)";
		int writeResult = 0;

		try {pstmt = con.prepareStatement(sql);

		pstmt.setNString(1, userId);
		System.out.println(foodCode);
		pstmt.setNString(2, foodCode);

		writeResult = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return writeResult;
	}
	// 레스토랑 가져오기
	public void getRecommendRst(ArrayList<StoreInfoBean> storeList, String add, String caCode) {
		
		StoreInfoBean sib;
		String sql="SELECT * FROM(SELECT * FROM\r\n"
	            + "(SELECT ST_NAME,ST_LOCATION,ST_COMMENT,ST_TEL,ST_FILENAME FROM STORE\r\n"
	            + "WHERE ST_CODE LIKE ? AND ST_CACODE = (SELECT FO_CACODE FROM FOODS\r\n"
	            + "INNER JOIN CATEGORY ON FO_CACODE = CA_CODE\r\n"
	            + "WHERE FO_CODE=?) AND ST_STATE='L')ORDER BY DBMS_RANDOM.VALUE)\r\n"
	            + "WHERE ROWNUM <=3";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, add);
			pstmt.setNString(2, caCode);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				sib = new StoreInfoBean();

				sib.setStoreName(rs.getNString(1));
				sib.setStoreAdd(rs.getNString(2));
				sib.setStoreComment(rs.getNString(3));
				sib.setStoreTel(rs.getNString(4));
				sib.setFileName(rs.getString(5));
				
				storeList.add(sib);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		  
	}
	// 랜덤으로 3개 데이터 가져오기
	public boolean getRandomFoods(ArrayList<FoodInfoBean> list) {
		boolean result = false;
		FoodInfoBean fib;
		String sql="SELECT FO_ENNAME,FO_FILENAME,FO_COMMENT FROM (SELECT * FROM FOODS ORDER BY DBMS_RANDOM.VALUE)\r\n"
				+ "WHERE ROWNUM <= 3";

		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				fib = new FoodInfoBean();

				fib.setFoName(rs.getNString(1));
				fib.setFoFileName(rs.getNString(2));
				fib.setFoComment(rs.getNString(3));
				
				list.add(fib);
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}


	public boolean CheckCount(BoardInfoBean bib) {
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM THUMBS WHERE TH_MEID=? AND TH_CONUM=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bib.getWriterId());
			pstmt.setInt(2, bib.getNum());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1)==0?true:false;
			}
			if (result==false) {
				close(pstmt);
				close(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int ThumbsCount(BoardInfoBean bib) {
		int result = 0;
		String sql = "INSERT INTO THUMBS VALUES(?,?,?,SYSDATE,?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bib.getWriterId()); // 누른사람
			pstmt.setString(2, bib.getWriter()); // 작성자
			pstmt.setInt(3, bib.getThumbs()); // 1,-1
			pstmt.setInt(4, bib.getNum()); //글번호
			
			result=pstmt.executeUpdate();
			
			close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void getRStoreInfo(ArrayList<StoreInfoBean> rlist,int startRow,int endRow) {
		String sql = " SELECT AAA.RN,ST_NAME,ST_LOCATION,ST_COMMENT,ST_CODE  FROM(SELECT ST_NAME,ST_LOCATION,ST_COMMENT,ST_CODE,ROWNUM AS RN,st_state FROM STORE WHERE st_state='R') AAA WHERE (AAA.RN BETWEEN ? AND ?)";
		StoreInfoBean sib;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sib = new StoreInfoBean();
				sib.setStoreName(rs.getString("ST_NAME"));
				sib.setStoreAdd(rs.getString("ST_LOCATION"));
				sib.setStoreComment(rs.getString("ST_COMMENT"));
				sib.setStoreCode(rs.getString("ST_CODE"));
				rlist.add(sib);
			}
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void getLtoreInfo(ArrayList<StoreInfoBean> llist,int startRow,int endRow) {
		String sql = "SELECT AAA.RN,ST_NAME,ST_LOCATION,ST_COMMENT,ST_CODE  FROM(SELECT ST_NAME,ST_LOCATION,ST_COMMENT,ST_CODE,ROWNUM AS RN,st_state FROM STORE WHERE st_state='L') AAA WHERE (AAA.RN BETWEEN ? AND ?)";
		
		StoreInfoBean sib;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sib = new StoreInfoBean();
				sib.setStoreName(rs.getString("ST_NAME"));
				sib.setStoreAdd(rs.getString("ST_LOCATION"));
				sib.setStoreComment(rs.getString("ST_COMMENT"));
				sib.setStoreCode(rs.getString("ST_CODE"));
				llist.add(sib);
			}
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public int StoreCount(int request) {
		int result = 0;
		String sql=null;
		if (request==2) {
			 sql = "SELECT COUNT(*) FROM STORE WHERE ST_STATE = 'R'";
		}else if (request==3) {
			 sql = "SELECT COUNT(*) FROM STORE WHERE ST_STATE = 'L'";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				return result;
	}

	public boolean CouponCheck(String id, String coupon) {
		boolean result = false;
		String sql= "SELECT COUNT(*) FROM APPLY WHERE AP_MEID=? AND AP_COCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, coupon);
			
			rs= pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1)==1?false:true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean CouponReg(String id, String coupon) {
		boolean result = false;
		String sql= "INSERT INTO APPLY VALUES(?,?,DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, coupon);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate()==1?true:false;
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public boolean MemberCoupon(UserInfoBean uib) { 
		boolean result = false;
		String sql = "INSERT INTO EVENTS VALUES('0000000001',?,SYSDATE+(INTERVAL '3' MONTH))";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uib.getUserId());
			
			result=pstmt.executeUpdate()==1?true:false;
			close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getCouponInfo(String id,ArrayList<EventInfoBean> list) {
		EventInfoBean eib;
		String sql= "SELECT EV_EXPIRE,co_comment,co_function,CO_FILE FROM EVENTS INNER JOIN COUPON ON EV_COCODE=CO_CODE WHERE EV_MEID=?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				 eib = new EventInfoBean();
				 
				 eib.setEventexprie(rs.getString(1));
				 eib.setEventcomment(rs.getString(2));
				 eib.setEventfunction(rs.getString(3));
				 eib.setEventImg(rs.getString(4));
				 list.add(eib);
			}
			close(pstmt);
			close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// 댓글 등록
	   public boolean insertReply(BoardInfoBean bib) {
	      
	      boolean result = false;
	      String sql = "INSERT INTO REPLY VALUES(?,SYSDATE,?,?,?)";
	            
	      try {
	         pstmt = con.prepareStatement(sql);
	         
	         pstmt.setInt(1, bib.getNum());
	         pstmt.setString(2, bib.getReply()); 
	         pstmt.setString(3, bib.getID());
	         pstmt.setString(4, bib.getWriter()); 

	         result= pstmt.executeUpdate()==1?true:false;
	         
	         close(pstmt);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	      
	   }
	   
	   // 댓글 삭제
	   public boolean deleteReply(BoardInfoBean bib) {
	      
	      boolean result = false;
	      String sql = "DELETE REPLY WHERE RE_CONUM=? AND RE_DATE = TO_DATE(?,\'YYYY-MM-DD HH24:MI:SS\')";
	            
	      try {
	         pstmt = con.prepareStatement(sql);
	         
	         pstmt.setInt(1, bib.getNum());
	         pstmt.setString(2, bib.getDate()); 

	         result= pstmt.executeUpdate()==1?true:false;
	         
	         close(pstmt);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	      
	   }
	   
	   // 댓글 조회
	   public boolean getReply(pageInfoBean pib, ArrayList<BoardInfoBean> reList) {
	      
	      BoardInfoBean bib;
	      boolean result = false;
	      String sql = "SELECT RE_CONUM,RE_DATE,RE_REPLY,RE_MEID,ME_NAME FROM \r\n"
	            + "(SELECT REPLY.*,ME_NAME,ROW_NUMBER() OVER(ORDER BY RE_DATE DESC) AS RN FROM REPLY \r\n"
	            + "INNER JOIN MEMBER ON RE_MEID = ME_ID WHERE RE_CONUM = ?)WHERE RN BETWEEN ? AND ?";

	      try {
	         pstmt = con.prepareStatement(sql);
	         
	         pstmt.setInt(1, pib.getBoardNum());
	         pstmt.setInt(2, pib.getStartPage()); 
	         pstmt.setInt(3, pib.getEndPage()); 

	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            bib = new BoardInfoBean();
	            bib.setNum(rs.getInt(1));
	            bib.setDate(rs.getString(2));
	            bib.setReply(rs.getString(3));
	            bib.setWriterId(rs.getString(4));
	            bib.setWriter(rs.getString(5));
	            
	            reList.add(bib);
	            result = true;
	         }
	         
	         close(pstmt);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	      
	   }

	   public void getTotalReply(pageInfoBean pib) {
	      
	      String sql = "SELECT count(*) FROM reply where re_conum = ?";

	      try {
	         pstmt = con.prepareStatement(sql);
	         
	         pstmt.setInt(1, pib.getBoardNum());

	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            pib.setMaxPage(rs.getInt(1));
	         }
	         
	         close(pstmt);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return;
	      
	   }

	public void CommunityReplyDel(int bNum) {
		String sql="DELETE FROM REPLY WHERE RE_CONUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bNum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void CommunityThumbsDel(int bNum) {
		String sql="DELETE FROM THUMBS WHERE TH_CONUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bNum);
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int StateUpdate(int start, StoreInfoBean sib) {
		int result = 0;
		String sql = null;
		if (start==1) {sql = "UPDATE STORE SET ST_STATE ='L' WHERE ST_CODE=?";}
		else if(start==2) {sql = "UPDATE STORE SET ST_STATE ='R' WHERE ST_CODE=?";}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sib.getStoreCode());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getStoreInfo(StoreInfoBean sib) {
		String sql = "SELECT ST_NAME,ST_LOCATION,ST_COMMENT,ST_TEL,ST_STATE,CA_TITLE,ST_CODE FROM STORE INNER JOIN CATEGORY ON ST_CACODE = CA_CODE WHERE ST_CODE =?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, sib.getStoreCode());
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				
				sib.setStoreName(rs.getString(1));
				sib.setStoreAdd(rs.getString(2));
				sib.setStoreComment(rs.getString(3));
				sib.setStoreTel(rs.getString(4));
				sib.setStoreState(rs.getString(5));
				sib.setStoreCategory(rs.getString(6));
				sib.setStoreCode(rs.getString(7));
			}
			
			pstmt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public int StoreMenuDel(StoreInfoBean sib) {
		int result = 0;
		String sql = "DELETE FROM STOREMENU WHERE SM_STCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sib.getStoreCode());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int StoreDel(StoreInfoBean sib) {
		int result = 0;
		String sql = "DELETE FROM STORE WHERE ST_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sib.getStoreCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void AllUserInfo(ArrayList<UserInfoBean> list,int startrow,int endrow) {
		String sql = "SELECT * FROM (SELECT MEMBER.*,ROWNUM AS RN FROM MEMBER WHERE ME_LEVEL NOT IN(3)) WHERE RN BETWEEN ? AND ?";
		UserInfoBean uib = null;
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				uib = new UserInfoBean();
				uib.setUserId(rs.getString(1));
				uib.setUserAdd(rs.getString(3));
				uib.setUserNickname(rs.getString(4));
				uib.setUserLevel(rs.getInt(5));
				
				list.add(uib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public int UserInfoCount() {
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ME_LEVEL NOT IN(3)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean UserLevelUp(UserInfoBean uib) {
		String sql=null;
		boolean result = false;
		if (uib.getRequestValue().equals("down")) {
			sql = "UPDATE MEMBER SET ME_LEVEL = ME_LEVEL-1 WHERE ME_ID=?";	
		}else if(uib.getRequestValue().equals("up")){
			sql = "UPDATE MEMBER SET ME_LEVEL = ME_LEVEL+1 WHERE ME_ID=?";}
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uib.getUserId());
			result = pstmt.executeUpdate()==1?true:false;
			pstmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean CheckLevel(UserInfoBean uib) {
		boolean result = false;
		String sql = "SELECT ME_LEVEL FROM MEMBER WHERE ME_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uib.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result=rs.getInt(1)!=0?true:false;
			}
			if (result==false) {
				rs.close();
				pstmt.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean UserDel(UserInfoBean uib) {
		boolean result = false;
		String sql = "UPDATE MEMBER SET ME_LEVEL=0 WHERE ME_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uib.getUserId());
			result = pstmt.executeUpdate()==1?true:false;
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	// 쿠폰번호 중복
		public boolean isSetCoupon(EventInfoBean eib) {
			boolean result = false;
			String sql= "SELECT COUNT(*) FROM COUPON WHERE CO_CODE=?";
			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, eib.getEventCode());
				
				rs= pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1)==1?false:true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return result;
			
		}

		public boolean setCouponInfo(EventInfoBean eib) {
			 boolean result = false;
		      String sql = "INSERT INTO COUPON VALUES(?,?,?,?,SYSDATE,'L')";   
		      try {
		         pstmt = con.prepareStatement(sql);
		         
		         pstmt.setString(1, eib.getEventCode());
		         pstmt.setString(2, eib.getEventcomment()); 
		         pstmt.setString(3, eib.getEventfunction());
		         pstmt.setString(4, eib.getEventImg()); 

		         result= pstmt.executeUpdate()==1?true:false;
		         
		         close(pstmt);
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
				close(pstmt);
			}
		      
		      return result;
		      
		}

		public void getApllyInfo(EventInfoBean eib) {
			String sql= "SELECT COUNT(*) FROM\r\n"
					+ "(SELECT * FROM APPLY WHERE AP_STATE = 'L')HH INNER JOIN MEMBER ON AP_MEID = ME_ID WHERE ME_LEVEL='1'"; 
			try {
				pstmt = con.prepareStatement(sql);
				
				rs= pstmt.executeQuery();
				if (rs.next()) {
					eib.setPeople(rs.getInt(1));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
		}

		public boolean getEventInfo(EventInfoBean eib) {
			boolean result = false;
			String sql= "SELECT CO_CODE,CO_COMMENT FROM COUPON WHERE CO_DATE = (SELECT MAX(CO_DATE) FROM COUPON WHERE CO_STATE = 'L')";
			try {
				pstmt = con.prepareStatement(sql);
				
				rs= pstmt.executeQuery();
				if (rs.next()) {
					eib.setEventCode(rs.getString(1));
					eib.setEventcomment(rs.getString(2));
					result = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return result;
			
		}

		public boolean getDrawlots(EventInfoBean eib, ArrayList<EventInfoBean> people) {
			boolean result = false;
			String sql= "SELECT AP_MEID,ME_NAME FROM\r\n"
					+ "(SELECT * FROM (\r\n"
					+ "SELECT * FROM APPLY WHERE AP_STATE = 'L' AND AP_COCODE = ?)HH \r\n"
					+ "INNER JOIN MEMBER ON ME_ID = AP_MEID\r\n"
					+ "WHERE ME_LEVEL = '1'\r\n"
					+ "ORDER BY DBMS_RANDOM.VALUE)\r\n"
					+ "WHERE ROWNUM <= ?";

			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setNString(1, eib.getEventCode());
				pstmt.setInt(2, eib.getPeople());
				
				EventInfoBean bbb;
				rs= pstmt.executeQuery();
				while(rs.next()) {
					bbb = new EventInfoBean();
					
					bbb.setEventMeid(rs.getString(1));
					bbb.setEventName(rs.getString(2));
					people.add(bbb);
					
					result = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return result;
		}

		public boolean comDrawlots(EventInfoBean eib) {
			 boolean result = false;
		      String sql = "UPDATE APPLY SET AP_STATE = 'F' WHERE AP_COCODE = ?";   
		      try {
		         pstmt = con.prepareStatement(sql);
		         
		         pstmt.setString(1, eib.getEventCode());

		         result= pstmt.executeUpdate()>0?true:false;
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
				close(pstmt);
			}
		      
		      return result;
			
		}

		public boolean comCoupon(EventInfoBean eib) {
			boolean result = false;
		      String sql = "UPDATE COUPON SET CO_STATE = 'F' WHERE CO_CODE = ?";   
		      try {
		         pstmt = con.prepareStatement(sql);
		         
		         pstmt.setString(1, eib.getEventCode());

		         result= pstmt.executeUpdate()==1?true:false;
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
				close(pstmt);
			}
		      
		      return result;
			
			
		}

		public boolean insertEvent(String eventMeid, String eventCode) {
			 boolean result = false;
			
		      String sql = "INSERT INTO EVENTS VALUES(?,?,SYSDATE)";   
		      try {
		         pstmt = con.prepareStatement(sql);
		         
		         pstmt.setString(1, eventCode);
		         pstmt.setString(2, eventMeid); 

		         result= pstmt.executeUpdate()==1?true:false;
		         
		         close(pstmt);
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
				close(pstmt);
			}
		      
		      return result;
		}

		public boolean GetFoodsInfo(ArrayList<FoodInfoBean> list) {
			boolean result = false;
			FoodInfoBean fib;
			String sql= "SELECT * FROM FIRSTRANK WHERE ROWNUM <= 3";
			try {
				pstmt = con.prepareStatement(sql);
				
				rs= pstmt.executeQuery();
				
				
				while (rs.next()) {
					fib = new FoodInfoBean();
					
					fib.setFoName(rs.getString("FO_NAME"));
					fib.setFoComment(rs.getString("FO_COMMENT"));
					fib.setFoFileName(rs.getString("FO_FILENAME"));
					fib.setFoState("T");
					
					list.add(fib);
					result = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return result;
			
		}

		public void getMyfoodsInfo(ArrayList<FoodInfoBean> list, String Code) {
			boolean result = false;
			FoodInfoBean fib;
			String sql= "SELECT * FROM (SELECT * FROM (SELECT * FROM HISTORY WHERE HI_MEID = ?) ORDER BY HI_RANKDATE DESC)HH INNER JOIN FOODS ON FO_CODE = HI_FOCODE WHERE ROWNUM <= 3";

			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, Code);
				
				rs= pstmt.executeQuery();
				
				while (rs.next()) {
					fib = new FoodInfoBean();
					
					fib.setFoName(rs.getString("FO_NAME"));
					fib.setFoComment(rs.getString("FO_COMMENT"));
					fib.setFoFileName(rs.getString("FO_FILENAME"));
					fib.setFoCode(rs.getString("HI_RANKDATE"));
					fib.setFoState("M");
					
					list.add(fib);
					result = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			
		}





}




















