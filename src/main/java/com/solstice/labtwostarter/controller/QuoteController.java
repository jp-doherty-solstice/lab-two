package com.solstice.labtwostarter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.labtwostarter.entity.StockData;
import com.solstice.labtwostarter.entity.Quote;
import com.solstice.labtwostarter.repository.QuoteRepository;
import com.solstice.labtwostarter.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@RestController
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/load")
    void loadQuotes() throws IOException {
        quoteService.loadQuotes();
    }

    @GetMapping("/{symbol}/{dateString}")
    StockData getData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        StockData data = quoteRepository.getDataBySymbolAndDay(symbol, getTimestampFromDateString(dateString));
        data.setClosingPrice(quoteRepository.getClosingPrice(symbol, timestamp));
        return data;
    }

    @GetMapping("/{symbol}/{dateString}/monthly")
    StockData getMonthlyData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        Timestamp timestamp = getTimestampFromDateString(dateString);
        StockData data = quoteRepository.getMonthlyDataBySymbolAndDay(symbol, timestamp);
        data.setClosingPrice(quoteRepository.getMonthClosingPrice(symbol, timestamp));
        return data;
    }

    private static Timestamp getTimestampFromDateString(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

}
