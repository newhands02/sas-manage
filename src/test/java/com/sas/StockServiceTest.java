package com.sas;

import com.sas.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockServiceImpl stockService;

    @Test
    public void testIncome(){
//        stockService.test();

    }
}
