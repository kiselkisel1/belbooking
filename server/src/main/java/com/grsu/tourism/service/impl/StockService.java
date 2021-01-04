package com.grsu.tourism.service.impl;

import com.grsu.tourism.converter.StockConverter;
import com.grsu.tourism.dto.StockDto;
import com.grsu.tourism.model.Stock;
import com.grsu.tourism.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StockService {
    private StockRepository stockRepository;
    private StockConverter stockConverter;

    public List<StockDto> getAll(Pageable pageable) {
        Page<Stock> pagedResult = stockRepository.findAll(pageable);

        List<Stock> stocks = new ArrayList<>();
        if (pagedResult.hasContent()) {
            stocks = pagedResult.getContent();
        }
        return stockConverter.convert(stocks);
    }

    public Stock save(Stock stock) {
        return this.stockRepository.save(stock);
    }

    public void delete(Integer id) {
        stockRepository.deleteById(id);
    }
}