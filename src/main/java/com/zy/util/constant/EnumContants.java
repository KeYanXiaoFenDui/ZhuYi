package com.zy.util.constant;

/**
 * methodName open-service-api
 * Created by yes_tao on 2017/12/23.16:59
 */
public class EnumContants {
    public static final String [] BROKER_ENTRY_STATUS = {"0","1","2"};
    public static final String [] BROKER_LEAVE_STATUS = {"3","4","5"};

    public static final String [] AUDIT_TYPE = {"entry","leave"};

    public static final String [] RECORD_TYPE = {"red","black","customer"};

    public static final String [] SEXS = {"男","女"};

    public static final String [] ADD_BROKER_ROWSNAME  = new String[]{"姓名","性别","证件类型","证件号码","所属门店","入职时间","信息卡号"};

    public static final String [] UPDATE_BROKER_ROWSNAME = new String[]{"姓名","证件号码","所属门店","入职时间"};

    public static final String [] LEAVE_BROKER_ROWSNAME = new String[]{"姓名","证件号码","离职时间"};

    public static final String [] ERROR_BROKER_ROWSNAME = new String[]{"姓名","证件号码","失败原因"};
}
