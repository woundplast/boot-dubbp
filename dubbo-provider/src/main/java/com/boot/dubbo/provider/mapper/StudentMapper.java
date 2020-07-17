package com.boot.dubbo.provider.mapper;

import com.boot.dubbo.interfaces.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    Student findUserById(int id);

    List<Student> selectAllStudent();
}
