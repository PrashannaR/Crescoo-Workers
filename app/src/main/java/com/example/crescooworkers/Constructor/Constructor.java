package com.example.crescooworkers.Constructor;

public class Constructor {
    String name, occupation, yearsOfExp, phone, pHour, pDay, age, gender;

    //empty constructor
    public Constructor(){

    }

    public Constructor(String name, String occupation, String yearsOfExp, String phone, String pHour, String pDay, String age, String gender) {
        this.name = name;
        this.occupation = occupation;
        this.yearsOfExp = yearsOfExp;
        this.phone = phone;
        this.pHour = pHour;
        this.pDay = pDay;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(String yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getpHour() {
        return pHour;
    }

    public void setpHour(String pHour) {
        this.pHour = pHour;
    }

    public String getpDay() {
        return pDay;
    }

    public void setpDay(String pDay) {
        this.pDay = pDay;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
