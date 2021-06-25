package com.skilldistillery.filmquery.entities;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Film {
	
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private String rentalDuration;
	private double rentalRate;
	private String length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
//	private List<Actor> actorsInFilm;

	public Film() {}
	public Film(int id, String title, String description, Integer releaseYear, int languageId, String rentalDuration,
			double rentalRate, String length, double replacementCost, String rating, String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
//		this.actorsInFilm = actorsInFilm;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public String getRentalDuration() {
		return rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public String getLength() {
		return length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public String getRating() {
		return rating;
	}
	
	public String getSpecialFeatures() {
		return specialFeatures;
	}

//	public List<Actor> getActorsInFilm() {
//		return actorsInFilm;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public void setRentalDuration(String rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

//	public void setActorsInFilm(List<Actor> actorsInFilm) {
//		this.actorsInFilm = actorsInFilm;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + languageId;
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + ((rentalDuration == null) ? 0 : rentalDuration.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (languageId != other.languageId)
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (rentalDuration == null) {
			if (other.rentalDuration != null)
				return false;
		} else if (!rentalDuration.equals(other.rentalDuration))
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", releaseYear=");
		builder.append(releaseYear);
		builder.append(", languageId=");
		builder.append(languageId);
		builder.append(", rentalDuration=");
		builder.append(rentalDuration);
		builder.append(", rentalRate=");
		builder.append(rentalRate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", replacementCost=");
		builder.append(replacementCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", specialFeatures=");
		builder.append(specialFeatures);
		builder.append("]");
		return builder.toString();
	}


	
	
}
