package com.srikanth.model;

import java.util.Objects;

public class Employee {

    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    private String username;
    private String password;
    private String fullName;
    private String emailID;
    private String dateOfBirth;
    private String gender;
    private String securityQuestion;
    private String securityAnswer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(fullName, employee.fullName) &&
                Objects.equals(emailID, employee.emailID) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(securityQuestion, employee.securityQuestion) &&
                Objects.equals(securityAnswer, employee.securityAnswer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, username, password, fullName, emailID, dateOfBirth, gender, securityQuestion, securityAnswer);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "xxxxxx" + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailID='" + emailID + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
}
