# amq-client
Used for fast activeMQ msg sender and receiver, and activeMQ benchmark

#How to use it
Compile code to runnable jar or just use the generated jar directly.

0. You want help:
java -jar amq-client.jar -h
1. Fast msg sender:
java -jar amq-client.jar --fsend -s __yourServerAddr__ -q __yourQueueName__ -d __data__