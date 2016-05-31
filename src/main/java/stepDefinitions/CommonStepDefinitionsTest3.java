package stepDefinitions;

import cucumber.api.java.en.Given;
import lib.Init;
import pages.BranchesAndATMsPage;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 23.05.2016.
 */
public class CommonStepDefinitionsTest3 {
    BranchesAndATMsPage branchesAndATMsPage;
    public CommonStepDefinitionsTest3() throws IOException {

    }

    //@Before
    public static void before() throws IOException {
        System.out.println("test3");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("urlTest3", property.get("db.urlTest3"));

    }

    /**
     * Открыть страницу "Отделения и банкоматы"
     * @throws IOException
     */
    @Given("^Открыть страницу 'Отделения и банкоматы'$")
    public  void openBranchesAndATMs() throws IOException {
        Allure.LIFECYCLE.fire(new AddParameterEvent("Переход на страницу", "http://www.sberbank.ru/ru/about/today/oib"));
        getDriver().get(Init.getStash().get("urlTest3").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        branchesAndATMsPage = new BranchesAndATMsPage();
    }

    /**
     * Выделить чекбокс "Отделения"
     * @throws IOException
     */
    @Given("^Выделить чекбокс 'Отделения'$")
    public void selectFilial() throws IOException, InterruptedException {
        branchesAndATMsPage.selectFilial();

    }

    /**
     * Проверяет количество ближайших локаций
     */
    @Given("^Проверяет количество ближайших локаций$")
    public void countFilial() throws IOException {
        branchesAndATMsPage.countFilial();
    }

    /**
     * Проверяет порядок найденных ближайших локаций, при выделение чекбокса "Отделения"
     */
    @Given("^Проверяет порядок найденных ближайших локаций, при выделение чекбокса 'Отделения'$")
    public void distanceToFilial() {
        branchesAndATMsPage.distanceToFilial();
    }

    /**
     * Отметить чекбокс "Платёжные устройства"
     * @throws IOException
     * @throws InterruptedException
     */
    @Given("^Отметить чекбокс 'Платёжные устройства'$")
    public void selectTerminal() throws IOException, InterruptedException {
        branchesAndATMsPage.selectTerminal();
    }

    /**
     * Проверяет порядок расположения найденных ближайших локаций,
     * при выбранных значения "Отделения", "Платёжные устройства"
     */
    @Given("^Проверяет порядок расположения найденных ближайших локаций при выбранных значения 'Отделения','Платёжные устройства'$")
    public void distanceToTerminal() {
        branchesAndATMsPage.distanceToTerminal();
    }

    /**
     * Осуществление нажатия на кнопку "Показать ещё", и проверка располодения найденных ближайших локаций
     * @throws IOException
     * @throws InterruptedException
     */
    @Given("^Осуществление нажатия на кнопку 'Показать ещё', и проверка расположения найденных ближайших локаций$")
    public void selectShowMore() throws IOException, InterruptedException {
        branchesAndATMsPage.selectShowMore();
    }

    /**
     * Снять выделения с чекбокса "Отделения"
     * @throws IOException
     * @throws InterruptedException
     */
    @Given("^Снять выделения с чекбокса 'Отделения'$")
    public void deselectFilial() throws IOException, InterruptedException {
        branchesAndATMsPage.deselectFilial();
    }

    /**
     * Проверяет порядок расположения найденных ближайших локаций
     */
    @Given("^Проверяет порядок расположения найденных ближайших локаций$")
    public void distanceLocationDeselectFilial(){
        branchesAndATMsPage.distanceLocationDeselectFilial();
    }


   // @After
    public void after () throws IOException{
        getDriver().quit();
        Init.clearStash();
    }


}
