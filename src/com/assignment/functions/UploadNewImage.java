package com.assignment.functions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.assignment.hibernateutil.HibernateUtil;
import com.assignment.model.ImageInfo;

public class UploadNewImage {

	String sizeValidationResult;
	public final static long bytesInMb = 1024*1024*1;
	public final static long ImageSize = 1024*1024*1;
	public final static long totalImageSize = 1024*1024*10;

	public boolean upload(Part picture, String userName) throws IOException {
		Transaction transaction = null;
		InputStream inp = null;
		boolean uploadResult;
		ImageInfo imageInfo = new ImageInfo();

		if(picture != null) {
			
			// check for file size(0 or not)
			if (picture.getSize() != 0) {
				inp = picture.getInputStream();
				// Creating a byte array to store image
				byte[] byt = new byte[inp.available()];
				inp.read(byt);
				imageInfo.setName(picture.getSubmittedFileName());
				imageInfo.setImage(byt);

				if (validateSize(userName, picture.getSize())) {

					imageInfo.setSize(picture.getSize());
					imageInfo.setUserName(userName);
					try (Session session = HibernateUtil.getSessionFactory().openSession()) {
						// start a transaction
						transaction = session.beginTransaction(); 
						
						//Saving object
						session.save(imageInfo);    
						session.getTransaction().commit();

						sizeValidationResult = "Image Uploaded Successfully!";
						uploadResult = true;

					} catch (Exception e) {
						if (transaction != null) {
							transaction.rollback();
						}
						System.out.println("Error in session");
						uploadResult = false;
					}
				} else {
					uploadResult = false;
				}
			}else {
				sizeValidationResult = "No Image is Selected!";
				uploadResult = false;
			}
		} else {
			sizeValidationResult = "Something Wrong!";
			uploadResult = false;
		}
		return uploadResult;
	}

	@SuppressWarnings("unchecked")
	public boolean validateSize(String userName, long sizeOfImage) {
		Transaction transaction = null;
		Optional<Double> totalSize;
		boolean validationResult;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction(); 

			totalSize = (Optional<Double>) session.createQuery("SELECT SUM(size) FROM ImageInfo WHERE UserName = : userName")
					.setParameter("userName", userName).uniqueResultOptional();   //Save the data

			// Validating size
			if (totalSize.isPresent()) {
				// Validating size of Image to be uploaded
				if (sizeOfImage <= ImageSize) {
					// Validating total size
					if (totalSize.get() + sizeOfImage <= totalImageSize) {
						validationResult = true;         			
					}else {
						sizeValidationResult = "Total Size of All Images will exceed 10 MB";
						System.out.println("Total Size of All Images will exceed 10 MB");
						validationResult = false;
					}

				}else {
					sizeValidationResult = "Image Size more than 1 MB";
					System.out.println("Image Size more than 1 MB");
					validationResult = false;
				}
			} else {
				validationResult = true;
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			//System.out.println("Error in session");
			validationResult = false;
		}

		return validationResult;
	}
	
	/*
	 * Return Upload Status
	 */
	public String getValidationMessage() {
		return this.sizeValidationResult;
	}

}
