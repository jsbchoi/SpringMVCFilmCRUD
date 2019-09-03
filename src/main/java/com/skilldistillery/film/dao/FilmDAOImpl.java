package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDAOImpl implements FilmDAO {
	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	public static List<String> specialFeatures = new ArrayList<>(
			Arrays.asList("Trailers", "Commentaries", "Deleted Scenes", "Behind the Scenes"));
	public static List<String> ratings = new ArrayList<>(
			Arrays.asList("G", "PG", "PG13", "R", "NC17"));
	private final String user = "student";
	private final String pass = "student";

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement st = null;

	public FilmDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film getFilmByID(int filmID) {
		try {
			conn = DriverManager.getConnection(url, user, pass);

			String sql = "select * from film where id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, filmID);
			rs = st.executeQuery();
			if (rs.next()) {
				Film film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getDouble("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
				return film;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	@Override
	public List<Film> getFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM film where lower(film.title) like ? or lower(film.description) like ?";

			st = conn.prepareStatement(sql);
			st.setString(1, "%" + keyword + "%");
			st.setString(2, "%" + keyword + "%");

			rs = st.executeQuery();
			while (rs.next()) {
				Film film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getDouble("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
				films.add(film);
			}

			return films;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	@Override
	public List<Actor> getActors(int filmID) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT * FROM actor join film_actor on actor.id = film_actor.actor_id where film_id = ?;";

		try {
			conn = DriverManager.getConnection(url, user, pass);
			st = conn.prepareStatement(sql);
			st.setInt(1, filmID);
			rs = st.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return actors;
	}

	@Override
	public boolean addFilm(Film film) {
		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setInt(4, film.getLanguage_id());
			st.setDouble(5, film.getRentalDuration());
			st.setDouble(6, film.getRentalRate());
			st.setDouble(7, film.getLength());
			st.setDouble(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecial_features());
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				film.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				st.close();
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteFilm(int filmID) {
		String sql = "delete from film where id = ?";
		int result = 0;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);

			st = conn.prepareStatement(sql);
			st.setInt(1, filmID);
			result = st.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				rs.close();
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result == 0 ? false : true;
	}

	@Override
	public boolean updateFilm(Film film) {
		String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? WHERE id = ?";

		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);

			st = conn.prepareStatement(sql);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setDouble(4, film.getRentalDuration());
			st.setDouble(5, film.getRentalRate());
			st.setDouble(6, film.getLength());
			st.setDouble(7, film.getReplacementCost());
			st.setString(8, film.getRating());
			st.setString(9, film.getSpecial_features());
			st.setInt(10, film.getId());
			st.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				rs.close();
				conn.commit();
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public String getLanguageOfFilm(int filmId) {
		if (filmId <= 0) {
			return null;
		}
		String sql = "SELECT language.name "
						+ "FROM film JOIN language ON film.language_id = language.id "
						+ "WHERE film.id = ?";
		Connection conn = null;
		String language = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet fr = stmt.executeQuery();
			if (fr.next()) {
				language =  fr.getString("language.name");
			}
			fr.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public String getCategoryOfFilm(int filmId) {
		if (filmId <= 0) {
			return null;
		}
		String sql = "SELECT category.name "
				+ "FROM film JOIN film_category ON film.id = film_category.film_id "
						  + "JOIN category ON category.id = film_category.category_id "
				+ "WHERE film.id = ?";
		Connection conn = null;
		String category = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet fr = stmt.executeQuery();
			if (fr.next()) {
				category =  fr.getString("category.name");
			}
			fr.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	} 

}
