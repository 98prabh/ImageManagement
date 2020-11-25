package com.assignment.functions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;
import com.assignment.model.ImageInfo;

public class DatabaseFunctions {

// Update function to change name of the image
 public boolean UpdateName (int imageId, String newName) {
	Transaction transaction = null;
	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				
				transaction = session.beginTransaction();
	            int result = session.createQuery("update ImageInfo set Name = :n where ImageId = :i")
	            			.setParameter("n", newName)
	            			.setParameter("i", imageId).executeUpdate();
	            //System.out.println(result);
	            transaction.commit();
			 } catch (Exception e) {
				 if (transaction != null) {
		                transaction.rollback();
		            }
		            return false;
		     }
			return true;
		}

	//Fetch data from database of the user entered 
 
 private List<ImageInfo> imageInfoList = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public List<ImageInfo> fetch (String userName) {
		System.out.println(userName);
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
         // get a user object
         imageInfoList = session.createQuery("FROM ImageInfo U WHERE U.UserName = :userName").setParameter("userName", userName).getResultList();
         System.out.println(imageInfoList.size());
         transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 System.out.println("Failed while fetching");
	     }
		return imageInfoList;
	}
	
	
// Method to delete image from database	
private int delete;
public boolean delete (int imageId) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			delete = session.createQuery("DELETE FROM ImageInfo I WHERE I.ImageId = :imageId").setParameter("imageId", imageId).executeUpdate();
			
            transaction.commit();
		 } catch (Exception e) {
			 System.out.println("Image not deleted");
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 return false;
	     }
		return true;
	}
}
