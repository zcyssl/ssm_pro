package cn.zyj.mapper;


import cn.zyj.bean.StudentAnswerInfo;

import java.util.List;

public interface StudentAnswerMapper {

    //验证考生是否已答题
    int registerExam(int studentNum);

    //保存学生答题信息
    void save(StudentAnswerInfo studentAnswerInfo);


}
