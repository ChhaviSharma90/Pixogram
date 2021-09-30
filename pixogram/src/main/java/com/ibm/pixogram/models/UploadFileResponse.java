package com.ibm.pixogram.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "allfilesdetails")
public class UploadFileResponse {
	private String description;
	private String title;
	private String tags;
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String username;
    private String createdDate;
    private String createdTime;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size,String description,String title,String tags,String username,String createdDate,String createdTime) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.description=description;
        this.title=title;
        this.tags=tags;
        this.username=username;
        this.createdDate=createdDate;
        this.createdTime=createdTime;
    }


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
    
}
