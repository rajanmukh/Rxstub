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

        try {
            sock = new ServerSocket(6006);
            String fname = "beacondata_LE_2023_07_07.txt";
            while (true) {
                try {
                    Socket ss = sock.accept();
                    DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
                    FileReader fr = new FileReader(fname);
                    BufferedReader br = new BufferedReader(fr);
                    String readLine = "";
                    int i = 0;
                    while (readLine != null) {
                        readLine = br.readLine();
                        if (!"".equals(readLine)) {
                            byte[] bytes = readLine.getBytes();
                            dos.write(bytes);
                            dos.flush();
                            i = i + 1;
                            System.out.println("pack=" + i);
                        }
                        Thread.sleep(40);
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
