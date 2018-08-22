package com.solstice.labtwostarter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.Quote;
import com.solstice.labtwostarter.entity.StockData;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
        URL url = new URL("https://bootcamp-training-files.cfapps.io/week4/week4_stocks.json");
        return mapper.readValue(url, Quote[].class);
    }

    private void saveQuotesToDatabase(Quote[] quotesArray) {
        quoteRepository.saveAll(Arrays.asList(quotesArray));
    }

    public StockData getData(String symbol, String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        StockData data = quoteRepository.getData(symbol, getTimestampFromDateString(dateString));
        data.setClosingPrice(quoteRepository.getClosingPrice(symbol, timestamp));
        return data;
    }

    public StockData getMonthlyData(String symbol, String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        StockData data = quoteRepository.getMonthlyData(symbol, timestamp);
        data.setClosingPrice(quoteRepository.getMonthClosingPrice(symbol, timestamp));
        return data;
    }

    private static Timestamp getTimestampFromDateString(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

}
