/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servelet.LoginServlet;

import DAO.StaffDAO;
import Model.Member226;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Tuan Diep
 */
public class LoginController extends HttpServlet {
    
    private StaffDAO staffDAO;
    
    public LoginController(){
        staffDAO = new StaffDAO();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            if (staffDAO.isValidUser(username, password)) {
                int userId = staffDAO.isValidWarehouser(username, password);
                if (userId != 0) {
                    Member226 mem = staffDAO.getMemberById(userId);
                    if (mem != null) {

                        session.setAttribute("mem", mem);
                        request.setAttribute("mem", mem);
                        request.getRequestDispatcher("/Pages/gdMainWarehouser2226.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "Không tìm thấy thông tin người dùng.");
                        request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không hợp lệ.");
                    request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không hợp lệ.");
                request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member226 memb = (Member226) session.getAttribute("mem");
        if(memb != null)request.getRequestDispatcher("/Pages/gdMainWarehouser2226.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
