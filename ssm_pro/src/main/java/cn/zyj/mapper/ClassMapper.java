package cn.zyj.mapper;

import cn.zyj.bean.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {

    //查询班级信息
    List<ClassInfo> findAll();

    int save(ClassInfo classInfo);


    int update(ClassInfo classInfo);

    ClassInfo selectById(@Param("id") int id);

    int delete(int id);

    int deleteByIds(@Param("ids") int[] ids);
}
