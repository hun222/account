package com.hoonyeee.android.account.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "account")
public class Account {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    public int bankIdx;
    @DatabaseField
    public String bankName;
    @DatabaseField
    public String accNo;

    public Account(){

    }

    public Account(int bankIdx, String bankName, String accNo) {
        this.bankIdx = bankIdx;
        this.bankName = bankName;
        this.accNo = accNo;
    }
}
