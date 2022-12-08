package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    public void sendToQueue(Measurement measurement) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("rabbitmq_queue", false, false, false, null);
        String json = new ObjectMapper().writeValueAsString(measurement);
        channel.basicPublish("", "rabbitmq_queue", null, json.getBytes());
        channel.close();
        connection.close();
    }
}
