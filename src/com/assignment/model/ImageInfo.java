package com.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

// Image Information table in ImageUtility Database
@Entity
@Table(name = "ImageInfo")     
public class ImageInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public final static long bytesInMb = 1024*1024*1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ImageId;
    
    // image name
    @Column(name = "Name")
    private String Name;
    
    // image size
    @Column(name = "Size")
    private float size;
    
    // userName
    @Column(name = "UserName")
    private String UserName;
    
    // JPA provides @Lob annotation to work with Large objects.
    @Lob
    private byte[] image;

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getSize() {
		return (float) (Math.round((size/bytesInMb) * 100.0) / 100.0);
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ImageInfo(String name, float size, String userName, byte[] image) {
		super();
		Name = name;
		this.size = size;
		UserName = userName;
		this.image = image;
	}

	public ImageInfo() {
	}
}
