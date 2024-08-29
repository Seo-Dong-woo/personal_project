package com.sdw.springsecuriy.controller;

import com.sdw.springsecuriy.dto.JoinDTO;
import com.sdw.springsecuriy.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private JoinService joinService;

    @Autowired
    public void JoinService(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @PostMapping("/joinPage")
    public String joinProcess(JoinDTO joinDTO) {
        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }
}
