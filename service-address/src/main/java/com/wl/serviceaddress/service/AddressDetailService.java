package com.wl.serviceaddress.service;

import com.wl.serviceaddress.entity.AddRessDetail;

import java.util.List;

public interface AddressDetailService {
    /**
     * 新增地址
     *
     * @param addRessDetail
     * @return
     */
    Boolean insertAddRessDetail(AddRessDetail addRessDetail);

    /**
     * 删除地址
     *
     * @param id
     * @return
     */
    Boolean deleteAddRessDetail(int id);

    /**
     * 修改地址
     *
     * @param addRessDetail
     * @return
     */
    Boolean updateAddRessDetail(AddRessDetail addRessDetail);

    /**
     * list查询
     *
     * @param userId
     * @return
     */
    List<AddRessDetail> listAddressDetail(int userId);

    /**
     * id查询
     *
     * @param id
     * @return
     */
    AddRessDetail selectById(int id);

    /**
     * 设置默认
     *
     * @param id
     * @return
     */
    Boolean changeDefault(int id);

}
