import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NBA {

    private static final Logger logger = Logger.getLogger(NBA.class.getName());
    private WebDriver driver;
    private static final String PLAYERS_API = "https://api.sportsdata.io/v3/nba/scores/json/Players/DAL?key=58957574730c4ee1b809da2f53525997";
    private static final ArrayList<String> links = new ArrayList<>();
    private static int index = 0;

    public NBA() {
        try {
            links.addAll(getPlayersLinks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> getPlayersLinks() throws IOException {
        ArrayList<String> playerLinks = new ArrayList<>();
        URL url = new URL(PLAYERS_API);
        try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            JSONArray json = new JSONArray(reader.readLine());
            for (Object o : json) {
                JSONObject obj = (JSONObject) o;
                playerLinks.add("https://www.nba.com/player/" + obj.getLong("NbaDotComPlayerID"));
            }
        }
        return playerLinks;
    }

    @BeforeMethod
    private void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(links.get(index++));
    }

    @Test(invocationCount = 17)
    public void testPlayerAverage() {
        String base = "//table[1]/tbody/tr";
        double sum = 0.0, avg = 0.0;
        String playerName = driver.findElement(By.xpath("//*[@class='PlayerSummary_mainInnerBio__JQkoj']"))
                .getText();

        for (int i = 1; i <= 5; i++) {
            try {
                sum += Double.parseDouble(driver.findElement(By.xpath(base + "[" + i + "]/td[9]/a")).getText());
            } catch (NoSuchElementException e) {
                sum += 0;
            }
        }

        avg = sum / 5;
        logger.log(Level.INFO, "Avg: "  + avg);
        logger.log(Level.INFO, "Player Name: " + playerName);
        Assert.assertTrue(avg >= 1, "Average (" + avg + ") should be greater than or equal to 1");
    }

    @AfterMethod()
    public void killDriver(){
        driver.close();
    }
}
