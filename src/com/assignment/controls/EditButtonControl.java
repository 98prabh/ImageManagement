// Class Contains Method to edit the Name of Image
package com.assignment.controls;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.functions.DatabaseFunctions;

public class EditButtonControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditButtonControl() {
        super();
    }
    
    // object of database function class
    private DatabaseFunctions db = new DatabaseFunctions();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// image id whose name to be changed
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		String newName = request.getParameter("newName");
		boolean result = db.UpdateName(imageId, newName);
		
		// Getting Update Status
		if(result) {
			
			response.sendRedirect("ImageUploadWindow.jsp");
		} else {
			System.out.println("Image Name not updated.. ");
		}
	}

}
