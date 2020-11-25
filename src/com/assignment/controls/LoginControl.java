package com.assignment.controls;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment.functions.LoginFunction;


public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginFunction login;           // object of login class having validate method 
       
    
    public LoginControl() {
        super();
    }
    
    public void init() {
    	login = new LoginFunction();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("LoginPage.jsp");    //Will open loginpage.jsp file 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        try {
		        	userauthentication(request, response);       // calling userauthentication method
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	// Will check if the entered details are correct or not 
	
    private void userauthentication(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();  
        
        // check user authentication
        if (login.validate(username, password)) {
        	try {
        		session.setAttribute("userName",username); 
        		
                // Will open Imageupoad.jsp file if entered details are correct
	            RequestDispatcher dispatcher = request.getRequestDispatcher("ImageUploadWindow.jsp");
	            dispatcher.forward(request, response);
	            
	            
        	} catch (Exception e) {
        		System.out.println("Login Failed");
        	}
        } else {
        	
    		// Navigate to Login Page with error message
    		request.setAttribute("LoginResult","Invalid Username or Password");
    		
    		// Will open the login page again if entered details are not correct
    		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginPage.jsp");
            dispatcher.forward(request, response);
            
            // Message on Console
            System.out.println("Login failed");
            
        }
	 }
}
