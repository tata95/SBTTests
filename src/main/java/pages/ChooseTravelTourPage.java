package pages;

import lib.Init;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

/**
 * Created by Татьяна on 26.05.2016.
 */
public class ChooseTravelTourPage extends AnyPage {
    private static final String XPATH_DATE_END = "')]";
    private static final String XPATH_FROM_TOUR_BEGIN = "//li[contains(@class, 'options-item_city-from" + XPATH_DATE_END + "//span[contains" +
            "(@class, 'item-wrapper_city-from') and text()='";
    private static final String XPATH_END = "']";
    private static final String XPATH_RESULT_START = "//li[@class='resultTr'][1]//div[@class='";
    private static final String XPATH_DATE_START = "//li[contains(@id, '";
    private static final String XPATH_ADULTS_START = "//label[contains(@class, 'label_tourists') and text()='Взрослых']/..//button[text()='";
    private static final String XPATH_CHILDREN_START = "//label[contains(@class, 'label_tourists') and text()='Детей']/..//button[text()='";
    private static final String XPATH_CHILDREN_AGE_START = "//li[contains(@class, 'options-item_children-age')]//span[text()='";
    private static final String XPATH_NIGHT_START = "//div[contains(@class, 'uis-select__options_nights')]//li[contains(@class, 'uis-select__options-item_nights')]//span[text()='";
    private static final String XPATH_FOOD_START = "//label[contains(@class, 'uis-checkbox__label_left') and text()='";
    private static final String XPATH_CATEGORY_START = "//span[contains(@class, 'slsf-category') and text()='";
    private static final String XPATH_CATEGORY_END = "']/..";
    private static final String XPATH_RATING_START = "//div[contains(@class, 'range__pips')]//div[text()='";
    private static final String XPATH_CURRENCY_START = "//ul[contains(@class, ' radio-group_price')]//label[text()='";
    private static final String XPATH_BEACH_LINE_START = "//label[contains(@class, 'label_beach-line') and text()='";
    private Map<String, String[]> criteriaMap = new HashMap<String, String[]>();

    private static final int TOUR_Key = 0;
    private static final int TOUR_Value = 1;

    @FindBy(xpath = "//*[@id='ui-select-departure']")
    private WebElement elemSelectDeparture;

    @FindBy(xpath = "//*[@id='ui-select-country-to']")
    private WebElement elemSelectCountryTo;

    @FindBy(xpath = "//div[contains(@class, 'result-full')]//li[contains(@class, 'resultTr')]")
    private List<WebElement> result;

    @FindBy(xpath = "//span[contains(@class, 'uis-text uis-text_departure')]")
    private WebElement elementDate;

    @FindBy(xpath = XPATH_ADULTS_START + "2" + XPATH_END)
    private WebElement bAdults2;

    @FindBy(xpath = XPATH_ADULTS_START + "1" + XPATH_END)
    private WebElement bAdults1;

    @FindBy(xpath = XPATH_ADULTS_START + "3" + XPATH_END)
    private WebElement bAdults3;

    @FindBy(xpath = XPATH_ADULTS_START + "4" + XPATH_END)
    private WebElement bAdults4;

    @FindBy(xpath = XPATH_CHILDREN_START + "0" + XPATH_END)
    private WebElement bChildren0;

    @FindBy(xpath = XPATH_CHILDREN_START + "1" + XPATH_END)
    private WebElement bChildren1;

    @FindBy(xpath = XPATH_CHILDREN_START + "2" + XPATH_END)
    private WebElement bChildren2;

    @FindBy(xpath = XPATH_CHILDREN_START + "3" + XPATH_END)
    private WebElement bChildren3;

    @FindBy(xpath = "//div[contains(@class, 'uis-scrollbar_city-from')]//li[contains(@class, 'select__options-item_city-from')]//span")
    private List<WebElement> selectCityFrom;

    @FindBy(xpath = "//div[contains(@class, 'uis-scrollbar_country-to')]//li//span")
    private List<WebElement> selectCountryTo;

    @FindBy(xpath = "//*[@id='telefones-close']")
    private WebElement bHide;

