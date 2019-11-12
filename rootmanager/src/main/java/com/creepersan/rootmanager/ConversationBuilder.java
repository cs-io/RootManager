package com.creepersan.rootmanager;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class ConversationBuilder {
    private Runtime runtime;
    private LinkedList<String> cmdList;
    private ExecuteThread thread;

    ConversationBuilder(Runtime runtime){
        this.runtime = runtime;
        cmdList = new LinkedList<>();
        thread = new ExecuteThread();
        thread.start();
    }

    public void exec(String cmd){
        log("准备执行命令 "+cmd);
        cmdList.addLast(cmd);
    }

    public void close(){
        thread.interrupt();
        thread.stop();
        thread = null;
        cmdList.clear();
        cmdList = null;
        runtime.exit(0);
    }





    private class ExecuteThread extends Thread{

        @Override
        public void run() {
            super.run();



            while (true){
                if (!cmdList.isEmpty()){
                    String cmd = cmdList.removeFirst();
                    try {

                        byte[] buffer = new byte[4094];
                        int len = 0;

                        ProcessBuilder builder = new ProcessBuilder();
                        builder.directory(new File("/"));
                        builder.command(
                                "pwd",
                                "cd /sdcard",
                                "pwd"
                        );

                        Process process = builder.start();
                        InputStream inputStream = process.getInputStream();
                        OutputStream outputStream = process.getOutputStream();

                        StringBuilder resultBuilder = new StringBuilder();
                        do {
                            len = inputStream.read(buffer, 0, buffer.length);
                            if (len >= 0){
                                resultBuilder.append(new String(buffer, 0, len));
                            }
                        }while (len >= 0);

                        log("执行结果 "+resultBuilder.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }






    private void log(String message){
        Log.e("ConversationBuilder",message);
    }

}
