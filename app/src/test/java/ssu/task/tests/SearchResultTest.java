package ssu.task.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.pages.HomePage;
import ssu.task.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class SearchResultTest extends BaseTest {

    /* Тест 4
     * Ввести в поисковую строку «Жираф», проверить, что название первой картины содержит слово «Жираф».
     * */
    @Test(testName = "Поиск по ключевому слову 'Жираф'")
    public void checkTitleOfFirstPaintingInSearchResultsForGiraffe() {
        log.info("Starting test: Searching for 'Жираф' in the search bar and verifying that " +
                "the title of the first painting contains the word 'Жираф'.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        String request = "Жираф";

        home.enteringTextInSearchField(request);
        boolean isContains = picture.checkContainsRequestInName(request);

        assertTrue(isContains);
    }
}
