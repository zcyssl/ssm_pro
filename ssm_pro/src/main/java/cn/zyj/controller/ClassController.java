package cn.zyj.controller;

import cn.zyj.bean.AcademyInfo;
import cn.zyj.bean.AdminInfo;
import cn.zyj.bean.ClassInfo;
import cn.zyj.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<ClassInfo> all = classService.findAll();
        model.addAttribute("class_list",all);

        return "/jsp/admin/class/class_list.jsp";
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "/jsp/admin/class/class_add.jsp";
    }

    @RequestMapping("/addSave")
    public String addSave(ClassInfo classInfo, Model model, HttpSession session){
        AdminInfo login_admin = (AdminInfo)session.getAttribute("login_admin");
        classInfo.setCreator(login_admin.getId());

        int row = classService.save(classInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，班级新增成功");

            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，班级新增操作失败");

            return "/result.jsp";

        }

    }


    @RequestMapping("/updatePage")
    public String updatePage(int id, Model model, HttpSession session){

        ClassInfo classInfo=classService.selectById(id);

        session.setAttribute("class_info",classInfo);
        return "/jsp/admin/class/class_edit.jsp";
    }

    @RequestMapping("/updateSave")
    public String updateSave(ClassInfo classInfo, Model model, HttpSession session){

        int row = classService.update(classInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，班级修改成功");

            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，班级修改操作失败");

            return "/result.jsp";

        }

    }


    @RequestMapping("/delete")
    public String delete(int id,Model model,HttpSession session){

        int row = classService.delete(id);

        List<ClassInfo> all = classService.findAll();
        session.setAttribute("class_list",all);

        if (row != 0){
            model.addAttribute("flag",1);

            model.addAttribute("tip_info","恭喜你，班级删除成功");
            return "/jsp/admin/class/class_list.jsp";
        }else{
            model.addAttribute("flag",0);
            model.addAttribute("tip_info","很抱歉，班级删除操作失败");
            return "/jsp/admin/class/class_list.jsp";
        }

    }

    @RequestMapping("/deleteByIds")
    public String deleteByIds(int[] ids,Model model, HttpServletRequest request){

        int row = classService.deleteByIds(ids);

        System.out.println(row);

        List<ClassInfo> all = classService.findAll();

        model.addAttribute("class_list",all);


        if (row != 0){

            model.addAttribute("tip_info","执行班级删除成功");

            return "/jsp/admin/class/class_list.jsp";
        }else{
            model.addAttribute("tip_info","执行班级删除操作失败");

            return "/jsp/admin/class/class_list.jsp";

        }


    }



}
