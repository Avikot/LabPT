package com.taras.kozak.programming.theory.service;

import java.util.List;

public interface ParsingService {

    void setInputString(String inputString);

    void outputKeyWords(List<String> matchesList);

    void getIdentificators();

    void outputNumbers();

    void outputOperations();

    void outputDelimiters();

    void getExpression();

    void outputOperator();

    void outputSingleOperator();
}
