package com.rick.springboot.controller;

import com.rick.springboot.dao.EmployeeDao;
import com.rick.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    //查询所有员工,返回列表页面
    @GetMapping(value = "/emps")
    public String list(Model model){
        Collection<Employee> employeeDaoAll = employeeDao.getAll();
        model.addAttribute("emps",employeeDaoAll);
        return "emp/list";
    }
}
