package com.wl.servicecommodity;

import com.wl.servicecommodity.entity.CommodityClassification;
import com.wl.servicecommodity.service.CommodityClassificationService;
import com.wl.servicecommodity.service.CommodityClassificationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceCommodityApplicationTests {

    @Autowired
    CommodityClassificationService commodityClassificationService;

    @Test
    public void contextLoads() {

        CommodityClassification commodityClassification = new CommodityClassification();
        commodityClassification.setName("手机");
        commodityClassificationService.insertCommodityClassification(commodityClassification);
    }

}
