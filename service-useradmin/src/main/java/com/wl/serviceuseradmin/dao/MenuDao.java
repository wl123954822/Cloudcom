package com.wl.serviceuseradmin.dao;



import com.wl.serviceuseradmin.entity.Menu;

import java.util.List;

public interface MenuDao {

    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(Long userId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);

    Menu findMenuByName(String name);

    int addMenu(Menu menu);

    int deleteMenu(Long mid);
}
