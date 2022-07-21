package cn.zyj.mapper;

import cn.zyj.bean.AdminInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {


    AdminInfo selectById(int id);

    //管理员登录功能
    AdminInfo login(@Param("adminName") String adminName, @Param("adminPass") String adminPass);


    int update(AdminInfo adminInfo);
}
