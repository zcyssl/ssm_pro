package cn.zyj.mapper;

import cn.zyj.bean.PieInfo;
import cn.zyj.bean.StudentInfo;
import cn.zyj.bean.TotalScore;

import java.util.List;

public interface ChartsMapper {

    List<StudentInfo> findAll();

    List<PieInfo> selectAll();

    List<TotalScore> totalScore();
}
