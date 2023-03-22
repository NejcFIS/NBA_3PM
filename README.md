# NBA Player Averages Scraper

This project is a simple Java web scraper that retrieves NBA player data and calculates their average points in the last 5 games. It uses the sportsdata.io API to get the player information and then uses Selenium WebDriver to scrape the data from www.nba.com. The project also includes TestNG for performing tests on the scraped data.

## Features

- Retrieves player information for a specific team (Dallas Mavericks) from the sportsdata.io API.
- Scrapes the last 5 games' points for each player from www.nba.com.
- Calculates the average points per player for the last 5 games.
- Uses TestNG to perform tests on the calculated averages.

## Prerequisites

To run this project, you will need:

- JDK 11 or higher.
- Maven for dependency management.
- Chrome browser installed.

## Dependencies

- Selenium WebDriver: for web scraping.
- TestNG: for testing purposes.
- json.org JSON: for handling JSON data.
- WebDriverManager: for managing the ChromeDriver.

## Installation

. Open your favorite Java IDE and import the project.
1. Clone the repository:

git clone https://github.com/NejcFIS/NBA_3PM

2. Navigate to the project directory:

cd NBAPlayerAveragesScraper

3. Install the required dependencies with Maven:

mvn clean install

## Usage

1. Open your favorite Java IDE and import the project.
2. Configure the TestNG run configuration for the project.
3. Run the `testPlayerAverage()` method in the `NBA` class using TestNG.

After running the test, you should see the average points for the last 5 games for each player and the player's name logged in the console.


## License

[MIT](https://choosealicense.com/licenses/mit/)
