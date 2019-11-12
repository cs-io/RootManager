package com.creepersan.rootmanager;

import java.io.IOException;

public class RootManager {
    private static volatile RootManager mInstance = null;

    public static RootManager getInstance(){
        if (mInstance == null){
            synchronized (RootManager.class){
                if (mInstance == null){
                    mInstance = new RootManager();
                }
            }
        }
        return mInstance;
    }

    private RootManager(){

    }

    public boolean checkHasRootPermission(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("su");
            int resultCode = process.waitFor();
            return resultCode == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Conversation createConversation(){
        return new Conversation(Runtime.getRuntime());
    }


}
