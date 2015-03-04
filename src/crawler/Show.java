/**
 * AUTHOR: ANKIT SARRAF
 * DATED : Feb 27, 2015
 * ABOUT : Show Meta data
 */

package crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Show {
	private String title;
	private String genre;
	private List<String> creators;
	private List<String> cast;
	private String country_of_origin;
	private int seasons;
	private int episodes;
	private Date start_date;
	private Date end_date;

	public Show() {
		creators = new ArrayList<String>();
		cast = new ArrayList<String>();
	}

	public String toString() {
		StringBuffer returnString = new StringBuffer();

		if(title != null && !title.equals("")) {
			returnString.append("{\n\t\"title\":\"" + title + "\",");
		}

		returnString.append("\n\t\"genre\":\"");
		if(genre != null && !genre.equals("")) {
			returnString.append(genre);
		}
		returnString.append("\",");

		returnString.append("\n\t\"creators\":[");
		for(String creator : creators) {
			returnString.append("\"" + creator + "\",");
		}

		returnString.deleteCharAt(returnString.length() - 1);
		returnString.append("],");

		returnString.append("\n\t\"cast\":[");
		for(String c : cast) {
			returnString.append("\"" + c + "\",");
		}

		returnString.deleteCharAt(returnString.length() - 1);
		returnString.append("],");

		if(country_of_origin != null && !country_of_origin.equals("")) {
			returnString.append("\n\t\"country_of_origin\":\"" + country_of_origin + "\",");
		}

		returnString.append("\n\t\"seasons\":" + seasons + ",");
		returnString.append("\n\t\"episodes\":" + episodes + ",");

		returnString.append("\n\t\"start_date\":\"");
		if(start_date != null) {
			returnString.append(start_date.toString());
		}
		returnString.append("\",");

		returnString.append("\n\t\"end_date\":\"");
		if(end_date != null) {
			returnString.append(end_date.toString());
		}
		returnString.append("\"");

		returnString.append("\n}");

		return returnString.toString();
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<String> getCreators() {
		return creators;
	}
	public void setCreators(List<String> creators) {
		this.creators = creators;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public String getCountry_of_origin() {
		return country_of_origin;
	}
	public void setCountry_of_origin(String country_of_origin) {
		this.country_of_origin = country_of_origin;
	}
	public int getSeasons() {
		return seasons;
	}
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}