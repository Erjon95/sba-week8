package com.github.perscholas;

public class MainApplication {

    public static void main(String[] args) {
        
        JdbcConfigurator.useDB();
        try {
            JdbcConfigurator.initialize();
        }catch (java.io.IOException e)
        {
            System.out.println("IOException: Cannot read from file");
            e.printStackTrace();
            return;
        }
        Runnable sms = new SchoolManagementSystem(JdbcConfigurator.entityMgr);
        sms.run();
        JdbcConfigurator.entityMgr.clear();
    }
}
