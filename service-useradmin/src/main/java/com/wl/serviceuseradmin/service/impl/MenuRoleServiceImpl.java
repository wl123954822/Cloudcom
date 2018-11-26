package com.wl.serviceuseradmin.service.impl;


import com.wl.serviceuseradmin.dao.MenuRoleDao;
import com.wl.serviceuseradmin.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    private MenuRoleDao menuRoleDao;

    @Override
    public int updateMenuRole(Long rid, Long[] mids) {
        {
            menuRoleDao.deleteMenuByid(rid);
            if (mids.length == 0) {
                return 0;
            }
            return menuRoleDao.addMenu(rid, mids);
        }
    }
}
