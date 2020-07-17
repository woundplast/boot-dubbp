package com.boot.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.boot.dubbo.interfaces.model.Student;
import com.boot.dubbo.interfaces.service.StudentService;
import com.boot.dubbo.provider.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service  或者 @Component 把 service变成spring的一个bean
//@org.springframework.stereotype.Service
@Component
//服务提供者  dubbo注解 <dubbo:service >
@Service(version = "1.0.0") //版本号可带可不带  要统一
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*注入
    springboot 自动配置好的 RedisTemplate
        泛型 只允许  <Object,Object>  <String,String>
    *  */
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public String sayHi(String name) {
        return "Hi SpringBoot : " + name;
    }

    @Override
    public Student getUser(int id) {
        //查询数据库
        return studentMapper.findUserById(id);
    }

    /*
    * 集成 Redis
    * */
    @Override
    public List<Student> getAllStudent() {
        //字符串 序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        //高并发  缓存穿透  双层检测锁
        //查询缓存
        List<Student> studentList = (List<Student>) redisTemplate.opsForValue().get("allStudents");
        System.out.println("======  初次查询缓存 结果 ： ====== " + studentList);
        if(null == studentList){
//            synchronized (StudentServiceImpl.class){
            synchronized (this){
                studentList = (List<Student>) redisTemplate.opsForValue().get("allStudents");
                System.out.println("======  二次查询缓存 结果 ： ====== " + studentList);
                if(null == studentList){
                    System.out.println("======  ++二次查询缓存为空 查询数据库++  ====== " );
                    //缓存为空 查询数据库
                    studentList = studentMapper.selectAllStudent();
                    System.out.println("======  初次查询数据库 结果 ： ====== " + studentList);
                    //把从数据库 查出的数据放入缓存
                    redisTemplate.opsForValue().set("allStudents" , studentList);
                }else{
                    System.out.println("======  初次查询缓存不存在 - 二次查询缓存存在   ====== " );
                }
            }
//            System.out.println("======  缓存为空 查询数据库  ====== " );
//            //缓存为空 查询数据库
//            studentList = studentMapper.selectAllStudent();
//            System.out.println("======  初次查询数据库 结果 ： ====== " + studentList);
//            //把从数据库 查出的数据放入缓存
//            redisTemplate.opsForValue().set("allStudents" , studentList);
        }else{
            System.out.println("======  初次查询缓存存在   ====== " );
        }
        return studentList;
    }


}
