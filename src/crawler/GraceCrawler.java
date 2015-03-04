/**
 * AUTHOR: ANKIT SARRAF
 * DATED : Feb 27, 2015
 * ABOUT : Main File for the Crawler
 */

package crawler;

import java.io.IOException;

public class GraceCrawler {
	public static void main(String ... args) throws IOException {
		double startTime = System.currentTimeMillis();

		WikiCrawler  wc = 
				new WikiCrawler("http://en.wikipedia.org/wiki/"
						+ "List_of_television_programs_by_name");

		wc.crawl();
		double endTime = System.currentTimeMillis();

		System.out.println("Total Execution Time ~ " +
				((endTime - startTime) / 60000) + " minutes");
	}
}