package com.wl.serviceuseradmin.dao;


import com.wl.serviceuseradmin.entity.Menu;
import org.apache.ibatis.annotations.Param;


/**
 * 菜单-权限 --关联表
 */
public interface MenuRoleDao {
    /**
     * 根据权限id 删除菜单
     * @param rid
     * @return
     */
    int deleteMenuByid(@Param("rid") Long rid);

    /**
     * 新增菜单 ---关联权限
     * @param rid
     * @param mids
     * @return
     */
    int addMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);

    Menu findMenuByMidAdrid(@Param("mid") Long mId, @Param("rid") Long rId);

    int giveMenu(@Param("mid") Long mId, @Param("rid") Long rId);

    int subtractMenu(@Param("mid") Long mId, @Param("rid") Long rId);
}
