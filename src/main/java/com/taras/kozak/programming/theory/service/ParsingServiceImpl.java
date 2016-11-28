package com.taras.kozak.programming.theory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taras.kozak.programming.theory.processor.ParsingProcessor;
import com.taras.kozak.programming.theory.pt.constants.PTConstants;

import java.util.ArrayList;
import java.util.List;

public class ParsingServiceImpl implements ParsingService {

    private String inputString;

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    ParsingProcessor parsingProcessor = new ParsingProcessor();

    public void outputKeyWords(List<String> matchesList) {

        String[] inputWordsByArray = inputString.split("[()-+=!<>*/,; ]");

        for (int i = 0; i < 8; i++) {

            String matcher = matchesList.get(i);

            List<String> onOut = parsingProcessor.parseKeyWords(matcher, inputWordsByArray);
            if (onOut.size() != 0) {
                System.out.println("Word \"" + onOut.get(0) + "\" has " + onOut.size() + " matches");
            }
        }
    }

    public void getIdentificators() {

        String[] inputWordsByArray = inputString.split("[()-+=!<>*/,; ]");
        List<String> listOfIdentificators;

        listOfIdentificators = parsingProcessor.parseIdentificatirs(inputWordsByArray);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonIdentificators = mapper.writeValueAsString(listOfIdentificators);
            System.out.println("Identificators: " + jsonIdentificators);
        } catch (Exception e) {
            System.out.println("Oops...");
        }

    }

    public void outputNumbers() {

        String[] stringArray = inputString.split("[()-+=!<>*/,; ]");
        List<String> listOfNumbers = new ArrayList<String>();

        for (int i = 0; i < stringArray.length; i++) {

            String matcher = stringArray[i];

            String number = parsingProcessor.parseNumbers(matcher);

            if (!number.equals("")) {
                listOfNumbers.add(number);
            }
        }
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonNumbers = mapper.writeValueAsString(listOfNumbers);
            System.out.println("Numbers: " + jsonNumbers);
        } catch (Exception e) {
            System.out.println("Oops...");
        }
    }

    public void outputOperations() {

        char[] charsArray = inputString.toCharArray();

        for (int i = 0; i < PTConstants.OPERATIONS_CHARS.length; i++) {

            char operation = PTConstants.OPERATIONS_CHARS[i];

            List<Character> characterList = parsingProcessor.parseOperation(operation, charsArray);
            if (characterList.size() != 0) {
                System.out.println("Operation \"" + characterList.get(0) + "\" has " + characterList.size() + " matches");
            }
        }

    }

    public void outputDelimiters() {

        List<Character> characterList = parsingProcessor.parseDelimiter(inputString);

        if (characterList.size() != 0) {
            System.out.println("Delimiter \";\" has " + characterList.size() + " matches");
        }
    }

    public void getExpression() {

        String[] arrayString = inputString.split("[,; ]");

        List<String> list;
        ObjectMapper mapper = new ObjectMapper();
        list = parsingProcessor.parseExpression(arrayString);

        try {
            System.out.println("Expressions: " + mapper.writeValueAsString(list));
        } catch (Exception e) {
            System.out.println("Oops...");
        }
    }

    public void outputOperator() {

        String[] stringArray = inputString.split("[()-+*/,; ]");

        List<String> stringList = new ArrayList<String>();

        for (int j = 0; j < stringArray.length; j++) {
            stringList.add(stringArray[j]);
        }

        for (int i = 0; i < PTConstants.OPERATORS_ARRAY.length; i++) {

            List<String> operators = parsingProcessor.parseOperator(PTConstants.OPERATORS_ARRAY[i], stringList);
            if (operators.size() != 0) {
                System.out.println("Operator \"" + operators.get(0) + "\" has " + operators.size() + " matches");
            }
        }
    }

    public void outputSingleOperator() {

        for (int i = 0; i < PTConstants.SINGLE_OPERATORS.length; i++) {

            List<String> operators = parsingProcessor.parseSingleOperator(PTConstants.SINGLE_OPERATORS[i]);
            if (operators.size() != 0) {
                System.out.println("Operator \"" + operators.get(0) + "\" has " + operators.size() + " matches");
            }
        }
    }
}
