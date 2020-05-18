package ro.pub.cs.systems.eim.practicaltest02.views.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.views.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02.views.general.Utilities;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String currencyType;
    private TextView currencyTextView;

    private Socket socket;

    public ClientThread(String address, int port, String currencyType, TextView currencyTextView) {
        this.address = address;
        this.port = port;
        this.currencyType = currencyType;
        this.currencyTextView = currencyTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(currencyType);
            printWriter.flush();
            String currencyInformation;
            while ((currencyInformation = bufferedReader.readLine()) != null) {
                final String finalizedCurrencyInformation = currencyInformation;
                currencyTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        currencyTextView.setText(finalizedCurrencyInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}

