package com.wl.servicecommodity.service;

import com.wl.servicecommodity.entity.CommodityClassification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommodityClassificationServiceTest {

    @Autowired
    private CommodityClassificationService commodityClassificationService;

    @Test
    public void tes() {
        CommodityClassification commodityClassification = new CommodityClassification();
        commodityClassification.setName("手机");
        commodityClassificationService.insertCommodityClassification(commodityClassification);
    }

}