package com.hoonyeee.android.account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoonyeee.android.account.domain.Account;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

public class DBConnect extends OrmLiteSqliteOpenHelper{
    private static final String databaseName ="account.db";
    private static final int databaseVersion = 1;

    public DBConnect(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Account.class);
            Account account = new Account();
            account.accNo = "312-0038-7739-51";
            account.bankIdx = 4;
            account.bankName = "농협";
            accDao.create(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    // Account 테이블에 접근하기 위한 변수
    private Dao<Account, Integer> accDao = null;
    public Dao<Account, Integer> getAccountDao() throws Exception{    // DB오류시 호출한 곳으로 예외전달
        if(accDao == null)
            accDao = getDao(Account.class);
        return accDao;
    }

    public void insert(int bankIdx, String bankName, String accNo) throws Exception{
        Account memo = new Account(bankIdx, bankName, accNo);
        Dao<Account, Integer> accountDao = getAccountDao();
        accountDao.create(memo); // insert
    }

    public List<Account> selectAll() throws Exception{
        List<Account> datas = null;

        Dao<Account, Integer> accountDao = getAccountDao();
        datas = accountDao.queryForAll();

        return datas;
    }

    public void update(Account memo) throws Exception{
        Dao<Account, Integer> accountDao = getAccountDao();
        accountDao.update(memo);
    }

    public void delete(int id) throws Exception{
        Dao<Account, Integer> accountDao = getAccountDao();
        accountDao.deleteById(id);
    }
}
