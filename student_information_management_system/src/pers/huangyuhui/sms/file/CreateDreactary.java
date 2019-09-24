package pers.huangyuhui.sms.file;

import pers.huangyuhui.sms.test.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet("/CreateDreactary")
public class CreateDreactary extends HttpServlet {

    //后续添补：获取当前路径。
    public static String path;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        directory(request, response);
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取用户提交的名称。
        String name = request.getParameter("userName");
        name = path + "\\" + name;
        File file = new File(name);

        if (file.exists()) file.delete();
        String message = null;

        if (file.exists()) {
            message = "删除失败!";
        } else {
            message = "删除成功!";
        }

        request.setAttribute("message",message);
        request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
    }

    public void directory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取用户提交的新建目录名。
        String name = request.getParameter("userName");
        name = path + "\\" + name;
        File file = new File(name);

        if (!file.exists() && !file.isDirectory()) {
            //创建目录
            file.mkdir();
        }
        String message = null;

        if (!file.exists() || !file.isDirectory()) {
            message = "创建目录失败!";
        } else {
            message = "创建目录成功!";
        }

        request.setAttribute("message",message);
        request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        path = new Test().getPathTemp();
        System.out.println("Upload:" + path);
        doGet(request, response);
    }
}
