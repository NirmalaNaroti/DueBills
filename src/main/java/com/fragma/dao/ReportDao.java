package com.fragma.dao;

import com.fragma.config.ConfigurationHelper;
import com.fragma.dto.MainDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.*;

@Repository
public class ReportDao {

    static Logger LOG = LoggerFactory.getLogger(ReportDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final ConfigurationHelper configurationHelper;
    int SLNo=0;
    int uSLNo=0;

    @Autowired
    public ReportDao(@Qualifier("hiveJdbcTemplate") JdbcTemplate jdbcTemplate, ConfigurationHelper configurationHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.configurationHelper = configurationHelper;

    }

    public void getData(MainDto mainDto){
        LOG.info("***** executing getData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String contractRefNo = isNullOrEmpty(resultSet.getString("contract_ref_no"));
                String operationCode = isNullOrEmpty(resultSet.getString("operation_code"));
                String extRefNo = isNullOrEmpty(resultSet.getString("ext_ref_no"));
                String productCode = isNullOrEmpty(resultSet.getString("product_code"));
                String cifId = isNullOrEmpty(resultSet.getString("cif_id"));
                String custName = isNullOrEmpty(resultSet.getString("cust_name"));
                String bcrefno = isNullOrEmpty(resultSet.getString("bcrefno"));
                String maturityDate = isNullOrEmpty(resultSet.getString("maturity_date"));
                String billCcy = isNullOrEmpty(resultSet.getString("bill_ccy"));
                String billAmt = isNullOrEmpty(resultSet.getString("bill_amt"));
                String typeOfLc = isNullOrEmpty(resultSet.getString("Type_of_LC"));

               LOG.info("contractRefNo:"+contractRefNo+"operationCode:"+operationCode+"extRefNo:"+extRefNo+"productCode:"+productCode+"cifId:"+cifId+"custName:"+custName+"maturityDate"+maturityDate+"bcrefno"+bcrefno+"billCcy"+billCcy+"billAmt"+billAmt+"typeOfLc"+typeOfLc);

                mainDto.populateData(++SLNo,contractRefNo,operationCode,extRefNo,productCode,cifId,custName,bcrefno,maturityDate,billCcy,billAmt,typeOfLc);
            }
        });
    }

    public void getUnConfirmedData(MainDto mainDto){
        LOG.info("***** executing getUnConfirmedData *****");
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                LOG.info("Query = "+ ConfigurationHelper.getUnconfirmedQuery() );
                PreparedStatement ps = connection.prepareStatement(ConfigurationHelper.getUnconfirmedQuery());

                return ps;
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                String contractRefNo = isNullOrEmpty(resultSet.getString("contract_ref_no"));
                String operationCode = isNullOrEmpty(resultSet.getString("operation_code"));
                String extRefNo = isNullOrEmpty(resultSet.getString("ext_ref_no"));
                String productCode = isNullOrEmpty(resultSet.getString("product_code"));
                String cifId = isNullOrEmpty(resultSet.getString("cif_id"));
                String custName = isNullOrEmpty(resultSet.getString("cust_name"));
                String bcrefno = isNullOrEmpty(resultSet.getString("bcrefno"));
                String maturityDate = isNullOrEmpty(resultSet.getString("maturity_date"));
                String billCcy = isNullOrEmpty(resultSet.getString("bill_ccy"));
                String billAmt = isNullOrEmpty(resultSet.getString("bill_amt"));
                String typeOfLc = isNullOrEmpty(resultSet.getString("Type_of_LC"));

                LOG.info("contractRefNo:"+contractRefNo+"operationCode:"+operationCode+"extRefNo:"+extRefNo+"productCode:"+productCode+"cifId:"+cifId+"custName:"+custName+"maturityDate"+maturityDate+"bcrefno"+bcrefno+"billCcy"+billCcy+"billAmt"+billAmt+"typeOfLc"+typeOfLc);

                mainDto.populateUnconfirmedData(++uSLNo,contractRefNo,operationCode,extRefNo,productCode,cifId,custName,bcrefno,maturityDate,billCcy,billAmt,typeOfLc);
            }
        });
    }


    public String isNullOrEmpty(String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("\"\"")) {
            return " ";
        } else
            return value;
    }

}
