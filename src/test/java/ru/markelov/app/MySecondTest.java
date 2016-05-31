package ru.markelov.app;

import lib.Init;
import org.junit.After;
import stepDefinitions.CommonStepDefinitionsTest2;

import java.io.IOException;

import static lib.Init.getDriver;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class MySecondTest {
    // @Test
    public  void test() throws Throwable {

        CommonStepDefinitionsTest2 commonStepDefinitionsTest2 = new CommonStepDefinitionsTest2();
        commonStepDefinitionsTest2.openCurrencyConversion();
        commonStepDefinitionsTest2.checkCurrentDate();
        commonStepDefinitionsTest2.presenceOfComponents();
        commonStepDefinitionsTest2.setValueRubEur();
        commonStepDefinitionsTest2.calculateValueRubEur();
        commonStepDefinitionsTest2.setValueUsdEur();
        commonStepDefinitionsTest2.calculateValueUsdEur();
        commonStepDefinitionsTest2.setValueUsdUsd();
        commonStepDefinitionsTest2.calculateValueUsdUsd();
    }
}

