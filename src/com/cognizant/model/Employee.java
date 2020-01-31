package com.cognizant.model;

public class Employee {

    private long employeeId;
    private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private long contactNumber;
    private String password;
    private long salary;
    private String designation;
    private String permanentAddress;
    private String correspondenceAddress;
    private char loginType;
    private char active;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public char getLoginType() {
        return loginType;
    }

    public void setLoginType(char loginType) {
        this.loginType = loginType;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (employeeId != other.employeeId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", userName=" + userName + ", firstName="
                + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
                + ", contactNumber=" + contactNumber + ", password=" + password + ", salary="
                + salary + ", designation=" + designation + ", permanentAddress=" + permanentAddress
                + ", correspondenceAddress=" + correspondenceAddress + ", loginType=" + loginType
                + ", active=" + active + "]";
    }

}
