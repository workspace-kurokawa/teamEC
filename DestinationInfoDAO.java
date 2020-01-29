package com.internousdev.latte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.latte.dto.DestinationInfoDTO;
import com.internousdev.latte.util.DBConnector;

public class DestinationInfoDAO {

//	宛先情報テーブルに宛先情報を登録するメソッド

	public int createDestination
		(String userId, String familyName, String firstName, String familyNameKana, String firstNameKana,
		String email, String telNumber, String userAddress)
		throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int count = 0;

		String sql = "INSERT INTO destination_info (user_id,family_name, first_name, family_name_kana, first_name_kana, "
				+ "email, tel_number, user_address, regist_date, update_date) "
				+ "VALUE(?,?,?,?,?,?,?,?,now(),now())";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, familyName);
			ps.setString(3, firstName);
			ps.setString(4, familyNameKana);
			ps.setString(5, firstNameKana);
			ps.setString(6, email);
			ps.setString(7, telNumber);
			ps.setString(8, userAddress);

			count = ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		return count;
	}

//	決済確認画面で表示する宛先情報を取得するメソッド

	public List<DestinationInfoDTO> getDestinationInfo(String userId) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<DestinationInfoDTO> destinationInfoDTOList = new ArrayList<DestinationInfoDTO>();

		String sql = "SELECT * FROM destination_info WHERE user_id = ? ORDER BY regist_date ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				DestinationInfoDTO destinationInfoDTO = new DestinationInfoDTO();
				destinationInfoDTO.setId(rs.getInt("id"));
				destinationInfoDTO.setFamilyName(rs.getString("family_name"));
				destinationInfoDTO.setFirstName(rs.getString("first_name"));
				destinationInfoDTO.setFamilyNameKana(rs.getString("family_name_kana"));
				destinationInfoDTO.setFirstNameKana(rs.getString("first_name_kana"));
				destinationInfoDTO.setEmail(rs.getString("email"));
				destinationInfoDTO.setTelNumber(rs.getString("tel_number"));
				destinationInfoDTO.setUserAddress(rs.getString("user_address"));
				destinationInfoDTOList.add(destinationInfoDTO);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		return destinationInfoDTOList;
	}

//  決済確認画面で情報を削除するメソッド

	public int deleteDestination(String id) throws SQLException{

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "DELETE FROM destination_info WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		return result;
	}

}
