package com.solstice.labtwostarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.Quote;

import java.io.File;
import java.io.IOException;

public class JsonManager {

    Quote[] quotesArray;

    public void loadQuotesFromJsonFile() throws IOException {

        System.out.println("INSIDE THE LOAD JSON METHOD");
        ObjectMapper mapper = new ObjectMapper();

        quotesArray = mapper.readValue(new File("/Users/johnpauldoherty/Downloads/lab-two-starter/src/main/resources/data.json"), Quote[].class);

        for (Quote quote : quotesArray) {
            System.out.println(quote.getSymbol());
        }
    }

    public void saveQuotesToDatabase() {

    }

}
