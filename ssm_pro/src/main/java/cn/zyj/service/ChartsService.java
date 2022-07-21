package cn.zyj.service;

import cn.zyj.bean.PieInfo;
import cn.zyj.bean.StudentInfo;
import cn.zyj.bean.TotalScore;

import java.util.List;

public interface ChartsService {

    List<StudentInfo> findAll();

    List<PieInfo> selectAll();

    List<TotalScore> totalScore();
}
