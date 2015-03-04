/**
 * AUTHOR: ANKIT SARRAF
 * DATED : Feb 28, 2015
 * ABOUT : Current Child Link (Show) Parser
 */

package crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ChildParser implements Runnable {
	private URL url;
	private Thread thread;
	private Show show;
	private String outputDir;

	ChildParser(URL url) {
		outputDir = "json/";
		this.url = url;
		run();
		this.thread = new Thread(this, "ChildParser");
		this.thread.start();
	}

	@Override
	public void run() {
		Scanner scanner;
		StringBuffer line;

		try {
			line = new StringBuffer();
			scanner = new Scanner(url.openStream());

			while(scanner.hasNext()) {
				line.append(scanner.next().trim() + " ");
			}

			createJSON(line.toString());
			scanner.close();
		} catch (IOException e) {

		} catch (ParserConfigurationException e) {

		} catch (SAXException e) {

		}
	}

	void createJSON(String fileData)
			throws IOException, ParserConfigurationException, SAXException {
		String title = getTitle(fileData);
		String targetText = getTargetText(title, fileData);

		if(targetText == null) {
			return;
		}

		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(outputDir + title + ".json"));
		writer.write(targetText);
		writer.close();

		show = serializedText(outputDir + title + ".json");

		writer = new BufferedWriter(new FileWriter(outputDir + title + ".json"));
		writer.write(show.toString());
		writer.close();
	}

	private String getTitle(String fileData) {
		int titleStartIndex = fileData.indexOf("<title>") + "<title>".length();
		int titleEndIndex = fileData.indexOf("- Wikipedia, the free encyclopedia</title>");
		String title = fileData.substring(titleStartIndex, titleEndIndex).trim();
		return title.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
	}

	private String getTargetText(String title, String fileData) {
		String startTag = "<table class=\"infobox vevent\" style=\"width:22em\">";
		String endTag = "</table>";

		int targetStartIndex = fileData.indexOf(startTag) + startTag.length();
		int targetEndIndex = fileData.indexOf(endTag, targetStartIndex) - 1;

		if(targetStartIndex > targetEndIndex || targetEndIndex < 0) {
			// If there isn't any Infobox
			System.out.println("MetaDeta Unavilable Available: " + title);
			return null;
		}

		System.out.println("Recording Metadata: " + title);
		return "<table>" + fileData.substring(targetStartIndex, targetEndIndex).trim() + "</table>";
	}

	private Show serializedText(String targetXMLFile) throws ParserConfigurationException, SAXException, IOException {
		CustomHandler handler = new CustomHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		try {
			saxParser.parse(targetXMLFile, handler);
		} catch(SAXParseException e) {}

		return handler.getShow();
	}
}