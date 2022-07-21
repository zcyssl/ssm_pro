package cn.zyj.controller;


import cn.zyj.bean.*;
import cn.zyj.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentExamController {

    @Autowired
    PaperService paperService;
    @Autowired
    StudentAnswerService studentAnswerService;

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    MajorService majorService;

    @RequestMapping("/personalcenter/detail")
    public String personalCenterDetail(Model model,HttpSession session){

        StudentInfo login_student = (StudentInfo)session.getAttribute("login_student");
        StudentInfo studentInfo=studentService.selectByNum(login_student.getStudentNum());

        model.addAttribute("student_info",studentInfo);

        return "/jsp/student/personalcenter/student_detail.jsp";
    }


    @RequestMapping("/personalcenter/updatePage")
    public String personalCenterUpdatePage(Model model,HttpSession session){

        StudentInfo login_student = (StudentInfo)session.getAttribute("login_student");
        StudentInfo studentInfo=studentService.selectByNum(login_student.getStudentNum());

        model.addAttribute("student_info",studentInfo);

        return "/jsp/student/personalcenter/student_edit.jsp";
    }


    @RequestMapping("/personalcenter/enterModifyPassword")
    public String enterModifyPassword(Model model,HttpSession session){

        StudentInfo login_student = (StudentInfo)session.getAttribute("login_student");
        StudentInfo studentInfo=studentService.selectByNum(login_student.getStudentNum());

        model.addAttribute("student_info",studentInfo);

        return "/jsp/student/personalcenter/modify_password.jsp";
    }

    @RequestMapping("/personalcenter/updateSave")
    public String updateSave(StudentInfo studentInfo, Model model, HttpSession session){


        StudentInfo login_student = (StudentInfo)session.getAttribute("login_student");
        studentInfo.setId(login_student.getId());
        int row = studentService.updateSave(studentInfo);

        if (row != 0){

            StudentInfo studentInfo1=studentService.selectByNum(login_student.getStudentNum());

            model.addAttribute("student_info",studentInfo1);

            model.addAttribute("tip_info","更新信息成功");

            return "/jsp/student/personalcenter/student_edit.jsp";
        }else{
            StudentInfo studentInfo1=studentService.selectByNum(login_student.getStudentNum());

            model.addAttribute("student_info",studentInfo1);

            model.addAttribute("tip_info","很抱歉，更新信息失败");

            return "/jsp/student/personalcenter/student_edit.jsp";

        }

    }

    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<StudentInfo> student_list=studentService.findAll();

        model.addAttribute("student_list",student_list);
        return "/jsp/admin/student/student_list.jsp";

    }


    @RequestMapping("/findByPage")
    public String findByPage(@RequestParam(defaultValue = "1")int pageNum, Model model){

        int pageSize = 8;//定义每页显示几条条数据

        PageHelper.startPage(pageNum,pageSize);

        List<StudentInfo> all = studentService.findAll();

        PageInfo<StudentInfo> pageInfo = new PageInfo<>(all);

        model.addAttribute("student_list",all);
        model.addAttribute("pageInfo",pageInfo);

        return "/jsp/admin/student/student_list.jsp";

    }


    @RequestMapping("/delete")
    public String delete(int id,Model model,HttpSession session){

        int row = studentService.delete(id);

        List<StudentInfo> all = studentService.findAll();
        session.setAttribute("student_list",all);

        if (row != 0){
            model.addAttribute("flag",1);

            model.addAttribute("tip_info","恭喜你，学生信息删除成功");
            return "/jsp/admin/student/student_list.jsp";
        }else{
            model.addAttribute("flag",0);
            model.addAttribute("tip_info","很抱歉，学生信息删除操作失败");
            return "/jsp/admin/student/student_list.jsp";
        }

    }

    @RequestMapping("/updatePage")
    public String updatePage(int id, Model model, HttpSession session){

        StudentInfo studentInfo=studentService.selectById(id);

        List<MajorInfo> all = majorService.findAll();
        session.setAttribute("major_list",all);
        session.setAttribute("student_info",studentInfo);
        return "/jsp/admin/student/student_edit.jsp";
    }



    @RequestMapping("/addSave")
    public String addSave(StudentInfo studentInfo, Model model, HttpSession session){
        studentInfo.setCreateTime(new Date());
        AdminInfo adminInfo = (AdminInfo)session.getAttribute("login_admin");
        studentInfo.setCreator(adminInfo.getId());
        int row = studentService.save(studentInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，执行学生新增成功");

            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，执行学生新增操作失败");

            return "/result.jsp";

        }

    }


    @RequestMapping("/addPage")
    public String addPage(Model model){

        List<MajorInfo> all = majorService.findAll();

        model.addAttribute("major_list",all);

        return "/jsp/admin/student/student_add.jsp";

    }


    @RequestMapping("/exam")
    public String toExamPage(Model model, HttpSession session){

        StudentInfo studentInfo = (StudentInfo)session.getAttribute("login_student");

        //验证学生是否交卷
        int examFlag = studentAnswerService.registerExam(studentInfo.getStudentNum());

        //若之前没有进行答题
        if (examFlag == 0){
            //组装答题页面数据（拿到试题paper）
            List<PaperInfo> exam = paperService.findExam();

            model.addAttribute("paper_list",exam);

            return "/jsp/student/index.jsp";
        }else{//若进行答题

           model.addAttribute("tip_info","您已经参与过测试，不允许重复测试");
           return "/main.jsp";
        }


    }


    @RequestMapping("/answer")
    public String studentAnswer(int[] questionNum ,HttpServletRequest request){


        List<StudentAnswerInfo>  list = new ArrayList<>();

        //获取当前登录的学生信息
        StudentInfo login_student =(StudentInfo)request.getSession().getAttribute("login_student");

        //获取学生总分
        int total = 0;

        for (int num : questionNum) {

            StudentAnswerInfo studentAnswerInfo = new StudentAnswerInfo();

            String studentType = request.getParameter(num + "_optionType");//考生所选答案

            PaperInfo paperMes = paperService.findPaperMes(num);

            String answerType = paperMes.getQuestionAnswer();

            //如果所选类型跟题目答案一致
            if (studentType!=null && studentType.equals(answerType)){

                studentAnswerInfo.setStudentCore(paperMes.getQuestionScore());
            }else{////如果所选类型跟题目答案不一致

                studentAnswerInfo.setStudentCore(0);
            }
            //组装完学生答题数据
            studentAnswerInfo.setStudentNumId(login_student.getStudentNum());
            studentAnswerInfo.setQuestionNumId(String.valueOf(num));
            studentAnswerInfo.setAnswerType(studentType);
            studentAnswerInfo.setCreateTime(new Date());
            studentAnswerInfo.setCreator(login_student.getId());

            list.add(studentAnswerInfo);

            //执行新增方法进行数据的添加
            studentAnswerService.save(list);

            //获取学生的总分
            total += studentAnswerInfo.getStudentCore();

            //将总分以及学生交卷状态存到StudentInfo中，
            login_student.setTotalScore(total);

            //修改考生交卷状态
            login_student.setExamFlag(1);//表示已交卷

            //根据总分进行分班

            if(total>=0&&total<=59)
                login_student.setClassId(3);
            else if(total>=60&&total<=79)
                login_student.setClassId(2);
            else login_student.setClassId(1);

            studentService.updateSave1(login_student);

            //获取班级信息，拿到分数范围
            //后台执行查找所有班级信息的操作，遍历班级信息，假设学生总分在此班级分数范围内



            //login_student.setClassId();



            //其实根据学生id对exam_flag以及total_score字段以及class_id进行数据修改
            //........

        }
        return "/main.jsp";
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
