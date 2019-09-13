package pers.huangyuhui.sms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.huangyuhui.sms.dao.AdminDao;
import pers.huangyuhui.sms.model.AdminInfo;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static AdminDao adminDao = new AdminDao();

    public RegisterServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");
        System.out.println("执行了吗?");

        if ("register".equals(method)) {
            register(request, response);
        } else {
            return;
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取用户提交的登录表单信息
        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        /*
         * 验证用户登录
         */

        // 验证用户信息
        AdminInfo adminInfo = new AdminInfo();
        try {
            adminDao.setUserInfo(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 登录成功: 将用户信息存储到Sesssion
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", adminInfo);
        // 进入系统主页
        response.getWriter().write("registerSuccess");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
