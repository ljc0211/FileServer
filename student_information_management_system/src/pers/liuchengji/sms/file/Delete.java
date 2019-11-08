package pers.liuchengji.sms.file;

import pers.liuchengji.sms.test.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

    //后续添补：获取当前路径。
    public static String path;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        del(request, response);
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取用户提交的名称。
        String name = request.getParameter("userName");
        name = path + "\\" + name;
        File file = new File(name);

        System.out.println(name);


        if (file.exists()) {
            if (file.isDirectory()) {
                FileUtils.deleteDirectory(file);
            } else {
                FileUtils.forceDelete(file);
            }
        }
        String message = null;

        if (file.exists()) {
            message = "删除失败!";
        } else {
            message = "删除成功!";
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
