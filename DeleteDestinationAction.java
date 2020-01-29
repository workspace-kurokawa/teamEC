package com.internousdev.latte.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latte.dao.DestinationInfoDAO;
import com.internousdev.latte.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware{

	private String id;
	private Map<String,Object> session;
	private List<DestinationInfoDTO> destinationInfoDTOList;

	public String execute() throws SQLException{

		String _logined = String.valueOf(session.get("logined"));
		int logined = "null".equals(_logined)? 0 : Integer.parseInt(_logined);
		if(logined != 1) {
			return "loginError";
		}
		String result = ERROR;

		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		int count = destinationInfoDAO.deleteDestination(id);

//		宛先情報テーブルの情報を削除できたかの分岐
		if(count > 0) {
			destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(session.get("userId").toString());
			result = SUCCESS;
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

}
