/**
 * AUTHOR: ANKIT SARRAF
 * DATED : Mar 01, 2015
 * ABOUT : SAX Parser Configuration File for Each Show
 */

package crawler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class CustomHandler extends DefaultHandler {
	boolean bGenre = false;
	boolean bTitle = false;
	boolean bCreators = false;
	boolean bCast = false;
	boolean bCountry = false;
	boolean bSeasons = false;
	boolean bEpisodes = false;
	boolean bRun = false;
	boolean bStartDate = false;
	int spanCount = 0;

	boolean thTag = false;
	boolean tdTag = false;
	boolean aTag = false;

	private final Show show;

	CustomHandler() {
		show = new Show();
	}

	public Show getShow() {
		return show;
	}

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("th")) {
			thTag = true;
		} else if(qName.equalsIgnoreCase("td")) {
			tdTag = true;
		} else if(qName.equalsIgnoreCase("a")) {
			aTag = true;
		} else if(qName.equalsIgnoreCase("span")) {
			spanCount++;
		}
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if (qName.equalsIgnoreCase("tr")) {
			thTag = false;
			bGenre = false;
			bCreators = false;
			bCast = false;
			bCountry = false;
			bSeasons = false;
			bEpisodes = false;
		} else if(qName.equalsIgnoreCase("td")) {
			tdTag = false;
		} else if(qName.equalsIgnoreCase("a")) {
			aTag = false;
		} else if(qName.equalsIgnoreCase("span")) {
			spanCount--;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if(thTag) {
			String data = new String(ch, start, length).trim();

			if(tdTag == false) {
				if(bTitle == false) {
					// Title will be the first one
					bTitle = true;

					// Set title here
					show.setTitle(data);
				} else if(data.equalsIgnoreCase("Genre")) {
					bGenre = true;
				} else if(data.equalsIgnoreCase("Created by")) {
					bCreators = true;
				} else if(data.equalsIgnoreCase("Starring")) {
					bCast = true;
				} else if(data.equalsIgnoreCase("Country of origin")) {
					bCountry = true;
				} else if(data.equalsIgnoreCase("No. of seasons")) {
					bSeasons = true;
				} else if(data.equalsIgnoreCase("No. of episodes")) {
					bEpisodes = true;
				} else if(data.equalsIgnoreCase("Original run")) {
					bRun = true;
				}
			} else {
				if(bGenre == true) {
					if(!data.startsWith("<a href=\"/wiki") && !data.equals("")) {
						show.setGenre(data);
					}
				} else if(bCreators == true) {
					if(!data.startsWith("<a href=\"/wiki") && !data.equals("")) {
						show.getCreators().add(data);
					} 
				} else if(bCast == true) {
					if(!data.startsWith("<a href=\"/wiki") && !data.equals("")) {
						show.getCast().add(data);
					}
				} else if(bCountry == true) {
					show.setCountry_of_origin(data);
				} else if(bSeasons == true) {
					try {
						show.setSeasons(Integer.parseInt(data));
					} catch(NumberFormatException e) {
						// If for example "1 (List of Seasons)"
						StringBuffer tempEpisodes = new StringBuffer("");
						for(int i = 0 ; i < data.length() ; i++) {
							if(!(data.charAt(i) >= '0' && data.charAt(i) <= '9')) {
								break;
							}
							tempEpisodes.append(data.charAt(i));
						}

						if(!tempEpisodes.toString().equals("")) {
							show.setEpisodes(Integer.parseInt(tempEpisodes.toString()));
						}
					}
				} else if(bEpisodes == true) {
					try {
						show.setEpisodes(Integer.parseInt(data));
					} catch(NumberFormatException e) {
						// If for example "27 (List of episodes)"
						StringBuffer tempEpisodes = new StringBuffer("");
						for(int i = 0 ; i < data.length() ; i++) {
							if(!(data.charAt(i) >= '0' && data.charAt(i) <= '9')) {
								break;
							}
							tempEpisodes.append(data.charAt(i));
						}

						if(!tempEpisodes.toString().equals("")) {
							show.setEpisodes(Integer.parseInt(tempEpisodes.toString()));
						}
					}
				} else if(bRun == true && spanCount == 2) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					if(bStartDate == false) {
						try {
							show.setStart_date(dateFormat.parse(data));
						} catch (ParseException e) {}
						bStartDate = true;
					} else {
						try {
							show.setEnd_date(dateFormat.parse(data));
						} catch (ParseException e) {}
					}
				}
			}
		}
	}
}