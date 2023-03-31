package com.bankapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.springframework.stereotype.Service;

import com.bankapp.model.Transaction;
import com.bankapp.utils.DBUtils;

@Service
public class MoneyTransferService {

	
	public void sendMoney(Transaction t) throws Exception {
		double moneyToTransfer=t.getAmount();
		int userFromId=t.getSenderId();
		int userToId=t.getReceiverId();
		
		String selectQuery = "SELECT * FROM customer WHERE customer_id = ?";
		String updateQuery = "UPDATE customer SET money = ? WHERE customer_id = ?";
		 

		try (Connection conn = DBUtils.getConnection();
				PreparedStatement psSelect = conn.prepareStatement(selectQuery);
				PreparedStatement psUpdate = conn.prepareStatement(updateQuery);) {

			Savepoint savepoint = null;
			try {
				conn.setAutoCommit(false);

				psSelect.setInt(1, userFromId);
				try (ResultSet rs = psSelect.executeQuery()) {
					if (rs.next() == true) {
						double moneyAmount = rs.getDouble("money");
						if (moneyToTransfer > moneyAmount) {
							System.out.println("Not enough money for transfer");
							return;
						} else {
							moneyAmount -= moneyToTransfer;
							psUpdate.setDouble(1, moneyAmount);
							psUpdate.setInt(2, userFromId);
							psUpdate.executeUpdate();
						}
					}
				}

//				savepoint = conn.setSavepoint();
				
				psSelect.setInt(1, userToId);
				try (ResultSet rs = psSelect.executeQuery()) {
					if (rs.next() == true) {
						double moneyAmount = rs.getDouble("money");
						moneyAmount += moneyToTransfer;
						psUpdate.setDouble(1, moneyAmount);
						psUpdate.setInt(2, userToId);
						psUpdate.executeUpdate();
					}
				}

				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
//				conn.rollback(savepoint);
			}
			System.out.println("Money transferred");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
