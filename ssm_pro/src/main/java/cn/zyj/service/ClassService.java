package cn.zyj.service;

import cn.zyj.bean.ClassInfo;

import java.util.List;

public interface ClassService {
    public List<ClassInfo> findAll();

    int save(ClassInfo classInfo);

    int update(ClassInfo classInfo);

    ClassInfo selectById(int id);

    int delete(int id);

    int deleteByIds(int[] ids);
}
