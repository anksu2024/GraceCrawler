# GraceCrawler
==================================================================================

A Basic Wikipedia Crawler

Problem Description

Given this link, http://en.wikipedia.org/wiki/List_of_television_programs_by_name,
write a crawler to fetch each show page and extract the following metadata
(where available) into a json file with the provided format below.
Each show should have it's data written into it's own json file, and each json
file should be named by replacing all special characters (including spaces) in
the title with underscores (_) and have a .json extension.  You may use any
language you would like to, but you must provide a README with instructions on
how to run your code.  Bonus points if the code can be run from the command
line without an IDE.

*** Please pay careful attention to rate limiting your solution.
Wikipedia does enforce such standards and will block your program if you do
not insert a reasonable (and possibly random) amount of time between fetching
each individual show page.

Metadata that should be extracted (if available):
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

Note: This Crawler requires uninterrupted Internet connection

This Application has been written using Java as Programming language

To execute in Eclipse:
1) Execute the GraceCrawler file
2) Result (.json files) can be obtained in the /json folder
3) The total Execution time in minutes is also displayed in the end