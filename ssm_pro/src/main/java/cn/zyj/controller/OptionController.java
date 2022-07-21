package cn.zyj.controller;

import cn.zyj.bean.*;
import cn.zyj.service.OptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/option")
public class OptionController {
    
    
    @Autowired
    OptionService optionService;



    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<OptionInfo> option_list=optionService.findAll();
        model.addAttribute("option_list",option_list);
        return "/jsp/admin/option/option_list.jsp";
    }

    @RequestMapping("/findByPage")
    public String findByPage(@RequestParam(defaultValue = "1")int pageNum, Model model){

        int pageSize = 8;//定义每页显示几条条数据

        PageHelper.startPage(pageNum,pageSize);

        List<OptionInfo> all = optionService.findAll();

        PageInfo<OptionInfo> pageInfo = new PageInfo<>(all);

        model.addAttribute("option_list",all);
        model.addAttribute("pageInfo",pageInfo);

        return "/jsp/admin/option/option_list.jsp";

    }


    //跳转到新增页面
    @RequestMapping("/add_page")
    public String addPage(){

        return "/jsp/admin/option/option_add.jsp";

    }


    //跳转到编辑页面
    @RequestMapping("/update_page")
    public String updatePage(int id,Model model){

        OptionInfo optionInfo = optionService.selectById(id);

        model.addAttribute("option_info",optionInfo);

        return "/jsp/admin/option/option_edit.jsp";

    }

    //执行选项编辑
    @RequestMapping("/option_add")
    public String optionAdd(OptionInfo optionInfo, Model model, HttpSession session){

        optionInfo.setCreateTime(new Date());

        AdminInfo adminInfo = (AdminInfo)session.getAttribute("login_admin");
        optionInfo.setOperator(adminInfo.getId());

        int row = optionService.addOption(optionInfo);

        if (row != 0){
            model.addAttribute("tip_info","恭喜你，执行选项添加成功");
            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，执行选项添加失败");
            return "/result.jsp";
        }

    }

    //执行选项编辑
    @RequestMapping("/update_save")
    public String updateSave(OptionInfo optionInfo, Model model, HttpSession session){

        optionInfo.setOperateTime(new Date());

        AdminInfo adminInfo = (AdminInfo)session.getAttribute("login_admin");
        optionInfo.setOperator(adminInfo.getId());

        int row = optionService.update(optionInfo);

        if (row != 0){
            model.addAttribute("tip_info","恭喜你，执行选项修改成功");
            return "/result.jsp";
        }else{
            model.addAttribute("tip_info","很抱歉，执行选项修改失败");
            return "/result.jsp";
        }

    }



}
