package com.solstice.labtwostarter.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.AggregatedStockData;
import com.solstice.labtwostarter.entity.Quote;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    void load() throws IOException {
        Quote[] quoteArray = extractQuotesFromJsonFile();
        saveQuotesToDatabase(quoteArray);
    }

    public Quote[] extractQuotesFromJsonFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String pathName = "/Users/johnpauldoherty/Downloads/lab-two-starter/src/main/resources/data.json";
        return mapper.readValue(new File(pathName), Quote[].class);
    }

    public void saveQuotesToDatabase(Quote[] quotesArray) {
        quoteRepository.saveAll(Arrays.asList(quotesArray));
    }

//    @RequestMapping(value = "/{symbol}/{date}", method = RequestMethod.GET)
//    AggregatedStockData aggregatedStockData(@PathVariable String symbol, @PathVariable String date) {
//        return new AggregatedStockData();
//    }

    @GetMapping(path="/{symbol}")
    public AggregatedStockData getDataBySymbol(@PathVariable String symbol) {
        return quoteRepository.getDataBySymbol(symbol);
    }

    @GetMapping(path="/testing/{symbol}")
    public Quote getAQuote(@PathVariable String symbol) {
        Quote myquote = quoteRepository.getAQuote();
        System.out.println(myquote);
        return new Quote();
    }

}
