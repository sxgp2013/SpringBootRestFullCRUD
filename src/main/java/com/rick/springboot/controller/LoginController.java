package com.rick.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){

        //添加session用于存储用户名密码校验
        //@RequestParam("username") String username,
        // 意思是从request域的请求参数获取username这个参数，注意区别@PathVaviable(""),这个是从请求路径获取参数
        // 然后转化成现在的String username，没有获取到的话就会报错


        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功,为了防止进入页面后刷新继续提交表单数据，此处用重定向
            //将登陆成功的用户名加入session中用于拦截器登录校验
            session.setAttribute("loginUser",username);

            return "redirect:/main.html";
        }else{
            //登录失败返回登录页面
            map.put("msg","登录请求密码错误！");
            return "index";
    //此拦截器需要在实现了WebMvcConfigurer的配置类中注册
        }

    }
}
