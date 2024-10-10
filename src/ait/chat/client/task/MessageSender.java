package ait.chat.client.task;

import ait.chat.model.Message;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageSender implements Runnable {
    private Socket socket;

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your name");
            String name = br.readLine();
            System.out.println("Enter your message or type exit for quit");
            String text = br.readLine();
            while (!"exit".equalsIgnoreCase(text)) {
                Message message = new Message(name, text);
                oos.writeObject(message);
                text = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
