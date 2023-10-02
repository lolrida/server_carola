package com.example;

import java.io.*;
import java.net.*;

public class ServerStr{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi()
    {
        try
        {
            System.out.println("server partito in esecuzione...");
            server =new ServerSocket(6789);
            client =server.accept();
            server.close();
            inDalClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient=new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica()
    {
        try
        {
            System.out.println("benvenuto client, scrivi una frase e la trasformo in maiuscolo attendo...");
            stringaRicevuta=inDalClient.readLine();
            System.out.println("stringa ricevuta: "+stringaRicevuta);
            stringaModificata=stringaRicevuta.toUpperCase();
            System.out.println("invio la stringa modificata...");
            outVersoClient.writeBytes(stringaModificata+'\n');
            System.out.println("fine elaborazione...");
            client.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
