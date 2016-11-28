package com.taras.kozak.programming.theory.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taras.kozak.programming.theory.pt.constants.PTConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ParsingProcessor {

    static List<String> someList = new ArrayList<String>();

    public List<String> parseKeyWords(String matcher, String[] inputWordsByArray) {

        List<String> matches = new ArrayList<String>();

        for (String inputWords : inputWordsByArray) {
            if (inputWords.equals(matcher)) {
                matches.add(inputWords);
            }
        }
        return matches;
    };

    public List<String> parseIdentificatirs(String[] inputWordsByArray) {

        List<String> listOfIdentificators = new ArrayList<String>();

        for (int i = 0; i < inputWordsByArray.length; i++) {

            String matcher = inputWordsByArray[i];

            String str = "";
            ObjectMapper mapper = new ObjectMapper();
            try {
                str = mapper.writeValueAsString(listOfIdentificators);
            } catch (Exception e) {
                System.out.println("Oops...");
            }
            if (!matcher.equals("") && matcher.toCharArray()[0] == '$' && !str.contains(matcher)) {
                listOfIdentificators.add(matcher);
            }
        }
        return listOfIdentificators;
    }


    public String parseNumbers(String mathcer) {

        int possibleNumber = 0;
        String number = "";

        try {
            possibleNumber = Integer.parseInt(mathcer);
            if (!mathcer.contains(".")){
                if (!mathcer.contains("-")) {
                    return mathcer;
                }
            }
            return number;
        } catch (Exception e) {

        }
        return number;
    }

    public List<String> parseOperator(String operator, List<String> inputList) {

        someList = inputList;
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).contains(operator)) {
                list.add(operator);
                someList.remove(i);
            }
        }
        return list;
    }

    public List<String> parseSingleOperator(String operator) {

        List<String> list = new ArrayList<String>();

        for (int j = 0; j < someList.size(); j++) {
            if (StringUtils.contains(someList.get(j), operator)) {
                list.add(operator);
            }
        }
        return list;
    }

    public List<Character> parseDelimiter(String inputString) {

        List<Character> characterList = new ArrayList<Character>();

        for (int i = 0; i < inputString.toCharArray().length; i++) {
            if (PTConstants.DELIMITER == inputString.toCharArray()[i]) {
                characterList.add(PTConstants.DELIMITER);
            }
        }
        return characterList;
    }

    public List<String> parseExpression(String[] stringArray) {

        List<String> returnedList = new ArrayList<String>();

        for (int i = 0; i < stringArray.length; i++) {

            String expr = StringUtils.substringBetween(stringArray[i], "(", ")");
            if (expr != null && !expr.equals("")) {
                returnedList.add(expr);
            }
        }
        return returnedList;
    }

    public List<Character> parseOperation(char operation, char[] charsArray) {

        List<Character> characterList = new ArrayList<Character>();

        for (int i = 0; i < charsArray.length; i++) {
            if (operation == charsArray[i]) {
                characterList.add(operation);
            }
        }
        return characterList;
    }
}
