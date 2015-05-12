package harry.tan.entity;

public class User{
	private String userId;
	private String  name;
	private String  sex;
    private String  information;
    
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getName() {
        return name;
    }



    public void setName(String pName) {
        name = pName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String pInformation) {
        information = pInformation;
    }

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
