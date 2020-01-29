package com.internousdev.latte.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latte.dao.CartInfoDAO;
import com.internousdev.latte.dao.PurchaseHistoryInfoDAO;
import com.internousdev.latte.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private int id;

	public String execute() throws SQLException{
		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if(logined != 1) {
			return "loginError";
		}

		String result = ERROR;

		String userId = session.get("userId").toString();

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = cartInfoDAO.getCartInfoDTO(userId);
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int insertCount = 0;

		for (CartInfoDTO dto : cartInfoDTOList) {
			insertCount += purchaseHistoryInfoDAO.insertSettlementComplete(
				userId,
				dto.getProductId(),
				dto.getProductCount(),
				dto.getPrice(),
				id
				);
		}

		if(insertCount > 0) {
			int count = cartInfoDAO.deleteAll(String.valueOf(session.get("userId")));
			if(count > 0) {
				result = SUCCESS;
			}
		}

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
