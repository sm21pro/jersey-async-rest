package com.srikanth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.srikanth.util.JerseyAppConstants;

import javax.persistence.*;
import java.util.Objects;

@JsonPropertyOrder({"employeeId"})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = JerseyAppConstants.EMPLOYEE_WITH_USERNAME_PASSWORD_NQ,
                query = "SELECT emp from Employee emp " +
                        "where emp.username = :username and emp.password = :password")
})
public class Employee {

    @Id
    @Column(unique = true, nullable = false)
    private String employeeId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String emailID;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String securityQuestion;

    @Column(nullable = false)
    private String securityAnswer;

//    private Map<String, Object> extras = new HashMap<>();

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


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

//    @JsonAnyGetter
//    public Map<String, Object> getExtras() {
//        return extras;
//    }
//
//    @JsonAnySetter
//    public void setExtras(String key, Object value) {
//        this.extras.put(key, value);
//    }

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
