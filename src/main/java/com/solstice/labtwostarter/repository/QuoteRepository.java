package com.solstice.labtwostarter.repository;

import com.solstice.labtwostarter.entity.AggregatedStockData;
import com.solstice.labtwostarter.entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    @Query("select new com.solstice.labtwostarter.entity.AggregatedStockData(MAX(q.price)) from Quote q where q.symbol = ?1")
    AggregatedStockData getDataBySymbol(String symbol);

    @Query("select q from Quote q where q.id = 3")
    Quote getAQuote();


}
