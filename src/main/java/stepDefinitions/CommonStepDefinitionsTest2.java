package stepDefinitions;

import cucumber.api.java.en.Given;
import lib.Init;
import pages.СurrencyСonversionPage;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class CommonStepDefinitionsTest2 {
    СurrencyСonversionPage currencyConversionPage;
    public CommonStepDefinitionsTest2() throws IOException {

    }
    //@Before
    public static void before() throws IOException {
        System.out.println("test2");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("urlTest2", property.get("db.urlTest2"));

    }
    /**
     * Открыть страницу "Конвертер валют"
     * @throws IOException
     */

    @Given("^Открыть страницу 'Конвертер валют'$")
    public  void openCurrencyConversion() throws IOException {
        Allure.LIFECYCLE.fire(new AddParameterEvent("Переход на страницу", "http://www.sberbank.ru/ru/person"));
                getDriver().get(Init.getStash().get("urlTest2").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currencyConversionPage = new СurrencyСonversionPage();
    }

    /**
     * Проверка текущей даты
     */
    @Given("^Проверка текущей даты$")
    public void checkCurrentDate() {
        currencyConversionPage.checkCurrentDate();
    }

    /**
     * Проверка наличия компонентов в блоке "Конвертер валют"
     */
    @Given("^Проверка наличия компонентов в блоке 'Конвертер валют'$")
    public void presenceOfComponents() {
        currencyConversionPage.presenceOfComponents();
    }

    /**
     * Установить значения: Поменять - RUB(34); На - EUR
     * @throws IOException
     */
    @Given("^Установить значения: Поменять - RUB; На - EUR$")
    public void setValueRubEur() throws IOException {
        currencyConversionPage.setValueRubEur();
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - RUB(34); На - EUR
     * @throws Throwable
     */
    @Given("^Проверка рассчитанного значения с параметрами: Поменять - RUB; На - EUR$")
    public void calculateValueRubEur() throws Throwable {
        currencyConversionPage.calculateValueRubEur();
    }

    /**
     * Установить значения: Поменять - USD(10023); На - EUR
     * @throws IOException
     */
    @Given("^Установить значения: Поменять - USD; На - EUR$")
    public void setValueUsdEur() throws IOException {
        currencyConversionPage.setValueUsdEur();
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - USD(10023); На - EUR
     * @throws Throwable
     */
    @Given("^Проверка рассчитанного значения с параметрами: Поменять - USD; На - EUR$")
    public void calculateValueUsdEur() throws Throwable {
        currencyConversionPage.calculateValueUsdEur();
    }

    /**
     * Установить значения: Поменять - USD(5); На - USD
     * @throws IOException
     */
    @Given("^Установить значения: Поменять - USD; На - USD$")
    public void setValueUsdUsd() throws IOException {
        currencyConversionPage.setValueUsdUsd();
    }

    /**
     * Рассчитать значения для параметров: Поменять - USD(5); На - USD
     * @throws Throwable
     */
    @Given("^Рассчитать значения для параметров: Поменять - USD; На - USD$")
    public void calculateValueUsdUsd() throws Throwable {
        currencyConversionPage.calculateValueUsdUsd();
    }

    //@After
    public void after () throws IOException{
        getDriver().quit();
        Init.clearStash();
    }



}
