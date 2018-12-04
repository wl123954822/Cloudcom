package com.wl.servicecommodity.controller;

import com.wl.servicecommodity.entity.CommodityClassification;
import com.wl.servicecommodity.service.CommodityClassificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.ldap.PagedResultsControl;

import static org.junit.Assert.*;

public class CommodityClassificationControllerTest {

   @Autowired
   private CommodityClassificationController commodityClassificationController;

    @Test
    public void tes() {
        CommodityClassification commodityClassification = new CommodityClassification();
        commodityClassification.setName("手机");

        commodityClassificationController.addCommondity(commodityClassification);
    }
}