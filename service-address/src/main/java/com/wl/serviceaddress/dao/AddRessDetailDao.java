package com.wl.serviceaddress.dao;

import com.wl.serviceaddress.entity.AddRessDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanglei
 */
public interface AddRessDetailDao {
    /**
     * 新增地址
     * @param addRessDetail
     * @return
     */
    int insertAddRessDetail(AddRessDetail addRessDetail);

    /**
     * 删除地址
     * @param id
     * @return
     */
    int deleteAddRessDetail(int id);

    /**
     * 修改地址
     * @param id
     * @return
     */
    int updateAddRessDetail(AddRessDetail addRessDetail);

    /**
     * list查询
     * @param userId
     * @return
     */
    List<AddRessDetail> listAddressDetail(int userId);

    /**
     * id查询
     * @param id
     * @return
     */
    AddRessDetail selectById(int id);

    /**
     * 设置默认
     * @param id
     * @return
     */
    int changeDefault(@Param("id") int id,@Param("isDefault") int isDefault);

    /**
     * 设置默认
     * @param isDefault
     * @return
     */
    AddRessDetail selectDefault (int isDefault);
}
