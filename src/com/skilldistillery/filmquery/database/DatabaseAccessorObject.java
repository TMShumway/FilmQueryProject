
package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		System.out.println("Find film by ID started...");

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM film WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 1);
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println("Executing SQL Query -> " + sql);
		
		while (rs.next()) {
			film = new Film();
			
			film.setId(rs.getInt(1));
			film.setTitle(rs.getString(2));
			film.setDescription(rs.getString(3));
			film.setReleaseYear(rs.getInt(4));
			film.setLanguageId(rs.getInt(5));
			film.setRentalDuration(rs.getString(6));
			film.setRentalRate(rs.getDouble(7));
			film.setLength(rs.getString(8));
			film.setReplacementCost(rs.getDouble(9));
			film.setRating(rs.getString(10));
			film.setSpecialFeatures(rs.getString(11));
		}

		rs.close();
		stmt.close();
		conn.close();

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Actor> findActorsByFilmId(int filmId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
