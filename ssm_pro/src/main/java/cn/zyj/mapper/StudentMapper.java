package cn.zyj.mapper;

import cn.zyj.bean.StudentInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    //根据id进行查找
    StudentInfo selectById(int id);

    //管理员登录功能
    StudentInfo login(@Param("studentNum") String studentNum, @Param("studentPassword") String studentPassword);

    StudentInfo queryStudentExamInfo(int id);

    List<StudentInfo> findAll();

    int save(StudentInfo studentInfo);

    @Delete("delete from student_info where id =#{id}")
    int delete(@Param("id") int id);

    StudentInfo selectByNum(int studentNum);

    int updateSave(StudentInfo studentInfo);

    void updateSave1(StudentInfo login_student);
}
