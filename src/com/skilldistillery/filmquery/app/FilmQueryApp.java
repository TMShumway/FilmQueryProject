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
		boolean keepGoing = true;
		
		do {
			displayMenu();
			int userInt = validateIntInput(kb, 3);
			if(userInt == 3) {
				displayGoodbye();
				break;
			}
			
			userMenuSelection(userInt, kb);
		} while (keepGoing);
	}

	private void displayGoodbye() {
		System.out.println("------------------------------ Farewell! ------------------------------");
		
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
				System.out.println("Unaccounted selection. Try again.");
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
			System.out.println("=======================================================================");
			filmsByKeyword.forEach(film -> printFilm(film, film.getId()));
		}
	}

	private void menuSelectionOne(Scanner kb) throws SQLException {
		System.out.print("Please enter the Film ID: ");
		int filmID = kb.nextInt();
		Film film = db.findFilmById(filmID);
		
		if(film == null) {
			System.out.println("No film with supplied Film ID: " + filmID + " found.");
		} else {
			System.out.println("Film ID located. Displaying results:");
			System.out.println("=======================================================================");
			printFilm(film, filmID);
		}
	}

	private void printFilm(Film film, int filmID) {
			System.out.println("\nFilm ID: " + filmID);
			System.out.println("Film Title: " + film.getTitle());
			System.out.println("Film Release Year: " + film.getReleaseYear());
			System.out.println("Film Rating: " + film.getRating());
			System.out.println("Film: Description: " + film.getDescription()); 
			System.out.println("Film Language: " + film.getFilmLanguage());
			System.out.print("Actors in Film:");
			(film.getActorsInFilm()).forEach((a) -> System.out.print(" " + a.getFirstName() + 
             " " + a.getLastName() + " - "));
			System.out.println("\n-----------------------------------------------------------------------");
	}
	
	private void displayMenu() {
		System.out.println("--------------------------------- MENU --------------------------------");
		System.out.println("\n\t1) Look up Film by Film ID");
		System.out.println("\t2) Look up Film by a search keyword");
		System.out.println("\t3) Quit Program");
		System.out.print("\nEnter your choice as a number only: ");
	}

	private int validateIntInput(Scanner kb, int menuUpperLimit) {
		boolean isInt = false;
		int number = 0;

		do {
						
			if(kb.hasNextInt()) {
				number = kb.nextInt();
				if (number < 1 || number > menuUpperLimit){
					System.out.println("Input out of range. 1 - " + menuUpperLimit + " only.");
					kb.next();
					System.out.print("Enter your choice as a number only: ");
					continue;
				}
				isInt = true;
				System.out.println();
			} else {
				System.out.println("Invalid input. Numerical input only.");
				kb.next();
				System.out.print("Enter your choice as a number only: ");
			}
		} while (!isInt);

		return number;
	}
	
}
