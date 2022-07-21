package cn.zyj.service.impl;

import cn.zyj.bean.ClassInfo;
import cn.zyj.mapper.ClassMapper;
import cn.zyj.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {


    @Autowired
    ClassMapper classMapper;


    @Override
    public List<ClassInfo> findAll() {

        List<ClassInfo> all = classMapper.findAll();
        return all;
    }

    @Override
    public int save(ClassInfo classInfo) {
        int row = classMapper.save(classInfo);
        return row;
    }

    @Override
    public int update(ClassInfo classInfo) {
        return classMapper.update(classInfo);
    }


    @Override
    public ClassInfo selectById(int id) {
        return classMapper.selectById(id);
    }

    @Override
    public int delete(int id) {
        return classMapper.delete(id);
    }

    @Override
    public int deleteByIds(int[] ids) {
        return classMapper.deleteByIds(ids);
    }
}
