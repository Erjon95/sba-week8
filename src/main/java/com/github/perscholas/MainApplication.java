package com.github.perscholas;

public class MainApplication {

    public static void main(String[] args) {
        JdbcConfigurator.useDB();
        JdbcConfigurator.test();
        Runnable sms = new SchoolManagementSystem(JdbcConfigurator.entityMgr);
        sms.run();
        JdbcConfigurator.entityMgr.clear();
    }
}
