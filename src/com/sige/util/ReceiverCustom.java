package com.sige.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;

public class ReceiverCustom implements Receiver {

	private static final long serialVersionUID = 1L;

	private String fileName;
	private String mtype;
	private String fileLocation;

	public OutputStream receiveUpload(String filename, String mimetype) {
		setMtype(mimetype);

		try {
			File destination = new File(fileLocation + this.fileName);
			return new FileOutputStream(destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
