# PDF

https://blog.csdn.net/chengp919/article/details/78040823

```java

@KafkaListener(....)
public void listen1(String data)

@KafkaListener(....)
public void listen2(ConsumerRecord<K, V> data)

@KafkaListener(....)
public void listen3(ConsumerRecord<K, V> data,Acknowledgment acknowledgment)

@KafkaListener(....)
public void listen4(ConsumerRecord<K, V> data,
        Acknowledgment acknowledgment,Consumer<K, V> consumer)

@KafkaListener(....)
public void listen5(List<String> data)

@KafkaListener(....)
public void listen6(List<ConsumerRecord<K, V>>data)

@KafkaListener(....)
public void listen7(List<ConsumerRecord<K, V>>data,Acknowledgment acknowledgment)

@KafkaListener(....)
public void listen8(List<ConsumerRecord<K, V>>data,Acknowledgment acknowledgment,Consumer<K, V> consumer)

```