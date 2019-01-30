package com.wl.serviceuseradmin.service.impl;


import com.wl.serviceuseradmin.common.UserUtiles;
import com.wl.serviceuseradmin.dao.MenuDao;
import com.wl.serviceuseradmin.dao.MenuRoleDao;
import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuRoleDao menuRoleDao;

    @Override
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }

    @Override
    public List<Menu> getMenusByUserId() {
        User user = UserUtiles.getCurrentUser();
        long userId = user.getId();
        return menuDao.getMenusByUserId(userId);
    }

    @Override
    public List<Menu> menuTree() {
        return menuDao.menuTree();
    }

    @Override
    public List<Long> getMenusByRid(Long rid) {
        return menuDao.getMenusByRid(rid);
    }

    @Override
    public int addMenu(Menu menu) {

        // 对参数非空校验
        if ("".equals(menu.getId()) || menu.getId() == null) {
            return -2;
        }
        //如果menu存在，返回错误
        if (menuDao.findMenuByName(menu.getName()) != null) {
            return -1;
        }
        return menuDao.addMenu(menu);
    }

    @Override
    public int deleteMenu(Long mid) {
        // 对参数非空校验
        if ("".equals(mid) || mid == null) {
            return -2;
        }
        return menuDao.deleteMenu(mid);
    }

    @Override
    public int giveMenus(Long mId, Long rId) {
        // 对参数非空校验
        if ("".equals(mId) || mId == null) {
            return -2;
        }
        // 对参数非空校验
        if ("".equals(rId) || rId == null) {
            return -2;
        }
        //如果menu存在，返回错误
        if (menuRoleDao.findMenuByMidAdrid(mId,rId) != null) {
            return -1;
        }

        return menuRoleDao.giveMenu(mId,rId);
    }

    @Override
    public int subtractMenus(Long mId, Long rId) {
        // 对参数非空校验
        if ("".equals(mId) || mId == null) {
            return -2;
        }
        // 对参数非空校验
        if ("".equals(rId) || rId == null) {
            return -2;
        }
        return menuRoleDao.subtractMenu(mId, rId);
    }
}
