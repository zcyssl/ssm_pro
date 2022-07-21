package cn.zyj.controller;

import cn.zyj.bean.PaperInfo;
import cn.zyj.bean.PieInfo;
import cn.zyj.bean.StudentInfo;
import cn.zyj.bean.TotalScore;
import cn.zyj.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartsController {

    @Autowired
    ChartsService chartsService;

    @RequestMapping("/score_chart")
    @ResponseBody
    public List<TotalScore> total(){

        return chartsService.totalScore();

    }

    @RequestMapping("/student_chart")
    @ResponseBody
    public List<StudentInfo> StudentChart(){

        return chartsService.findAll();

    }

    @RequestMapping("/count_chart")
    @ResponseBody
    public List<PieInfo> CountChart(){

        return chartsService.selectAll();

    }


}
