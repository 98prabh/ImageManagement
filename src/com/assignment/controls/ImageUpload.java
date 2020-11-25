package com.assignment.controls;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.assignment.functions.UploadNewImage;

public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ImageUpload() {
        super();
    }
    
   
    private UploadNewImage UI = new UploadNewImage() ;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Get an Image File from Http Request in type Part ,
		converted it in Input-stream and saved it to computer */

		Part picture = request.getPart("image");
		
		HttpSession session = request.getSession(); 
		
		// Get userName from the previous entered details
		String userName = (String) session.getAttribute("userName");
		String message;
		
		boolean uploadResult = UI.upload(picture, userName);
		
		// Getting Upload Status
		if (uploadResult) {
			message = UI.getValidationMessage();
			request.setAttribute("UploadResult", message);
        	request.getRequestDispatcher("/ImageUploadWindow.jsp").forward(request, response);
        	//System.out.println(message);

        } else {
			message = UI.getValidationMessage();
        	request.setAttribute("UploadResult", message);
        	request.getRequestDispatcher("/ImageUploadWindow.jsp").forward(request, response);
        	//System.out.println(message );
        }
			
	}
	
}
