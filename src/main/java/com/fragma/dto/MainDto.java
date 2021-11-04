package com.fragma.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainDto {

    static Logger LOG = LoggerFactory.getLogger(MainDto.class);

    public Map<Integer, DueBills> confirmedMap = new HashMap<>();
    public Map<Integer, DueBills> unconfirmedMap = new HashMap<>();
    

    public Set<String> maptoset = new LinkedHashSet<>();

    public LocalDate todayDate;

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public String subDate(){
        LocalDate yesturday=todayDate;
        String date = yesturday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return  date;

    }


    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public Map<Integer, DueBills> getConfirmedMap() {
        return confirmedMap;
    }



    public Set<String> getMaptoset() {
        return maptoset;
    }

    public Map<Integer, DueBills> getUnconfirmedMap() {
        return unconfirmedMap;
    }

    public String doubleToString(double amount)
    {
        if(Double.compare(amount,0.0D)==0)
        {
            return "0.00";
        }
        else {
            DecimalFormat df = new DecimalFormat("#,###.00"); // or pattern "###,###.##$"
            return df.format(amount);
        }
    }

    public String isNullOrEmpty(String  value)
    {
        if(value == null || value.isEmpty() || value.equals("\"\""))
        {
            return "-";
        }
        else
            return  value;
    }
    
    public String intToString(int count)
    {
        if(count==0)
        {
            return "0";
        }
        else {
            return String.valueOf(count);

        }
    }

    public void populateData(int SLNo, String contractRefNo, String operationCode, String extRefNo, String productCode, String cifId, String custName, String bcrefno, String maturityDate, String billCcy, String billAmt, String typeOfLc) {

        DueBills dueBills = confirmedMap.get(SLNo);

        if (dueBills == null) {
            dueBills = new DueBills();
        }

        dueBills.setContractRefNo(contractRefNo);
        dueBills.setOperationCode(operationCode);
        dueBills.setExtRefNo(extRefNo);
        dueBills.setProductCode(productCode);
        dueBills.setCifId(cifId);
        dueBills.setCustName(custName);
        dueBills.setBcrefno(bcrefno);
        dueBills.setMaturityDate(maturityDate);
        dueBills.setBillCcy(billCcy);
        dueBills.setBillAmt(billAmt);
        dueBills.setTypeOfLc(typeOfLc);

        confirmedMap.put(SLNo, dueBills);
    }

    public void populateUnconfirmedData(int SLNo, String contractRefNo, String operationCode, String extRefNo, String productCode, String cifId, String custName, String bcrefno, String maturityDate, String billCcy, String billAmt, String typeOfLc) {

        DueBills dueBills = unconfirmedMap.get(SLNo);

        if (dueBills == null) {
            dueBills = new DueBills();
        }

        dueBills.setContractRefNo(contractRefNo);
        dueBills.setOperationCode(operationCode);
        dueBills.setExtRefNo(extRefNo);
        dueBills.setProductCode(productCode);
        dueBills.setCifId(cifId);
        dueBills.setCustName(custName);
        dueBills.setBcrefno(bcrefno);
        dueBills.setMaturityDate(maturityDate);
        dueBills.setBillCcy(billCcy);
        dueBills.setBillAmt(billAmt);
        dueBills.setTypeOfLc(typeOfLc);

        unconfirmedMap.put(SLNo, dueBills);
    }
}
