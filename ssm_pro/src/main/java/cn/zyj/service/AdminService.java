package cn.zyj.service;

import cn.zyj.bean.AdminInfo;

public interface AdminService {


    //管理员登录
    AdminInfo login(String adminName, String adminPass);


    AdminInfo selectById(Integer id);

    int update(AdminInfo adminInfo);
}
