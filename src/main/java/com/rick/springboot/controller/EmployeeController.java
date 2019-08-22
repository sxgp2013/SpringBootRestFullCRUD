package com.rick.springboot.controller;

import com.rick.springboot.dao.DepartmentDao;
import com.rick.springboot.dao.EmployeeDao;
import com.rick.springboot.entities.Department;
import com.rick.springboot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger("EmployeeController");

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工,返回列表页面
    @GetMapping(value = "/emps")
    public String list(Model model) {
        Collection<Employee> employeeDaoAll = employeeDao.getAll();
        model.addAttribute("emps", employeeDaoAll);
        return "emp/list";
    }

    //跳转到添加页面,需要查出所有的部门
    @GetMapping(value = "/emp")
    public String toAddpage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping(value = "/emp")
    public String addEmp(Employee employee){
        //此处stringboot会自动从页面获取employee参数，前提是提交的内容与employee的参数名一致
        //此处用请求重定向：发送两次请求
        //添加完了后重新跳转到/emps页面
        logger.info(employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //跳转到修改页面，结果是我们跳转到add页面二合一修改
    //还需要回闲数据，已经部门信息
    @GetMapping(value = "/emp/{id}")
    public String getUpdateEmpPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        //回显所有部门信息，以供修改使用
        model.addAttribute("depts",departments);
        //回显的需要编辑的员工信息
        model.addAttribute("emp",employee);
        //跳转到add页面二合一修改
        return "emp/add";
    }

    //需要提交员工id
    @PutMapping(value = "/emp")
    public String updateEmp(Employee employee){
        logger.info(employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";

    }

    //删除员工的controller
    @DeleteMapping(value = "/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        Employee emp = employeeDao.get(id);
        logger.info("删除用户：--->"+emp.toString());
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
