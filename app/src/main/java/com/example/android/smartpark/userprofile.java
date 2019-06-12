package com.example.android.smartpark;

public class userprofile {
    public String id;
    public String name;
    public String age;
    public String gender;
    public String email;
    public String mobile;
    public userprofile()
    {

    }
    public userprofile(String id,String name,String email)
    {   this.id=id;
        this.name=name;
        this.email=email;
        this.gender=this.mobile=this.age="";
    }
    public void setprofile(String name,String email,String age,String mobile,String gender)
    {
        this.name=name;
        this.age=age;
        this.email=email;
        this.gender=gender;
        this.mobile=mobile;
    }
    public String getname()
    {
       return this.name;
    }
    public String getemail()
    {return this.email;}

    public String getGender()
    {
        return this.gender;
    }
    public String getmobile()
    {return this.mobile;}
    public String getage(){return this.age;}

}
