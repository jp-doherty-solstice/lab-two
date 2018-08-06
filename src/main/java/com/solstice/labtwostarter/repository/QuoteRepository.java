package com.solstice.labtwostarter.repository;

import com.solstice.labtwostarter.entity.AggregatedStockData;
import com.solstice.labtwostarter.entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    String baseQuery = "select new com.solstice.labtwostarter.entity.AggregatedStockData(max(q.price), " +
                        "min(price), sum(volume)) from Quote q where q.symbol = ?1";

    @Query(baseQuery + " and day(q.date) = day(?2)")
    AggregatedStockData getDataBySymbolAndDay(String symbol, Timestamp timestamp);

    @Query(baseQuery + " and month(q.date) = month(?2)")
    AggregatedStockData getMonthlyDataBySymbolAndDay(String symbol, Timestamp timestamp);

}
