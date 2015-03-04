/**
 * AUTHOR: ANKIT SARRAF
 * DATED : Feb 28, 2015
 * ABOUT : TV Serial Links Extractor
 */

package crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WikiCrawler {
	private final String baseURL;
	private URL url;
	private List<URL> tvSerials;
	private String mainFile;

	public WikiCrawler(String inputURL) throws IOException {
		baseURL = "http://en.wikipedia.org";
		url = new URL(inputURL);
		tvSerials = new ArrayList<URL>();
		mainFile = "input/main.xml";
	}

	void crawl() throws IOException {
		crawlMainPage();
		getChildrenURL();
		getChildrenData();
	}

	void crawlMainPage() throws IOException {
		Scanner scanner = new Scanner(url.openStream());
		BufferedWriter writer = new BufferedWriter(new FileWriter(mainFile));

		while(scanner.hasNext()) {
			writer.write(scanner.next() + " ");
		}

		writer.close();
		scanner.close();
	}

	void getChildrenURL() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(mainFile));

		String currentShow = "<li><i><a href=\"";
		String line = reader.readLine();

		if(line != null) {
			while(true) {
				int startIndex = line.indexOf(currentShow);
				int endIndex = line.indexOf('"', startIndex + currentShow.length());

				if(startIndex == -1 || endIndex == -1) {
					break;
				}

				String currentURL = line.substring(startIndex + currentShow.length(), endIndex);
				tvSerials.add(new URL(baseURL + currentURL));

				line = line.substring(endIndex);
			}
		}

		reader.close();
	}

	void getChildrenData() {
		int count = 0;

		for(URL url: tvSerials) {
			// Parse each url
			new ChildParser(url);
			count++;
			if(count == 100) {
				try {
					Thread.sleep(500);
					count = 0;
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}