    @FindBy(xpath = XPATH_FOOD_START + "AI" + XPATH_END)
    private WebElement lFoodAI;

    @FindBy(xpath = XPATH_FOOD_START + "BB" + XPATH_END)
    private WebElement lFoodBB;

    @FindBy(xpath = XPATH_FOOD_START + "FB" + XPATH_END)
    private WebElement lFoodFB;

    @FindBy(xpath = XPATH_FOOD_START + "FB+" + XPATH_END)
    private WebElement lFoodFb;

    @FindBy(xpath = XPATH_FOOD_START + "HB" + XPATH_END)
    private WebElement lFoodHB;

    @FindBy(xpath = XPATH_FOOD_START + "HB+" + XPATH_END)
    private WebElement lFoodHb;

    @FindBy(xpath = XPATH_FOOD_START + "UAI" + XPATH_END)
    private WebElement lFoodUAI;

    @FindBy(xpath = XPATH_FOOD_START + "RO" + XPATH_END)
    private WebElement lFoodRO;

    @FindBy(xpath = "//section//a[contains(@class, 'controls__button-find') and text()='Найти']")
    private WebElement bFind;

    @FindBy(xpath = "//*[@id='ui-select-nightsMin']")
    private WebElement bNightMin;

    @FindBy(xpath = "//*[@id='ui-select-nightsMax']")
    private WebElement bNightMax;

    @FindBy(xpath = "//label[contains(@class, 'category-rest') and text()='Apts']")
    private WebElement bCategoryApts;

    @FindBy(xpath = "//div[contains(@class, 'slsf-hotels__additional-filter-block')]//span[contains(@class, 'slsf-hotels')]")
    private WebElement lAddFilters;

    @FindBy(xpath = "//input[contains(@class, 'filter-apply') and contains(@value, 'Применить')]")
    private WebElement bApply;

    @FindBy(xpath = "//span[contains(text(),'Цена от')]/..//input")
    private WebElement iPriceFrom;

    @FindBy(xpath = "//span[contains(text(),'Цена до')]/..//input")
    private WebElement iPriceTo;

    @FindBy(xpath = "//label[contains(@class, 'flight-info ') and contains(text(), 'Перелёт включен')]")
    private WebElement lFlight;

    @FindBy(xpath = "//label[contains(@class, 'flight-info ') and contains(text(), 'Есть билеты')]")
    private WebElement lTicket;

    @FindBy(xpath = "//label[contains(@class, 'flight-info ') and contains(text(), 'Есть места')]")
    private WebElement lPlace;

    @FindBy(xpath = "//div[contains(@class, 'options_children-age')]//li//span")
    private List<WebElement> listChildrenAge;

    @FindBy(xpath = XPATH_RESULT_START + "priceval" + XPATH_END)
    private  WebElement elemPriceval;

    @FindBy(xpath = XPATH_RESULT_START + "vilet']//span[@class='vilet-days']")
    private WebElement elemCountNight;

