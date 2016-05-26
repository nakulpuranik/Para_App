package com.app.dna.manager.Utils;

import android.util.Log;

import com.app.dna.manager.Employee.EmployeeModel;
import com.app.dna.manager.Transaction.TransactionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by swanand on 20/3/16.
 */

public class JSONParser {

    // Below method is used for parsing all employee details from JsonArray & stored into an ArrayList

    public static ArrayList<EmployeeModel> parseAllEmployee(JSONArray employeeInfoArray) {
        ArrayList<EmployeeModel> listAllEmployeeInfo = new ArrayList<>();

        try {

            for (int element = 0; element < employeeInfoArray.length(); element++) {

                JSONObject emp = employeeInfoArray.getJSONObject(element);
                EmployeeModel employee = new EmployeeModel();

                int empId = Integer.parseInt(emp.getString("emp_id"));
                employee.setEmpId(empId);

                String empName = emp.getString("emp_name");
                employee.setEmpName(empName);

                String empGender = emp.getString("emp_gender");
                employee.setEmpGender(empGender);

                String empDOB = emp.getString("emp_dob");
                employee.setEmpDob(empDOB);

                String empAddr = emp.getString("emp_addr");
                employee.setEmpAddr(empAddr);

                String empBloodGroup = emp.getString("emp_blood_group");
                employee.setEmpBloodGroup(empBloodGroup);

                String empContactNo = emp.getString("emp_contact_no");
                employee.setEmpContactNo(empContactNo);

                String empEmrgContactNo = emp.getString("emp_emergency_contact_no");
                employee.setEmpEmergencyContact_no(empEmrgContactNo);

                String empBankAccountNo = emp.getString("emp_bank_account_no");
                employee.setEmpBankAccountNo(empBankAccountNo);

                String empBankName = emp.getString("emp_bank_name");
                employee.setEmpBankName(empBankName);

                int empSalary = emp.getInt("emp_salary");
                employee.setEmpSalary(empSalary);

                int empStatus = emp.getInt("emp_status");
                employee.setEmpStatus(empStatus);

                String empEnrollmentDate = emp.getString("emp_enrollment_date");
                employee.setEmpEnrollmentDate(empEnrollmentDate);

                String empDateOfJoining = emp.getString("emp_date_of_joining");
                employee.setEmpDateOfJoining(empDateOfJoining);

                String empDateOfLeaving = emp.getString("emp_date_of_leaving");
                employee.setEmpDateOfLeaving(empDateOfLeaving);

/*                String empPhoto = emp.getString("emp_photo");
                employee.setEmpPhoto(empPhoto);

                String empIdproofPhoto = emp.getString("emp_idproof_photo");
                employee.setEmpIdproofPhoto(empIdproofPhoto);*/

                listAllEmployeeInfo.add(employee);

                Log.e("PARSER","LIst size:: "+ listAllEmployeeInfo.size() );

            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return listAllEmployeeInfo;
    }


    public static ArrayList<TransactionModel> parseUsersAllTransactions(JSONArray transactionInfoArray) {
        ArrayList<TransactionModel> listAllTransactionInfo = new ArrayList<>();

        try {

            for (int element = 0; element < transactionInfoArray.length(); element++) {

                JSONObject emp = transactionInfoArray.getJSONObject(element);
                TransactionModel transaction = new TransactionModel();

                int tran_id = Integer.parseInt(emp.getString("tran_id"));
                transaction.setTransId(tran_id);

                int tran_emp_id = emp.getInt("tran_emp_id");
                transaction.setTransEmpId(tran_emp_id);

                int tran_own_id = emp.getInt("tran_own_id");
                transaction.setTransOwnerId(tran_own_id);

                int tran_amt = emp.getInt("tran_amt");
                transaction.setAmount(tran_amt);

                String tran_description = emp.getString("tran_description");
                transaction.setTransDescription(tran_description);

                String tran_date = emp.getString("tran_date");
                transaction.setDate(tran_date);

                listAllTransactionInfo.add(transaction);

                Log.e("TRANS PARSER","LIst size:: "+ listAllTransactionInfo.size() );

            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return listAllTransactionInfo;
    }
}
