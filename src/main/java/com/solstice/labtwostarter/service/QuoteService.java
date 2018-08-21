package com.solstice.labtwostarter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.Quote;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public void loadQuotes() throws IOException {
        Quote[] quoteArray = extractQuotesFromJsonFile();
        saveQuotesToDatabase(quoteArray);
    }

    private Quote[] extractQuotesFromJsonFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
        return mapper.readValue(url, Quote[].class);
    }

    private void saveQuotesToDatabase(Quote[] quotesArray) {
        quoteRepository.saveAll(Arrays.asList(quotesArray));
    }

}
