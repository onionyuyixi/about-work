package com.example.aboutwork.controller;


import com.example.aboutwork.copy.AClass;
import com.example.aboutwork.copy.BClass;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/copy/")
public class CopyController {



    @GetMapping("test")
    public Object test() {
        AClass onion = new AClass(1, "onion", 23);
        BClass bClass = new BClass();
        BeanUtils.copyProperties(onion,bClass);
        return bClass;
    }

}
