package ru.markelov.app;

import stepDefinitions.CommonStepDefinitionsTest3;

import java.io.IOException;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class MyThirdTest {
    // @Test
    public  void test() throws IOException, InterruptedException {

        CommonStepDefinitionsTest3 commonStepDefinitionsTest3 = new CommonStepDefinitionsTest3();
        commonStepDefinitionsTest3.openBranchesAndATMs();
        commonStepDefinitionsTest3.selectFilial();
        commonStepDefinitionsTest3.countFilial();
        commonStepDefinitionsTest3.distanceToFilial();
        commonStepDefinitionsTest3.selectTerminal();
        commonStepDefinitionsTest3.distanceToTerminal();
        commonStepDefinitionsTest3.selectShowMore();
        commonStepDefinitionsTest3.deselectFilial();
        commonStepDefinitionsTest3.distanceLocationDeselectFilial();
    }

}

