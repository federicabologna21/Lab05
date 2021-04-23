package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {

	// DEFINISCO LA CLASSE DAO 
	// E CREO UN METODO CHE CONTROLLA CHE LA PAROLA 
	// SIA CORRETTA O ERRATA
	// LA RICORSIONE LA IMPLEMENTO IN MODEL
	// MA TUTTI GLI ALTRI CONTROLLI VANNO NELLA CLASSE DAO
	
	public boolean isCorrect(String anagramma) {
		
		boolean trovato = false;
		
		String sql = "SELECT nome "
				+ "FROM parola "
				+ "WHERE nome=?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();
			
			// se trova una riga valida
			// cioè se la parola inserita è presente del db
			if(rs.first()) {
				trovato = true;
			}
			
			st.close();
			rs.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return trovato;
	}
}

