package com.ps.rest.controller;

import com.ps.rest.dto.PersonDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/validate")
public class ValidationController {
    @PostMapping("")
    public String check(@Valid @RequestBody PersonDto dto){
        return "OK";
    }
}
