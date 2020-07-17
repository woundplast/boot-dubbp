package com.boot.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.boot.dubbo.interfaces.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("student")
public class StudentController {

    @Reference(version = "1.0.0")  // <dubbo:reference id="" ...>
    private StudentService studentService;

    @RequestMapping("getStudent/{id}")
    public String getStudent(Model model,@PathVariable("id") int id){
        System.out.println("===== into  ======  " +  id);
        System.out.println(studentService.getUser(id));
        model.addAttribute("student",studentService.getUser(id));
        model.addAttribute("msg","集成jsp");
        return "student";
    }

    @RequestMapping("sayHi/{name}")
    public @ResponseBody String sayHi(@PathVariable("name") String name){
        System.out.println("===== sayHi  ======  " + name );
        return "sayHi : " + name;
    }

    @RequestMapping("getAllStudent")
    public String getAllStudent(Model model){
//        多线程 测试 高并发  缓存穿透
//        创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(25);
//      线程 用于调用底层查询所有学生的方法
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                studentService.getAllStudent();
            }
        };
        for (int i = 0; i < 10000; i++) {
            executorService.submit(runnable);
        }


        model.addAttribute("student",studentService.getAllStudent());
        model.addAttribute("msg","查询所有学生对象");

        return "student";
    }
}
