/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 16cse121
 */
public class clt {
   public static void main(String  args[]) throws Exception{
      ServerSocket server=null;
      Socket socket;
      server = new ServerSocket(4000);
      System.out.println("Server Waiting for image");

      socket = server.accept();
      System.out.println("Client connected.");
      
      byte[] data;
        try (InputStream in = socket.getInputStream(); DataInputStream dis = new DataInputStream(in)) {
            int len = dis.readInt();
            System.out.println("Image Size: " + len/1024 + "KB");
            data = new byte[len];
            dis.readFully(data);
        }

      InputStream ian = new ByteArrayInputStream(data);
      BufferedImage bImage = ImageIO.read(ian);
 
      JFrame f = new JFrame("Server");
      ImageIcon icon = new ImageIcon(bImage);
      JLabel l = new JLabel();
      
      l.setIcon(icon);
      f.add(l);
      f.pack();
      f.setVisible(true);
   }
}

