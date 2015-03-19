# GraceCrawler

Problem Description:

Given this link, http://en.wikipedia.org/wiki/List_of_television_programs_by_name,
this crawler fetches each show page and extracts the metadata (where available) 
into a json file with the provided format below. Each show should have it's data
written into it's own json file, and each json file should be named by replacing
all special characters (including spaces) in the title with underscores (_) and
have a .json extension.

Tools and technologies: Eclipse IDE, Core Java

Note:
Wikipedia enforces rate limiting standards and blocks the program if a reasonable 
amount of time isn't inserted between fetching each individual show page.

Metadata extracted (if available) in the following format:
 - Show title
 - Genre
 - Creators
 - Starring cast
 - Country of origin
 - Number of seasons
 - Number of episodes
 - Start of the show
 - End of the show

JSON Format:
{

    title: "",
    genre: "",
    creators: ["name 1", "name 2", ...],
    cast: ["name 1", "name 2", ...],
    country_of_origin: "",
    seasons: 123,
    episodes: 123,
    start_date: (ISO-8601 Date String),
    end_date: (ISO-8601 Date String)
}

==================================================================================

Implementation Notes:
This Crawler requires uninterrupted Internet connection
This Application has been written using Java as Programming language

==================================================================================

Execution/ Configuration:

To execute in Eclipse:

1) Execute the GraceCrawler file

2) Result (.json files) can be obtained in the /json folder

3) The total Execution time in minutes is also displayed in the end
