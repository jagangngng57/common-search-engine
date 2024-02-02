package common.search.engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.search.engine.pages.BingSearchEngine;
import common.search.engine.pages.GoogleSearchEngine;
import common.search.engine.pages.YahooSearchEngine;

public class SearchTests {

    private WebDriver driver;
    private SearchEngine searchEngine;

    @BeforeClass
    @Parameters({"browser", "searchEngineUrl"})
    public void setUp(String browser, String searchEngineUrl) {
        // Initialize WebDriver based on the provided browser parameter
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\eclipse_new\\common-search-engine\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        // Initialize Search Engine based on the provided URL parameter
        if (searchEngineUrl.contains("google")) {
            searchEngine = new GoogleSearchEngine(driver);
        } else if (searchEngineUrl.contains("bing")) {
            searchEngine = new BingSearchEngine(driver);
        } else if (searchEngineUrl.contains("yahoo")) {
            searchEngine = new YahooSearchEngine(driver);
        } else {
            throw new IllegalArgumentException("Invalid search engine URL");
        }

        // Visit the specified search engine
        searchEngine.visit(searchEngineUrl);
    }

    @Test(dataProvider = "searchTermsProvider")
    public void testSearch(String searchTerm) {
        // Search for a term
        searchEngine.search(searchTerm);

        // Get the first result
        String firstResult = searchEngine.getFirstResult();

        // Assert the result
        Assert.assertTrue(firstResult.toLowerCase().contains(searchTerm.toLowerCase()));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "searchTermsProvider")
    public Object[][] searchData() {
        return new Object[][] {
                {"Selenium"},
                {"TestNG"},
                {"Java"},
                // Add more search terms as needed
        };
    }
}

