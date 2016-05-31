package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import lib.Init;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.ChooseTravelTourPage;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 26.05.2016.
 */
public class ChooseTravelTourStep {

    ChooseTravelTourPage chooseTravelTourPage;

    @Before
    public  void before() throws IOException, InvalidFormatException {
        System.out.println("final test");
        Properties property = new Properties();
        property.load(new InputStreamReader(new FileInputStream("src/test/java/config/application.properties"), "UTF-8"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("url", property.get("db.finalurl"));

    }

    @Given("^Открыть страницу 'Поиск и бранирование туров'$")
    public  void openSearchTour() throws IOException {
        Allure.LIFECYCLE.fire(new AddParameterEvent("Переход на страницу", "http://sletat.ru"));
        getDriver().get(Init.getStash().get("url").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chooseTravelTourPage = new ChooseTravelTourPage();
    }

    @Given("^Установить критерии$")
    public void establishCriterias() throws IOException, InterruptedException, InvalidFormatException {
        chooseTravelTourPage.establishCriterias();
    }
    @Given("^Поиск  и вывод результата$")
    public void find() throws IOException, InterruptedException {
        chooseTravelTourPage.find();
    }

    @After
    public void after() throws IOException {
        getDriver().quit();
        Init.clearStash();
    }
}
