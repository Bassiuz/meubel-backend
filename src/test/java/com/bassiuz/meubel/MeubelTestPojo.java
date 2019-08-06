package com.bassiuz.meubel;

import com.bassiuz.meubel.parsers.MeubelParser;

public class MeubelTestPojo{
    private MeubelParser meubelParser;

    private String nameWithMultipleResults;
    private String nameWithJustOneResult;
    private String nameWithNoResults;

    private String nameWithResultsButNotContainingName;

    

    public MeubelParser getMeubelParser() {
        return meubelParser;
    }

    public void setMeubelParser(MeubelParser meubelParser) {
        this.meubelParser = meubelParser;
    }

    public String getNameWithMultipleResults() {
        return nameWithMultipleResults;
    }

    public void setNameWithMultipleResults(String nameWithMultipleResults) {
        this.nameWithMultipleResults = nameWithMultipleResults;
    }

    public String getNameWithJustOneResult() {
        return nameWithJustOneResult;
    }

    public void setNameWithJustOneResult(String nameWithJustOneResult) {
        this.nameWithJustOneResult = nameWithJustOneResult;
    }

    public String getNameWithNoResults() {
        return nameWithNoResults;
    }

    public void setNameWithNoResults(String nameWithNoResults) {
        this.nameWithNoResults = nameWithNoResults;
    }

    public String getNameWithResultsButNotContainingName() {
        return nameWithResultsButNotContainingName;
    }

    public void setNameWithResultsButNotContainingName(String nameWithResultsButNotContainingName) {
        this.nameWithResultsButNotContainingName = nameWithResultsButNotContainingName;
    }

    public MeubelTestPojo(MeubelParser meubelParser, String nameWithMultipleResults, String nameWithJustOneResult,
            String nameWithNoResults, String nameWithResultsButNotContainingName) {
        this.meubelParser = meubelParser;
        this.nameWithMultipleResults = nameWithMultipleResults;
        this.nameWithJustOneResult = nameWithJustOneResult;
        this.nameWithNoResults = nameWithNoResults;
        this.nameWithResultsButNotContainingName = nameWithResultsButNotContainingName;
    }

    
}