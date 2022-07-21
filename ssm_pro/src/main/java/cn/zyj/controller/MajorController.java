package cn.zyj.controller;

import cn.zyj.bean.AcademyInfo;
import cn.zyj.bean.ClassInfo;
import cn.zyj.bean.MajorInfo;
import cn.zyj.bean.AdminInfo;
import cn.zyj.service.AcademyService;
import cn.zyj.service.MajorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    AcademyService academyService;


    @RequestMapping("/findByPage")
    public String findByPage(@RequestParam(defaultValue = "1")int pageNum,Model model){

        int pageSize = 8;//定义每页显示几条条数据

        PageHelper.startPage(pageNum,pageSize);

        List<MajorInfo> all = majorService.findAll();

        PageInfo<MajorInfo> pageInfo = new PageInfo<>(all);

        model.addAttribute("major_list",all);
        model.addAttribute("pageInfo",pageInfo);

        return "/jsp/admin/major/major_list.jsp";

    }


    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<MajorInfo> all = majorService.findAll();

        model.addAttribute("major_list",all);

        return "/jsp/admin/major/major_list.jsp";

    }

    //跳转到新增页面
    @RequestMapping("/addPage")
    public String addPage(Model model){

        List<AcademyInfo> all = academyService.findAll();
        model.addAttribute("academy_list",all);
        return "/jsp/admin/major/major_add.jsp";

    }

    //执行专业新增
    @RequestMapping("/addSave")
    public String addSave(MajorInfo majorInfo,int academyInfoId,Model model, HttpSession session){

        majorInfo.setCreateTime(new Date());

        AdminInfo adminInfo = (AdminInfo)session.getAttribute("login_admin");
        majorInfo.setCreator(adminInfo.getId());
        majorInfo.setAcademy_info_id(academyInfoId);

        System.out.println(majorInfo);
        int row = majorService.save(majorInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，执行专业新增成功");

            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，执行专业新增操作失败");

            return "/result.jsp";

        }

    }


    //跳转到新增页面
    @RequestMapping("/updatePage")
    public String updatePage(int id,Model model){

        List<AcademyInfo> all = academyService.findAll();
        model.addAttribute("academy_list",all);

        MajorInfo majorInfo = majorService.selectById(id);
        model.addAttribute("major_info",majorInfo);

        return "/jsp/admin/major/major_edit.jsp";

    }

    //执行专业修改
    @RequestMapping("/updateSave")
    public String updateSave(MajorInfo academyInfo, Model model, HttpSession session){

        academyInfo.setOperateTime(new Date());

        AdminInfo adminInfo = (AdminInfo)session.getAttribute("login_admin");
        academyInfo.setOperator(adminInfo.getId());

        int row = majorService.update(academyInfo);

        if (row != 0){

            model.addAttribute("tip_info","恭喜你，执行专业修改成功");

            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，执行专业修改操作失败");

            return "/result.jsp";

        }

    }

    @RequestMapping("/delete")
    public String delete(int id,Model model,HttpSession session){

        int row = majorService.delete(id);

        List<MajorInfo> all = majorService.findAll();
        session.setAttribute("major_list",all);

        if (row != 0){
            model.addAttribute("flag",1);

            model.addAttribute("tip_info","恭喜你，专业删除成功");
            return "/jsp/admin/major/major_list.jsp";
        }else{
            model.addAttribute("flag",0);
            model.addAttribute("tip_info","很抱歉，专业删除操作失败");
            return "/jsp/admin/major/major_list.jsp";
        }

    }

    @RequestMapping("/deleteByIds")
    public String deleteByIds(int[] ids,Model model, HttpServletRequest request){

        int row = majorService.deleteByIds(ids);


        List<MajorInfo> all = majorService.findAll();

        model.addAttribute("major_list",all);


        if (row != 0){

            model.addAttribute("tip_info","执行专业删除成功");

            return "/jsp/admin/major/major_list.jsp";
        }else{
            model.addAttribute("tip_info","执行专业删除操作失败");

            return "/jsp/admin/major/major_list.jsp";

        }


    }



}
