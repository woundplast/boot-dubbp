package com.boot.dubbo.consumer.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {
    //重写方法 快捷键 Ctrl + O
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MyServlet  Hello word  doGet");
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MyServlet  Hello word  doPost");
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
