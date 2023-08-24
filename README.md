# Online Energy Utility Platform Assignment 2

_Distributed Systems Assignment, Year 4, Semester 1, UTCN_

Additional features:

• Sensor Monitoring System and Real-Time Notification

• Sensor_Simulator (Java desktop application) -> Message Producer - simulates a sensor and sends data to the Message Broker

• Message Consumer -> pre-processes the data to compute the total hourly energy consumption, if the computed total hourly energy consumption 
exceeds the device maximum value it notifies asynchronously the user on his/her web interface


**_Technical specifications:_** 

• RabbitMQ for message broker

• WebSockets for async communication
