package harry.tan.entity;

import java.util.Date;

public class User implements Person{
    private String  name;
    private Integer age;
    private String  information;
    private String  address;
    private Date    birthDay;


    public Date getBirthDay() {
		return birthDay;
	}



	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}



	public String getAddress() {
        return address;
    }



    public void setAddress(String pAddress,Integer p) {
        address = pAddress;
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



    public Integer getAge() {
        return age;
    }



    public void setAge(Integer pAge) {
        age = pAge;
    }



	@Override
	public void sys() {
		System.out.println("SB");
		
	}
}
