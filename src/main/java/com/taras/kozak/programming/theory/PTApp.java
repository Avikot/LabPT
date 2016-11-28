package com.taras.kozak.programming.theory;

import com.taras.kozak.programming.theory.service.ParsingService;
import com.taras.kozak.programming.theory.service.ParsingServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.taras.kozak.programming.theory.pt.constants.PTConstants.*;

public class PTApp {

    public static void main(String[] args) {

        ParsingService parsingService = new ParsingServiceImpl();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> matchesList = populateList();

        try {
            System.out.println("\n===========================================================================\n"
                               + "                        ProgramingTheory Lab-3                             "
                               + "\n===========================================================================\n");

            System.out.println("\nInput words for identification: ");
            String inputString = br.readLine();

            parsingService.setInputString(inputString);
            parsingService.outputOperator();
            parsingService.outputSingleOperator();
            System.out.println();
            parsingService.getIdentificators();
            System.out.println();
            parsingService.outputKeyWords(matchesList);
            System.out.println();
            parsingService.outputNumbers();
            System.out.println();
            parsingService.outputOperations();
            System.out.println();
            parsingService.outputDelimiters();
            parsingService.getExpression();

        } catch (Exception e) {
            throw new RuntimeException("\nStream reading failed: ", e);
        }

    }

    private static List<String> populateList() {

        List<String> matchesList = new ArrayList<String>();

        matchesList.add(PROGRAM_WORD);
        matchesList.add(BEGIN_WORD);
        matchesList.add(END_WORD);
        matchesList.add(WHILE_WORD);
        matchesList.add(DO_WORD);
        matchesList.add(IF_WORD);
        matchesList.add(THEN_WORD);
        matchesList.add(ELSE_WORD);

        return matchesList;
    }
}
