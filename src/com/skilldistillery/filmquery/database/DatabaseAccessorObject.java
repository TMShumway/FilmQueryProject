
package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
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
//		System.out.println("Find film by ID started...");

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

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
			film.setActorsInFilm(findActorsByFilmId(filmId));
			film.setFilmLanguage(findFilmLanguageById(filmId));
		}

		rs.close();
		stmt.close();
		conn.close();

		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		
		Actor actor = null;
//		System.out.println("Find actor by ID started...");

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();

		System.out.println("Executing SQL Query -> " + sql);
		
		while (rs.next()) {
			actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		
		rs.close();
		stmt.close();
		conn.close();

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		
		List<Actor> actorsByFilmId = new ArrayList<>();
//		System.out.println("Find film by ID started...");

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT actor.id, actor.first_name, actor.last_name " +
				     "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + 
				     "WHERE film_actor.film_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		System.out.println("Executing SQL Query -> " + sql + "\n");
		while (rs.next()) {
			Actor actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
			actorsByFilmId.add(actor);
		}
		
		rs.close();
		stmt.close();
		conn.close();

		return actorsByFilmId;
		
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> filmsByKeyword = new ArrayList<>();
		
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * " +
				     "FROM film " + 
				     "WHERE film.title LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		ResultSet rs = stmt.executeQuery();

		System.out.println("Executing SQL Query -> " + sql + "\n");
		while (rs.next()) {
			filmsByKeyword.add(findFilmById(rs.getInt(1)));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return filmsByKeyword;
	}

	@Override
	public String findFilmLanguageById(int filmId) throws SQLException {
		String filmLanguage = "";
		
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT language.name " +
				     "FROM language JOIN film " + 
				     "ON film.language_id = language.id " + 
				     "WHERE film.id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		System.out.println("Executing SQL Query -> " + sql + "\n");
		while (rs.next()) {
			filmLanguage = rs.getString(1);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return filmLanguage;
	}

}
