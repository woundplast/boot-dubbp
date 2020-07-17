package com.boot.dubbo.interfaces.service;

import com.boot.dubbo.interfaces.model.Student;

import java.util.List;

public interface StudentService {
    public String sayHi(String name);

    public Student getUser(int id);

    public List<Student> getAllStudent();
}
