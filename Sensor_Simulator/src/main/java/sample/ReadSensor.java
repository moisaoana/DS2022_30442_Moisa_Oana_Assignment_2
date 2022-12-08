package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

public class ReadSensor extends TimerTask {

    public int deviceId;

    private String indexFilePath;
    private String sensorFilePath = "./src/main/resources/sensor.csv";

    public ReadSensor(int deviceId){
        this.deviceId = deviceId;
        this.indexFilePath = "./src/main/resources/"+deviceId+".txt";
    }

    @Override
    public void run() {
        System.out.println("Method called at: "
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                ZoneId.systemDefault()));
        System.out.println(indexFilePath);
        int index = 0 ;
        try{
            Scanner scannerIndexFile = new Scanner(new File(indexFilePath));
            index = Integer.parseInt(scannerIndexFile.next());
            System.out.println("Index "+index);
        }catch(FileNotFoundException fileNotFoundException){
           // fileNotFoundException.printStackTrace();
            try {
                File file = new File(indexFilePath);
                BufferedWriter writer = new BufferedWriter(new FileWriter(indexFilePath));
                writer.write(Integer.toString(0));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int counter = 0;
        Scanner scannerSensorFile = null;
        try {
            scannerSensorFile = new Scanner(new File(sensorFilePath));
            while (scannerSensorFile.hasNextLine())
            {
                if(counter==index) {
                    //System.out.print("Value: "+scannerSensorFile.nextLine()+"\n");
                    String timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                            ZoneId.systemDefault()).toString();
                    double value = Double.parseDouble(scannerSensorFile.nextLine());
                    Measurement measurement = new Measurement(timestamp,value,deviceId);
                    System.out.println("timestamp: "+ timestamp);
                    System.out.println("value: "+value);
                    System.out.println("deviceId: "+deviceId);
                    Sender sender = new Sender();
                    sender.sendToQueue(measurement);
                    counter++;
                    break;
                }else{
                    scannerSensorFile.nextLine();
                }
                counter++;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(indexFilePath));
            writer.write(Integer.toString(counter));
            writer.close();
            scannerSensorFile.close();
        } catch (IOException | TimeoutException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}

