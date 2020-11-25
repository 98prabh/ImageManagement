//Class Contains method to delete image
package com.assignment.controls;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.functions.DatabaseFunctions;

public class DeleteButtonControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteButtonControl() {
        super();
    }
   
    DatabaseFunctions db = new DatabaseFunctions();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Id of image to be deleted
		int imageId = Integer.parseInt(request.getParameter("id"));
		// Method call to delete function
		boolean result = db.delete(imageId);
		
		if (result) {
			// will open same window after deleting image 
			response.sendRedirect("ImageUploadWindow.jsp");
		} else {
			System.out.println("Image not deleted");
		}
		
	}	

}
