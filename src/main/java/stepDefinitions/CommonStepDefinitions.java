package stepDefinitions;

import cucumber.api.java.en.Given;
import lib.Init;
import pages.InsuranceTravelPage;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 18.05.2016.
 */
public class CommonStepDefinitions {
    InsuranceTravelPage insuranceTravelPage;
    public  CommonStepDefinitions() throws IOException {

    }

    //@Before
    public  void before() throws IOException {
        System.out.println("test1");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("url", property.get("db.url"));
        setStash("TotalCost", property.get("db.TotalCost"));
        setStash("TotalCostSufficient", property.get("db.TotalCostSufficient"));
        setStash("TotalCostSport", property.get("db.TotalCostSport"));
        setStash("SportSumma", property.get("db.SportSumma"));
        setStash("TotalCostProvident", property.get("db.TotalCostProvident"));
        setStash("TotalCostFinish", property.get("db.TotalCostFinish"));

    }

    @Given("^Открыть страницу страхование путешественников$")
    public  void openInsuranceTravel() throws IOException {
        Allure.LIFECYCLE.fire(new AddParameterEvent("Переход на страницу",
                "https://online.sberbankins.ru/store/vzr/index.html#/viewCalc"));
        getDriver().get(Init.getStash().get("url").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        insuranceTravelPage = new InsuranceTravelPage();
    }

    @Given("^Проверить значения по умолчанию$")
    public void checkDefaultValues() throws IOException {
        insuranceTravelPage.checkDefaultValues();
    }

    @Given("^Проверка доступности вкладок 'Оформление', 'Подтверждение'$")
    public void checkAvailabilityTabs() throws IOException {
        insuranceTravelPage.checkAvailabilityTabs();
    }

    @Given("^Проверка значения Итоговая стоимость$")
    public void checkTotalCost() throws IOException, InterruptedException {
        insuranceTravelPage.checkTotalCost();
    }

    @Given("^Выбор значения 'Достаточная' в блоке 'Выберите сумму страховой защиты'$")
    public void selectSufficient() throws IOException {
        insuranceTravelPage.selectSufficient();
    }

    @Given("^Проверка значения 'Итоговая стоимость' после выбора значения 'Достаточная'$")
    public void checkTotalCostAfterSufficient() throws IOException, InterruptedException {
        insuranceTravelPage.checkTotalCostAfterSufficient();
    }

    @Given("^Выбор значения 'Спортивный' в секции 'Рекомендуем предусмотреть'$")
    public void selectSport() throws IOException, InterruptedException {
        insuranceTravelPage.selectSport();
    }

    @Given("^Проверка текста значения 'Спортивный' в блоке 'Рекомендуем предусмотреть'$")
    public void checkValueSport() throws IOException {
        insuranceTravelPage.checkValueSport();
    }

    @Given("^Выбор значения 'Предусмотрительный' в секции 'Рекомендуем предусмотреть' и проверка значения 'Итоговая стоимость'$")
    public void selectProvident() throws IOException, InterruptedException {
        insuranceTravelPage.selectProvident();
    }

   @Given("^Выбор значения 'Защита багажа', отлючение значения 'Спортивный', проверка значения 'Итоговая стоимость'$")
    public void selectProtectionLuggage() throws IOException, InterruptedException {
        insuranceTravelPage.selectProtectionLuggage();
    }

    //@After
    public void after() throws IOException {
        getDriver().quit();
        Init.clearStash();
    }


}
