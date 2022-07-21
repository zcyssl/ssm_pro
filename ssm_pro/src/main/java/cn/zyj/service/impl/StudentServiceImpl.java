package cn.zyj.service.impl;

import cn.zyj.bean.StudentInfo;
import cn.zyj.mapper.StudentMapper;
import cn.zyj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    //学生登录
    @Override
    public StudentInfo login(String studentName, String studentPass) {
        try {
            return studentMapper.login(studentName,studentPass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("学生登录失败",e);
        }
    }

    @Override
    public StudentInfo queryStudentExamInfo(int num) {
        try {
            return studentMapper.queryStudentExamInfo(num);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询学生考试信息异常",e);
        }
    }

    @Override
    public List<StudentInfo> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public int save(StudentInfo studentInfo) {
        return studentMapper.save(studentInfo);
    }

    @Override
    public StudentInfo selectById(int id) {
        return studentMapper.selectById(id);
    }

    @Override
    public int delete(int id) {
        return studentMapper.delete(id);
    }

    @Override
    public StudentInfo selectByNum(int studentNum) {
        return studentMapper.selectByNum(studentNum);
    }

    @Override
    public int updateSave(StudentInfo studentInfo) {
        return studentMapper.updateSave(studentInfo);
    }

    @Override
    public void updateSave1(StudentInfo login_student) {
        studentMapper.updateSave1(login_student);
    }


}
