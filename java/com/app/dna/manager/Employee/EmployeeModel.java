package com.app.dna.manager.Employee;

/**
 * Created by dhawal on 6/2/16.
 */
public class EmployeeModel
{

    private int empId, empSalary, empStatus;
    private String empName=null, empDob=null, empAddr=null, empBloodGroup=null, empContactNo=null, empEmergencyContact_no=null;
    private String empBankAccountNo=null, empBankName=null;
    private String empEnrollmentDate=null,  empDateOfJoining=null, empDateOfLeaving=null;
    private String empPhoto=null, empIdproofPhoto=null;
    private String empGender = null;

    public EmployeeModel() {
    }

    public EmployeeModel(String name) {
        super();
        this.empName = name;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDob() {
        return empDob;
    }

    public void setEmpDob(String empDob) {
        this.empDob = empDob;
    }

    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    public String getEmpBloodGroup() {
        return empBloodGroup;
    }

    public void setEmpBloodGroup(String empBloodGroup) {
        this.empBloodGroup = empBloodGroup;
    }

    public String getEmpContactNo() {
        return empContactNo;
    }

    public void setEmpContactNo(String empContactNo) {
        this.empContactNo = empContactNo;
    }

    public String getEmpEmergencyContact_no() {
        return empEmergencyContact_no;
    }

    public void setEmpEmergencyContact_no(String empEmergencyContact_no) {
        this.empEmergencyContact_no = empEmergencyContact_no;
    }

    public String getEmpBankAccountNo() {
        return empBankAccountNo;
    }

    public void setEmpBankAccountNo(String empBankAccountNo) {
        this.empBankAccountNo = empBankAccountNo;
    }

    public String getEmpBankName() {
        return empBankName;
    }

    public void setEmpBankName(String empBankName) {
        this.empBankName = empBankName;
    }

    public String getEmpEnrollmentDate() {
        return empEnrollmentDate;
    }

    public void setEmpEnrollmentDate(String empEnrollmentDate) {
        this.empEnrollmentDate = empEnrollmentDate;
    }

    public String getEmpDateOfJoining() {
        return empDateOfJoining;
    }

    public void setEmpDateOfJoining(String empDateOfJoining) {
        this.empDateOfJoining = empDateOfJoining;
    }

    public String getEmpDateOfLeaving() {
        return empDateOfLeaving;
    }

    public void setEmpDateOfLeaving(String empDateOfLeaving) {
        this.empDateOfLeaving = empDateOfLeaving;
    }

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

    public String getEmpIdproofPhoto() {
        return empIdproofPhoto;
    }

    public void setEmpIdproofPhoto(String empIdproofPhoto) {
        this.empIdproofPhoto = empIdproofPhoto;
    }


    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }
}
