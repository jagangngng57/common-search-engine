package common.search.engine.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.search.engine.SearchEngine;

public class YahooSearchEngine implements SearchEngine {

    private WebDriver driver;

    public YahooSearchEngine(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void visit(String url) {
        driver.get(url);
    }

    @Override
    public void search(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("p"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Override
    public String getFirstResult() {
        WebElement firstResult = driver.findElement(By.cssSelector("h3"));
        return firstResult.getText();
    }
}
