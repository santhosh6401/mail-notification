package com.rewards.spinthewheel.controller;

import com.rewards.spinthewheel.model.response.HistoryResponse;
import com.rewards.spinthewheel.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class VIewController {

    @Autowired
    private HistoryService historyService;

    @GetMapping({"/list", "/"})
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("index");
        List<HistoryResponse> historyResponses = historyService.getAll();
        Collections.reverse(historyResponses);
        System.out.println(historyResponses);
        mav.addObject("values", historyResponses);
        return mav;
    }
}
