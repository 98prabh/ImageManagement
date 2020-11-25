package com.assignment.functions;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;
import com.assignment.model.User;

public class LoginFunction {

	// Method to validate entered information by the user
    public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        boolean result;         // variable to validate user
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            transaction = session.beginTransaction();          // session started
            
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();           // Query to extract data from table 

            // Validating result
            if (user != null && user.getPassword().equals(password)) {
            	result = true;
            } else {
            	result = false;
            	System.out.println("UserName or Password is incorrect!");
            }
            
            // commit transaction
            transaction.commit();
        }// rollBack transaction if there is some error
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
        return result;
    }

}