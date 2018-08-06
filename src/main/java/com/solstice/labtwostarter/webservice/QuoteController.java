package com.solstice.labtwostarter.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.AggregatedStockData;
import com.solstice.labtwostarter.entity.Quote;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@RestController
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    void load() throws IOException {
        Quote[] quoteArray = extractQuotesFromJsonFile();
        saveQuotesToDatabase(quoteArray);
    }

    private Quote[] extractQuotesFromJsonFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String pathName = "/Users/johnpauldoherty/Downloads/lab-two-starter/src/main/resources/data.json";
        return mapper.readValue(new File(pathName), Quote[].class);
    }

    private void saveQuotesToDatabase(Quote[] quotesArray) {
        quoteRepository.saveAll(Arrays.asList(quotesArray));
    }

    @RequestMapping(value = "/{symbol}/{dateString}", method = RequestMethod.GET)
    AggregatedStockData getDataBySymbolAndDate(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        AggregatedStockData data = quoteRepository.getDataBySymbolAndDay(symbol, timestamp);
        data.setClosingPrice(quoteRepository.getClosingPrice(symbol, timestamp));
        return data;
    }

    @RequestMapping(value = "/{symbol}/{dateString}/monthly", method = RequestMethod.GET)
    AggregatedStockData getMonthlyDataBySymbolAndDate(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        AggregatedStockData data = quoteRepository.getMonthlyDataBySymbolAndDay(symbol, timestamp);
        Double closingPrice = quoteRepository.getMonthClosingPrice(symbol, timestamp);
        data.setClosingPrice(closingPrice);
        return data;
    }

    private static Timestamp getTimestampFromDateString(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

}
