package model.Data;

public class Data {
	private int id;
	private String email;
	private String path;
	private String FileName;

	public Data(int id, String email, String path, String fileName) {
		super();
		this.id = id;
		this.email = email;
		this.path = path;
		FileName = fileName;
	}

	public Data(int id, String path, String fileName) {
		super();
		this.id = id;
		this.path = path;
		FileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

}
