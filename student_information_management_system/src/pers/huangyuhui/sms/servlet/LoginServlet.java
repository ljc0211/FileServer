package pers.huangyuhui.sms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.huangyuhui.sms.dao.AdminDao;
import pers.huangyuhui.sms.model.AdminInfo;

/**
 * @ClassName: VerifyLoginServlet
 * @Description: 验证用户登录
 * @author: HuangYuhui
 * @date: May 6, 2019 10:19:15 PM
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static AdminDao adminDao = new AdminDao();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        if ("login".equals(method)) {
            login(request, response);
        } else if ("loginOut".equals(method)) {
            loginOut(request, response);
            return;
        }
    }

    /**
     * @throws IOException
     * @Title: login
     * @Description: 验证用户登录表单信息
     * @param: request
     * @param: response
     * @return: void
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取用户提交的登录表单信息
        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        /*
         * 验证用户登录
         */

        // 验证用户信息
        AdminInfo adminInfo = new AdminInfo();
        adminInfo = adminDao.getUserInfo(name, password);
        if (adminInfo == null) {
            response.getWriter().write("loginError");
            return;
        }
        // 登录成功: 将用户信息存储到Sesssion
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", adminInfo);
        // 进入系统主页
        response.getWriter().write("loginSuccess");

    }

    /**
     * @throws IOException
     * @Title: loginOut
     * @Description: 注销用户信息
     * @param: request
     * @param: response
     * @return: void
     */
    private void loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userInfo");

        try {
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
