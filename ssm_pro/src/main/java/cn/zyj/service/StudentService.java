package cn.zyj.service;

import cn.zyj.bean.StudentInfo;

import java.util.List;

public interface StudentService {

    //学生登录
     StudentInfo login(String studentName, String studentPass);

     //查询学生考试信息
    StudentInfo queryStudentExamInfo(int id);

    List<StudentInfo> findAll();

    int save(StudentInfo studentInfo);

    StudentInfo selectById(int id);

    int delete(int id);

    StudentInfo selectByNum(int studentNum);

    int updateSave(StudentInfo studentInfo);

    void updateSave1(StudentInfo login_student);
}
