package cn.zyj.service.impl;

import cn.zyj.bean.PieInfo;
import cn.zyj.bean.StudentInfo;
import cn.zyj.bean.TotalScore;
import cn.zyj.mapper.ChartsMapper;
import cn.zyj.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartsServiceImpl implements ChartsService {

    @Autowired
    ChartsMapper chartsMapper;


    @Override
    public List<StudentInfo> findAll() {
        try {
            return chartsMapper.findAll();

        }catch (Exception e){

            e.printStackTrace();

            throw new RuntimeException("查询学生列表异常",e);

        }
    }

    @Override
    public List<TotalScore> totalScore() {
        try {

            return chartsMapper.totalScore();

        }catch (Exception e){

            e.printStackTrace();

            throw new RuntimeException("查询学生列表异常",e);

        }
    }

    @Override
    public List<PieInfo> selectAll() {

        return chartsMapper.selectAll();

    }


}
