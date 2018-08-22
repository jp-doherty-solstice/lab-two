package com.solstice.labtwostarter.service;

import com.solstice.labtwostarter.entity.StockData;
import com.solstice.labtwostarter.repository.QuoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class QuoteServiceTests {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetData() throws ParseException {

        StockData stockData = new StockData(112.32, 65.54, 8437L);
        Double closingPrice = 86.54;

        when(quoteRepository.getData(any(String.class), any(Timestamp.class))).thenReturn(stockData);
        when(quoteRepository.getClosingPrice(any(String.class), any(Timestamp.class))).thenReturn(closingPrice);

        StockData dailyData = quoteService.getData("GOOG", "2018-06-22 03:34:00");

        assertEquals((Double) 112.32, dailyData.getDailyHigh());
        assertEquals((Double) 65.54, dailyData.getDailyLow());
        assertEquals(8437, dailyData.getTotalVolume());
        assertEquals((Double) 86.54, dailyData.getClosingPrice());

    }

    @Test
    public void testGetMonthlyData() throws ParseException {

        StockData monthlyStockData = new StockData(112.32, 65.54, 8437L);
        Double monthlyClosingPrice = 86.54;

        when(quoteRepository.getMonthlyData(any(String.class), any(Timestamp.class))).thenReturn(monthlyStockData);
        when(quoteRepository.getMonthClosingPrice(any(String.class), any(Timestamp.class))).thenReturn(monthlyClosingPrice);

        StockData monthlyData = quoteService.getMonthlyData("GOOG", "2018-06-22 03:34:00");

        assertEquals((Double) 112.32, monthlyData.getDailyHigh());
        assertEquals((Double) 65.54, monthlyData.getDailyLow());
        assertEquals(8437, monthlyData.getTotalVolume());
        assertEquals((Double) 86.54, monthlyData.getClosingPrice());

    }

}
