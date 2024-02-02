package common.search.engine.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.search.engine.SearchEngine;

public class BingSearchEngine implements SearchEngine {

    private WebDriver driver;

    public BingSearchEngine(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void visit(String url) {
        driver.get(url);
    }

    @Override
    public void search(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Override
    public String getFirstResult() {
        WebElement firstResult = driver.findElement(By.cssSelector("h2"));
        return firstResult.getText();
    }
}
