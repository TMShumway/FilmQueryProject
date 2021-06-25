package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		
		try {
//			app.test();
			app.launch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner kb = new Scanner(System.in);

		startUserInterface(kb);

		kb.close();
	}

	private void startUserInterface(Scanner kb) throws SQLException {
		displayMenu();
		
		int userInt = kb.nextInt();
		
		userMenuSelection(userInt, kb);
	}

	private void userMenuSelection(int userInt, Scanner kb) throws SQLException {
		
		switch(userInt) {
			case 1:
				menuSelectionOne(kb);
				break;
			case 2:
				menuSelectionTwo(kb);
				break;
			default:
				System.out.println("Invalid Selction. Please try again.");
		}
		
	}

	private void menuSelectionTwo(Scanner kb) throws SQLException {
		System.out.print("Please enter the search keyword: ");
		String searchKeyword = kb.next();
		
		List<Film> filmsByKeyword = db.findFilmsByKeyword(searchKeyword);
		
		if(filmsByKeyword.size() == 0) {
			System.out.println("No films containing the keyword: " + 
		                       searchKeyword + " found.");
		} else {
			System.out.println("Search keyword matched to film(s).");
			for (Film film : filmsByKeyword) {
				System.out.println("\nFilm ID " + film.getId() + ": " + film.getTitle() +
				           " " + film.getReleaseYear() + " " + film.getRating() +
				           " " + film.getDescription() + " " + film.getFilmLanguage() +
				           " ");
				(film.getActorsInFilm()).forEach((a) -> System.out.println(a.getFirstName() + 
						                                " " + a.getLastName() + " "));
			}
		}
	}

	private void menuSelectionOne(Scanner kb) throws SQLException {
		System.out.print("Please enter the Film ID: ");
		int filmID = kb.nextInt();
		Film film = db.findFilmById(filmID);
		
		if(film == null) {
			System.out.println("No film with supplied Film ID: " + filmID + " found.");
		} else {
			System.out.println("Film ID located.");
			System.out.println("\nFilm ID " + filmID + ": " + film.getTitle() +
					           " " + film.getReleaseYear() + " " + film.getRating() +
					           " " + film.getDescription() + " " + film.getFilmLanguage() +
					           " ");
			(film.getActorsInFilm()).forEach((a) -> System.out.println(a.getFirstName() + 
                    " " + a.getLastName() + " "));
			
		}
	}

	private void displayMenu() {
		System.out.println("------------------------------ Welcome! ------------------------------");
		System.out.println("\n\t1) Look up Film by Film ID");
		System.out.println("\t2) Look up Film by a search keyword");
		System.out.print("\nEnter your choice as a number only: ");
	}

}
