package main.java;

import java.io.Serializable;

/**
 * @author Jerry Binder
 * Class containing properties of an individual file.
 * Serializable for future config storage purposes.
 */
public class BackupFile implements Serializable {
	private static final long serialVersionUID = -1211074823532941165L;
	private String path;
	private String filename;
	private String md5Hash;
	
	BackupFile(String path, String filename, String md5Hash){
		this.path = path;
		this.filename = filename;
		this.md5Hash = md5Hash;
	}
	
	BackupFile(String path, String filename){
		// TODO: use HashFileUtil to generate MD5 hash
		// md5Hash = HashFileUtil.getInstance().generateMd5Hash(path + filename);
		// this(path, filename, md5Hash);
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMd5Hash() {
		return md5Hash;
	}
	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
}