    /**
     * Конструктор для класса ChooseTravelTourPage
     * @throws IOException
     */
    public ChooseTravelTourPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 90)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//span[text()='Поиск тура']/../..")));
    }

    /**
     * Загрузка критериев из файла xlsx, и установка считанных критериев
     * @throws IOException
     * @throws InterruptedException
     * @throws InvalidFormatException
     */
    public void readCriterion () throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("src/test/input/input.xlsx"));
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        Iterator<Row> rows = myExcelSheet.rowIterator();
        String[] valueString;
        Allure.LIFECYCLE.fire(new AddParameterEvent("Введенные критерии",""));
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            XSSFCell keyCell = row.getCell(TOUR_Key);
            XSSFCell valueCell = row.getCell(TOUR_Value);
            if (valueCell != null) {
                switch (valueCell.getCellType()) {
                    case CELL_TYPE_NUMERIC:
                        if (keyCell.getStringCellValue().contains("Date")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            valueString = sdf.format(valueCell.getDateCellValue()).split(";");
                            criteriaMap.put(keyCell.getStringCellValue(), valueString);
                            Allure.LIFECYCLE.fire(new AddParameterEvent(keyCell.getStringCellValue(), valueString[0]));
                        } else {
                            String value1 = "" + valueCell.getNumericCellValue();
                            valueString = value1.substring(0, value1.indexOf(".")).split(";");
                            criteriaMap.put(keyCell.getStringCellValue(), valueString);
                            Allure.LIFECYCLE.fire(new AddParameterEvent(keyCell.getStringCellValue(), valueString[0]));
                        }
                        break;
                    case CELL_TYPE_STRING:
                        valueString = valueCell.getStringCellValue().split(";");
                        criteriaMap.put(keyCell.getStringCellValue(), valueString);
                        Allure.LIFECYCLE.fire(new AddParameterEvent(keyCell.getStringCellValue(), valueString[0]));
                };
            }
        }
        myExcelBook.close();
    }

    public void establishCriterias() throws IOException, InterruptedException, InvalidFormatException {
        new WebDriverWait(Init.getDriver(), 60)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(@class, 'uis-text uis-text_departure" + XPATH_DATE_END)));
        readCriterion();
        if (bHide.getText().equals("Скрыть"))
            click(bHide);
        for (String key : criteriaMap.keySet()) {
            switch (key) {
                case "From":
                    click(elemSelectDeparture);
                    setText(elemSelectDeparture, criteriaMap.get("From")[0]);
                    if (selectCityFrom.size() >= 1)
                        click(selectCityFrom.get(1));
                    else
                        Allure.LIFECYCLE.fire(new AddParameterEvent("Введенного города ", criteriaMap.get("From")[0] +
                                " не найдено. Установлено значение по умолчанию."));
                    break;
                case "To":
                    click(elemSelectCountryTo);
                    setText(elemSelectCountryTo, criteriaMap.get("To")[0]);
                    Thread.sleep(500);
                    if (selectCountryTo.size() >= 1)
                        click(selectCountryTo.get(1));
                    else Allure.LIFECYCLE.fire(new AddParameterEvent("Введенной страны ", criteriaMap.get("To")[0] +
                            " не найдено. Установлено значение по умолчанию."));
                    break;
                case "Date_Start":
                    if ((Integer.parseInt(criteriaMap.get("Date_Start")[0].substring(4,5))>7) ||
                            Integer.parseInt(criteriaMap.get("Date_Finish")[0].substring(4,5))>7)
                        Allure.LIFECYCLE.fire(new AddParameterEvent
                                ("Введенные значение даты",
                                        criteriaMap.get("Date_Start")[0] +"-"+ criteriaMap.get("Date_Finish")[0]+
                                                " не могут быть использованы, извините за неудобства"
                                ));
                    else {
                        click(elementDate);
                        click(By.xpath(XPATH_DATE_START + criteriaMap.get("Date_Start")[0] + XPATH_DATE_END));
                        click(By.xpath(XPATH_DATE_START + criteriaMap.get("Date_Finish")[0] + XPATH_DATE_END));
                    }
                    break;
                case "Adults":
                    switch (criteriaMap.get("Adults")[0]) {
                        case "1":
                            click(bAdults1);
                            break;
                        case "2":
                            click(bAdults2);
                            break;
                        case "3":
                            click(bAdults3);
                            break;
                        case "4":
                            click(bAdults4);
                            break;
                    }
                    break;
                case "Children":
                    if (Integer.parseInt(criteriaMap.get("Children")[0]) == criteriaMap.get("Age").length){
                        click(By.xpath(XPATH_CHILDREN_START + criteriaMap.get("Children")[0] + XPATH_END));
                        Thread.sleep(1000);
                        for (int i = 0; i < (new Integer(criteriaMap.get("Children")[0])); i++) {
                            Init.getDriver().findElement
                                    (By.xpath("//*[@id='ui-select-child-" + i + "']"))
                                    .click();
                            Thread.sleep(1000);
                            System.out.println("listChildrenAge: " + listChildrenAge.size());
                            for (WebElement elm : listChildrenAge) {
                                if (elm.getText().trim().contains(criteriaMap.get("Age")[i])) {
                                    elm.click();
                                }
                            }
                            Thread.sleep(500);
                            if(Integer.parseInt(criteriaMap.get("Age")[i])>12)
                                Allure.LIFECYCLE.fire(new AddParameterEvent
                                        ("Введенное значение возраста ребенка",
                                                criteriaMap.get("Age")[i]+" было проигнорировано, " +
                                                        "следует ввести значение меньше 12. Извините за неудобства"));
                        }

                    }
                    else Allure.LIFECYCLE.fire(new AddParameterEvent("Критерий Chidren",
                            criteriaMap.get("Children")[0]+" проигнорирован, так как введены не все значения возраста детей."));
                case "Night_Min":
                    if (Integer.parseInt(criteriaMap.get("Night_Min")[0])>13)
                        Allure.LIFECYCLE.fire(new AddParameterEvent("Введенное значение",
                                criteriaMap.get("Night_Min")[0]+",для поля Night_Max не может быть установлено.Извините за неудобства." +
                                        "Следует ввести значение меньше 14.Установлено значение по умолчанию"));
                    else {
                        click(bNightMin);
                        click(By.xpath(XPATH_NIGHT_START + criteriaMap.get("Night_Min")[0] + XPATH_END));
                    }
                    break;
                case "Night_Max":
                    if (Integer.parseInt(criteriaMap.get("Night_Max")[0])>13)
                        Allure.LIFECYCLE.fire(new AddParameterEvent("Введенное значение",
                                criteriaMap.get("Night_Max")[0]+",для поля Night_Max не может быть уcтановлено.Извините за неудобства." +
                                        "Следует ввести значение меньше 14.Установлено значение по умолчанию"));
                    else {
                        click(bNightMax);
                        click(By.xpath(XPATH_NIGHT_START + criteriaMap.get("Night_Max")[0] + XPATH_END));
                    }
                    break;
                case "Food":
                    for (int i=0; i<criteriaMap.get("Food").length; i++){
                        switch (criteriaMap.get("Food")[i]) {
                            case "AI":
                                click(lFoodAI);
                                break;
                            case "BB":
                                click(lFoodBB);
                                break;
                            case "FB":
                                click(lFoodFB);
                                break;
                            case "FB+":
                                click(lFoodFb);
                                break;
                            case "HB+":
                                click(lFoodHb);
                                break;
                            case "HB":
                                click(lFoodHB);
                                break;
                            case "RO":
                                click(lFoodRO);
                                break;
                            case "UAI":
                                click(lFoodUAI);
                                break;
                            default:
                                click(By.xpath("//label[contains(@class, 'checkbox__label_meal') and text()='Любое']"));
                                break;
                        }
                    }
                    break;
                case "Category":
                    for (int i = 0; i<criteriaMap.get("Category").length; i++){
                        switch (criteriaMap.get("Category")[i]) {
                            case "2":
                                click(By.xpath(XPATH_CATEGORY_START + "2" + XPATH_CATEGORY_END));
                                break;
                            case "3":
                                click(By.xpath(XPATH_CATEGORY_START + "3" + XPATH_CATEGORY_END));
                                break;
                            case "4":
                                click(By.xpath(XPATH_CATEGORY_START + "4" + XPATH_CATEGORY_END));
                                break;
                            case "5":
                                click(By.xpath(XPATH_CATEGORY_START + "5" + XPATH_CATEGORY_END));
                                break;
                            case "Apts":
                                click(bCategoryApts);
                                break;
                        }
                    }
                    break;
                case "Rating":
                    click(lAddFilters);
                    click(By.xpath(XPATH_RATING_START + criteriaMap.get("Rating")[0] + XPATH_END));
                    click(bApply);
                    break;
                case "Beach_Line":
                    for (int i = 0; i<criteriaMap.get("Beach_Line").length; i++){
                        switch (criteriaMap.get("Beach_Line")[i]) {
                            case "1":
                                click(lAddFilters);
                                click(By.xpath(XPATH_BEACH_LINE_START + "Первая линия" + XPATH_END));
                                click(bApply);
                                break;
                            case "2":
                                click(lAddFilters);
                                click(By.xpath(XPATH_BEACH_LINE_START + "Вторая линия" + XPATH_END));
                                click(bApply);
                                break;
                            case "3":
                                click(lAddFilters);
                                click(By.xpath(XPATH_BEACH_LINE_START + "Третья линия" + XPATH_END));
                                click(bApply);
                                break;
                            default:
                                click(lAddFilters);
                                click(By.xpath(XPATH_BEACH_LINE_START + "Любая" + XPATH_END));
                                click(bApply);
                                break;
                        }
                    }
                case "Price_From":
                    click(iPriceFrom);
                    setText(iPriceFrom, criteriaMap.get("Price_From")[0]);
                    break;
                case "Price_To":
                    click(iPriceTo);
                    setText(iPriceTo, criteriaMap.get("Price_To")[0]);
                    break;
                case "Currency":
                    click(By.xpath(XPATH_CURRENCY_START + criteriaMap.get("Currency")[0] + XPATH_END));
                    break;
                case "Flight":
                    if (!lFlight.getAttribute("class").contains("label_checked"))
                        click(lFlight);
                    break;
                case "Ticket":
                    if (!lTicket.getAttribute("class").contains("label_checked"))
                        click(lTicket);
                    break;
                case "Place":
                    if (!lPlace.getAttribute("class").contains("label_checked"))
                        click(lPlace);
                    break;

            }
        }
    }

    /**
     * Поиск тура и возраст результата
     * @throws IOException
     * @throws InterruptedException
     */
    public void find() throws IOException, InterruptedException {
        click(bFind);
        waitPageToLoad();
        Thread.sleep(9000);
        int sizeList = result.size();
        if (sizeList > 0) {
            int size = Init.getDriver().findElements
                    (By.xpath(XPATH_RESULT_START +"rating']//span[contains(@class, 's1')]")).size();
            if (criteriaMap.containsKey("To"))
            Assert.assertEquals("Для введенной страны не найдено тура",
                    elemSelectCountryTo.getAttribute("value"), (criteriaMap.get("To")[0]));
            if(criteriaMap.containsKey("Category"))
            Assert.assertTrue("Категория отеля не соотвествует поиску",
                    criteriaMap.get("Category")[0].contains(size+""));
            if (criteriaMap.containsKey("Price_From"))
             Assert.assertTrue("Стоимость меньше заявленной",
                     Integer.parseInt(criteriaMap.get("Price_From")[0])<=Integer.parseInt(elemPriceval.getText().replace(" ","")));
            if (criteriaMap.containsKey("Price_To"))
                Assert.assertTrue("Стоимость больше заявленной",
                        Integer.parseInt(criteriaMap.get("Price_To")[0])>=Integer.parseInt(elemPriceval.getText().replace(" ","")));
            if (criteriaMap.containsKey("Night_Min"))
                Assert.assertTrue("Количество ночей меньше заявленных",
                        Integer.parseInt(criteriaMap.get("Night_Min")[0])<=Integer.parseInt(elemCountNight.getText().substring(2,3)));
            if (criteriaMap.containsKey("Night_Max"))
                Assert.assertTrue("Количество ночей больше заявленных",
                        Integer.parseInt(criteriaMap.get("Night_Max")[0])>=Integer.parseInt(elemCountNight.getText().substring(2,3)));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Название курорта",
                    Init.getDriver().findElement
                            (By.xpath("//li[@class='resultTr'][1]//p[@class='ellipsis'][1]")).getText()));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Страна",
                    elemSelectCountryTo.getText()));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Стоимость",
                   elemPriceval.getText()
                    + criteriaMap.get("Currency")[0]));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Название отеля",
                    Init.getDriver().findElement
                            (By.xpath(XPATH_RESULT_START + "title" + XPATH_END)).getText()));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Количество звезд", ""+size));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Туроператор",
                    Init.getDriver().findElement
                            (By.xpath(XPATH_RESULT_START + "logocontainer" + XPATH_END)).getAttribute("title")));
            Allure.LIFECYCLE.fire(new AddParameterEvent("Количество ночей",
                    elemCountNight.getText()));
        } else Allure.LIFECYCLE.fire(new AddParameterEvent("По вашем критериям отобрано", "0 туров"));
    }
}

