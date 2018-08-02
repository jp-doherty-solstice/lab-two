package com.solstice.labtwostarter.webservice;

import com.solstice.labtwostarter.JsonManager;
import com.solstice.labtwostarter.entity.AggregatedStockData;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QuoteController {

    private QuoteRepository repository;

    public QuoteController(QuoteRepository repo) {
        repository = repo;
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    void load() throws IOException {
        JsonManager jsonManager = new JsonManager();
        jsonManager.loadQuotesFromJsonFile();
        jsonManager.saveQuotesToDatabase();
    }

    @RequestMapping(value = "/{symbol}/{date}", method = RequestMethod.GET)
    AggregatedStockData aggregatedStockData() {

        return new AggregatedStockData();
    }

}
