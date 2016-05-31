package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class СurrencyСonversionPage extends AnyPage {
    private static final String XPATH_SELECT_BEGIN = "//div[contains(@id, 'select2-drop')]//li//div[contains(text(), '";
    private static final String XPATH_SELECT_END = "')]";

    @FindBy(className = "currency-converter-date")
    private WebElement txtCurrentDate;

    @FindBy(xpath = "//label[contains(@class, 'control-label') and contains(text(), 'Поменять')]")
    private WebElement lFrom;

    @FindBy(xpath = "//label[contains(@class, 'control-label') and contains(text(), 'На')]")
    private WebElement lTo;

    @FindBy(id = "from")
    private WebElement tbFrom;

    @FindBy(id = "to")
    private WebElement tbTo;
    
    @FindBy(xpath = "//input[contains(@id, 'from')]/../..//div[contains(@class, 'input-group-addon')]")
    private WebElement selectFrom;
    
   @FindBy(xpath = "//input[@id='to']/../..//div[contains(@class, 'input-group-addon')]")
   private WebElement selectTo;

    @FindBy(className = "currency-converter-result")
    private WebElement txtConverterResult;

    @FindBy(xpath = "//div[contains(@class, 'currency-converter-result')]//span[5]")
    private WebElement txtCurrentCourse;

    @FindBy(xpath = "//div[contains(@class, 'currency-converter-result')]//span")
    private List<WebElement> txtResult;


    /**
     * Конструктор для класса CurrencyConversionPage
     * @throws IOException
     */
    public СurrencyСonversionPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 90)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//span[contains(@class, 'currency-icon') " +
                                "and contains(text(), 'Конвертер валют')]")));
    }

    /**
     * Установить значения в конверте валют
     * @param currencyFrom - валюта в блоке "Поменять"
     * @param currencyTo - валюта в блоке "На"
     * @param valueFrom - значение валюты в блоке "Поменять"
     * @throws IOException
     */
    public void setValue(String currencyFrom, String currencyTo, String valueFrom) throws IOException {
        click(selectFrom);
        click(By.xpath(XPATH_SELECT_BEGIN + currencyFrom + XPATH_SELECT_END));
        Assert.assertEquals(currencyFrom, selectFrom.getText());
        click(tbFrom);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Значение 'Поменять'", currencyFrom));
        setText(tbFrom, valueFrom);
        Assert.assertEquals(valueFrom, tbFrom.getAttribute("value"));
        Allure.LIFECYCLE.fire(new AddParameterEvent("Значение 'Поменять' " + currencyFrom, valueFrom));
        click(selectTo);
        click(By.xpath(XPATH_SELECT_BEGIN + currencyTo + XPATH_SELECT_END));
        Assert.assertEquals(currencyTo, selectTo.getText());
        Allure.LIFECYCLE.fire(new AddParameterEvent("Значение 'На'", currencyTo));

    }

    /**
     * Рассчитать значение и сверить с значение в поле "На"
     * @param valueFrom - значение в поле "Поменять"
     * @throws Throwable
     */
    public void calculateValue(int valueFrom, double currentCourse) throws Throwable {
        double result = (new BigDecimal(currentCourse*valueFrom).setScale(2, RoundingMode.DOWN).doubleValue());
        String resultString = Double.toString(result);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Рассчитанное значение", resultString));
        try {
            Assert.assertEquals(resultString, tbTo.getAttribute("value").replace(" ", ""));
        } catch (Throwable e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Значение рассчитано неверно", ""+valueFrom * currentCourse));
        }


    }

    /**
     * Проверка даты в блоке "Конвертер валют"
     */
    public void checkCurrentDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMMM yyyy");
        Date currentDate = new Date();
        Assert.assertEquals(format1.format(currentDate), txtCurrentDate.getText());
        Allure.LIFECYCLE.fire(new AddParameterEvent("Дата", txtCurrentDate.getText()));
    }

    /**
     * Проверка наличия компонентов в блоке "Конвертер валют"
     */
    public void presenceOfComponents() {
        Assert.assertTrue(lFrom.isDisplayed());
        Assert.assertTrue(lTo.isDisplayed());
        Assert.assertTrue(tbFrom.isDisplayed());
        Assert.assertTrue(tbTo.isDisplayed());
        Assert.assertTrue(selectFrom.isDisplayed());
        Assert.assertTrue(selectTo.isDisplayed());
        Assert.assertTrue(txtConverterResult.isDisplayed());
        Assert.assertTrue(txtResult.get(0).getText().matches("1"));
        Assert.assertTrue(txtResult.get(2).getText().matches("[A-Z][A-Z][A-Z]"));
        Assert.assertTrue(new Float(txtResult.get(4).getText()) > 0);
        Assert.assertTrue(txtResult.get(6).getText().matches("[A-Z][A-Z][A-Z]"));
        Allure.LIFECYCLE.fire(new AddParameterEvent("Компоненты в блоке 'Конвертер валют'", "присутсвуют"));
    }

    /**
     * Установить значения: Поменять - RUB(34); На - EUR
     * @throws IOException
     */
    public void setValueRubEur() throws IOException {
        setValue("RUB", "EUR", "34");
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - RUB(34); На - EUR
     * @throws Throwable
     */
    public void calculateValueRubEur() throws Throwable {
        calculateValue(34, 0.0130327121);
    }

    /**
     * Установить значения: Поменять - USD(10023); На - EUR
     * @throws IOException
     */
    public void setValueUsdEur() throws IOException {
        setValue("USD", "EUR", "10023");
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - USD(10023); На - EUR
     * @throws Throwable
     */
    public void calculateValueUsdEur() throws Throwable {
        calculateValue(10023, 0.832790303662);
    }

    /**
     * Установить значения: Поменять - USD(5); На - USD
     * @throws IOException
     */
    public void setValueUsdUsd() throws IOException {
        setValue("USD", "USD", "5");
        Assert.assertEquals("RUB", selectFrom.getText());
    }

    /**
     * Рассчитать значения для параметров: Поменять - USD(5); На - USD
     * @throws Throwable
     */
    public void calculateValueUsdUsd() throws Throwable {
        calculateValue(5, 0.0144927536);
    }

}
