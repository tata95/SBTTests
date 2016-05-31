package ru.markelov.app;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import stepDefinitions.ChooseTravelTourStep;

import java.io.IOException;

/**
 * Created by Татьяна on 26.05.2016.
 */
public class FinalTest {

    public void test() throws IOException, InterruptedException, InvalidFormatException {
        ChooseTravelTourStep chooseTravelTourStep = new ChooseTravelTourStep();
        chooseTravelTourStep.openSearchTour();
        chooseTravelTourStep.establishCriterias();
        chooseTravelTourStep.find();
    }

}
