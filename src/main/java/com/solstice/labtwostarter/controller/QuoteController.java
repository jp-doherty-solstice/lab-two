package com.solstice.labtwostarter.controller;

import com.solstice.labtwostarter.entity.StockData;
import com.solstice.labtwostarter.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/load")
    void loadQuotes() throws IOException {
        quoteService.loadQuotes();
    }

    @GetMapping("/{symbol}/{dateString}")
    StockData getData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        return quoteService.getData(symbol, dateString);
    }

    @GetMapping("/{symbol}/{dateString}/monthly")
    StockData getMonthlyData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        return quoteService.getMonthlyData(symbol, dateString);
    }

}
