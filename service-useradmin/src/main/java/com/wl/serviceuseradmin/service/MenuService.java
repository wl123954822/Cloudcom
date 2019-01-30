package com.wl.serviceuseradmin.service;



import com.wl.serviceuseradmin.entity.Menu;

import java.util.List;


public interface MenuService {

     List<Menu> getAllMenu();

     List<Menu> getMenusByUserId();

     List<Menu> menuTree();

     List<Long> getMenusByRid(Long rid);

    int addMenu(Menu menu);

    int deleteMenu(Long mid);

    int giveMenus(Long mId, Long rId);

    int subtractMenus(Long mId, Long rId);
}
