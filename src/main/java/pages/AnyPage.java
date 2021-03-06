package pages;

import lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.IOException;

/**
 * Created by Татьяна on 20.05.2016.
 */
public abstract class AnyPage {

    /**
     *Конструктор класса AnyPage
     *
     * @throws IOException
     */
    public AnyPage() throws IOException {
        PageFactory.initElements(Init.getDriver(), this);
        waitPageToLoad();
    }

    /**
     * Метод, который проверяет, что браузер отдал страницу
     * @throws IOException
     */
    public void waitPageToLoad() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor)(Init.getDriver());
        js.executeScript("return document.readyState").toString().equals("complete");

    }

    /**
     * Осуществляет нажатие на элемент.
     * @param element - WebElement, для которого необходимо выполнить функцию click()
     * @throws IOException
     */
    public  void click(WebElement element) throws IOException {
        new WebDriverWait(Init.getDriver(), 120)
                .until(ExpectedConditions.elementToBeClickable(element));
        Allure.LIFECYCLE.fire(new AddParameterEvent("Нажатие на элемент", element.getText()));
        element.click();
    }

    /**
     * Осуществляет нажатие на элемент
     * @param by - locator для поиска элемента
     * @throws IOException
     */
    public void click(By by) throws IOException {
       WebElement element = Init.getDriver().findElement(by);
        click(element);
    }

    /**
     * Установить значение для элемента
     * @param element - элемент, которому необходимо установить значение
     * @param text - значение
     */
    public void setText(WebElement element, String text) throws IOException {
        element.clear();
        element.sendKeys(text);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Установлено значение в элемент" + element.getText(), text));
    }

}
