/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.rxstub;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajan
 */
public class Rxstub {

    ServerSocket sock;
//server socket

    public Rxstub() {
        int date = 1;

        try {
            sock = new ServerSocket(6006);

            while (true) {
                try {
                    Socket ss = sock.accept();
                    DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
                    String fname = "D:\\BeaconData\\beacondata_LE_2023_09_" + String.format("%02d", date) + ".txt";
                    FileReader fr = new FileReader(fname);
                    BufferedReader br = new BufferedReader(fr);
                    int i = 0;
                    while (true) {
                        String readLine = br.readLine();
                        boolean fileend = false;
                        if (readLine == null) {
                            fileend = true;
                        } 

                        if (fileend) {
                            //go to next file
                            fname = "D:\\BeaconData\\beacondata_LE_2023_09_" + String.format("%02d", ++date) + ".txt";
                            fr = new FileReader(fname);
                            br = new BufferedReader(fr);
                            continue;
                        } else if (!"".equals(readLine)) {
                            byte[] bytes = readLine.getBytes();
                            dos.write(bytes);
                            dos.flush();
                            i = i + 1;
                            System.out.println("date="+date+"\tpack=" + i);
                        }
                        Thread.sleep(20);
                    }

                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(Rxstub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Rxstub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //read file
    //send one by one
    public static void main(String[] args) {
        System.out.println("Hello World!");
        new Rxstub();
    }
}
