package com.xw.controller;

import com.xw.dto.Result;
import com.xw.dto.SimpleSpecificationBuilder;
import com.xw.dto.SpecificationOperator;
import com.xw.entiry.City;
import com.xw.entiry.User;
import com.xw.service.ICityService;
import com.xw.service.IUserService;
import com.xw.util.CaptchaUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@Slf4j
public class UserController extends BaseController {


    @Autowired
    private IUserService userService;
    @Autowired
    private ICityService cityService;

    /**
     * 显示注册
     *
     * @return
     */
    @RequestMapping("/showRegister")
    public String showRegister(Model model) {
        List<City> list = cityService.findAll();
        System.out.println("list = " + list);
        model.addAttribute("lists",list);
        return "register";
    }

    /**
     * 注册请求
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Result<Void> register(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userService.save(user);
        return new Result<Void>(1, "注册成功,3秒后自动回到登录页面");
    }

    /**
     *显示登录
     *
     * @return
     */
    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }


    /**
     * 登录请求
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result<Void> login(String name, String password) {
        Result<Void> result = new Result<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            subject.login(token);
            result.setCode(1);
            result.setMsg("登录成功");
        } catch (AuthenticationException e) {
          
            return result.error(0, "用户名或密码错误");
        } catch (Exception e) {
         
            return result.error(0, "网络异常");
        }

        return result;
    }


    /**
     * 根据用户名查询
     *
     * @param name
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Boolean checkName(String name) {
       
        boolean result = true;
        if (name != null) {
            User user = userService.findByName(name);
            if (user != null) {
                result = false;
            }
        }
        return result;

    }

    /**
     * 主页
     *
     * @param model
     * @return
     */
    @RequestMapping("/showIndex")
    public String index(Model model) {
        return "index";
    }


    /**
     * 主页模糊查询
     */
    @RequestMapping("/findIndex")
    @ResponseBody
    public Page<User> findIndex(User user) {
        SimpleSpecificationBuilder<User> builder = new SimpleSpecificationBuilder<>();
        if (StringUtils.isNotBlank(user.getName())) {
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), user.getName());
        }
        Page<User> page = userService.findAll(builder.generateSpecification(), getPageRequest());
        return page;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result<Void> deleteUser(Integer id){
        try{
            userService.delete(id);
        } catch (Exception e){
            return new Result<Void>(0,"删除失败");
        }
        return new Result<>(1,"删除成功");
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        CaptchaUtil.outputCaptcha(request, response);
    }

}
