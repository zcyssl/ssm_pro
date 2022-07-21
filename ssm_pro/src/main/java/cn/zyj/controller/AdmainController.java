package cn.zyj.controller;

import cn.zyj.bean.AdminInfo;
import cn.zyj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdmainController {

    @Autowired
    AdminService adminService;

    //登录功能
    @RequestMapping("/admin_login")
    public String login(String adminName, String adminPass, Model model, HttpSession session){

        AdminInfo adminInfo = adminService.login(adminName, adminPass);

        if (adminInfo!=null){
            session.setAttribute("login_admin",adminInfo);
            return "redirect:index.jsp";
        }else{
            model.addAttribute("msg","用户名或者秘密错误");
            return "forward:admin_login.jsp";
        }
    }

    //注销功能
    @RequestMapping("/admin_logout")
    public String admin_logout(HttpSession session){

       session.invalidate();

       return "redirect:admin_login.jsp";
    }



    @RequestMapping("/admin/personalcenter/detail")
    public String detail(Model model,HttpSession session){

        AdminInfo login_admin =(AdminInfo) session.getAttribute("login_admin");
        AdminInfo adminInfo=adminService.selectById(login_admin.getId());

        model.addAttribute("admin_info",adminInfo);

        return "/jsp/admin/personalcenter/admin_detail.jsp";
    }

    @RequestMapping("/admin/personalcenter/updatePage")
    public String updatePage(Model model,HttpSession session){

        AdminInfo login_admin =(AdminInfo) session.getAttribute("login_admin");
        AdminInfo adminInfo=adminService.selectById(login_admin.getId());

        model.addAttribute("admin_info",adminInfo);

        return "/jsp/admin/personalcenter/admin_edit.jsp";
    }

    @RequestMapping("/admin/personalcenter/updateSave")
    public String updateSave(AdminInfo adminInfo,Model model,HttpSession session){

        int row=adminService.update(adminInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，个人信息修改成功");

            return "/jsp/admin/personalcenter/admin_edit.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，个人信息修改失败");

            return "/jsp/admin/personalcenter/admin_edit.jsp";

        }
    }


    @RequestMapping("/admin/personalcenter/enterModifyPassword")
    public String enterModifyPassword(AdminInfo adminInfo,Model model,HttpSession session){

        AdminInfo login_admin = (AdminInfo)session.getAttribute("login_admin");

        model.addAttribute("password",login_admin.getAdminPass());

        return "/jsp/admin/personalcenter/modify_password.jsp";

    }

    @RequestMapping("/admin/personalcenter/modifyPassword")
    public String modifyPassword(String new_pass,Model model,HttpSession session){

        AdminInfo login_admin = (AdminInfo)session.getAttribute("login_admin");

        login_admin.setAdminPass(new_pass);

        int row = adminService.update(login_admin);

        if(row!=0){
            model.addAttribute("error_info","true");
            return "/jsp/admin/personalcenter/result_password.jsp";

        }else {
            model.addAttribute("error_info","false");
            return "/jsp/admin/personalcenter/result_password.jsp";
        }

    }


}
