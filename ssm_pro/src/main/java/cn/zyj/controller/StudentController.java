package cn.zyj.controller;

import cn.zyj.bean.*;
import cn.zyj.service.MajorService;
import cn.zyj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    MajorService majorService;

    //登录功能
    @RequestMapping("/student_login")
    public String login(String studentNum, String studentPassword, Model model, HttpSession session){

        StudentInfo studentInfo = studentService.login(studentNum, studentPassword);

        if (studentInfo!=null){
            session.setAttribute("login_student",studentInfo);
            return "redirect:index.jsp";
        }else{
            model.addAttribute("tip_info","用户名或者密码错误");
            return "forward:student_login.jsp";
        }
    }

    //注销功能
    @RequestMapping("/student_logout")
    public String student_logout(HttpSession session){

       session.invalidate();

       return "redirect:student_login.jsp";
    }


    //查询学生整体考试信息
    @RequestMapping("/queryinfo")
    public String queryStudentExamInfo(HttpSession session,Model model){

        StudentInfo login_student =(StudentInfo)session.getAttribute("login_student");

        StudentInfo studentInfo = studentService.queryStudentExamInfo(login_student.getStudentNum());


        model.addAttribute("studentExam",studentInfo);


        return "/jsp/student/info/query_info.jsp";

    }



}
