package cn.zyj.service.impl;

import cn.zyj.bean.MajorInfo;
import cn.zyj.mapper.MajorMapper;
import cn.zyj.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    MajorMapper majorMapper;

    @Override
    public List<MajorInfo> findAll() {

        try {
            return majorMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询专业信息失败",e);
        }
    }

    @Override
    public int save(MajorInfo majorInfo) {
        return majorMapper.save(majorInfo);
    }

    @Override
    public MajorInfo selectById(int id) {
        return majorMapper.selectById(id);
    }

    @Override
    public int update(MajorInfo majorInfo) {
        return majorMapper.update(majorInfo);
    }

    @Override
    public int deleteById(int id) {
        return majorMapper.deleteById(id);
    }

    @Override
    public int delete(int id) {
        return majorMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(int[] ids) {
        return majorMapper.deleteByIds(ids);
    }
}
