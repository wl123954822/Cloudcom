package com.wl.serviceaddress.service.impl;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.wl.serviceaddress.dao.AddRessDetailDao;
import com.wl.serviceaddress.entity.AddRessDetail;
import com.wl.serviceaddress.service.AddressDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDetailServiceImpl implements AddressDetailService {

    @Autowired
    private AddRessDetailDao addRessDetailDao;

    @Override
    public Boolean insertAddRessDetail(AddRessDetail addRessDetail) {
        int result = this.addRessDetailDao.insertAddRessDetail(addRessDetail);
        Boolean flag = false;
        if (result >0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean deleteAddRessDetail(int id) {
        int result = this.addRessDetailDao.deleteAddRessDetail(id);
        Boolean flag = false;
        if (result>0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean updateAddRessDetail(AddRessDetail addRessDetail) {
        boolean flag = false;
        int result = this.addRessDetailDao.updateAddRessDetail(addRessDetail);
        if (result >0 ) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<AddRessDetail> listAddressDetail(int userId) {
        List<AddRessDetail> list = new ArrayList<>();
       if (userId != 0) {
           list = this.listAddressDetail(userId);
       }
       return list;
    }

    @Override
    public AddRessDetail selectById(int id) {
        AddRessDetail addRessDetail = new AddRessDetail();
        if (id != 0) {
            addRessDetail = this.addRessDetailDao.selectById(id);
        }
        return addRessDetail;
    }

    @Override
    public Boolean changeDefault(int id) {
        Boolean flag = false;
        //先修改原先默认值，
        AddRessDetail addRessDetail = this.addRessDetailDao.selectDefault(1);
        if (addRessDetail == null) {
            int result = this.addRessDetailDao.changeDefault(id,1);
            if (result >0) {
                flag = true;
            }
        } else {
            int rfirst = this.addRessDetailDao.changeDefault(addRessDetail.getId(),0);
            int rSecond = this.addRessDetailDao.changeDefault(id,1);
            if (rfirst * rSecond >0) {
                flag = true;
            }
        }
        return flag;
    }
}
