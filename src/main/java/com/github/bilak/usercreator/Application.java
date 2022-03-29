package com.github.bilak.usercreator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.bilak.usercreator.data.model.Command;
import com.github.bilak.usercreator.data.repository.UserRepository;
import com.github.bilak.usercreator.data.repository.UserRepositoryImpl;
import com.github.bilak.usercreator.service.UserCommandHandler;
import com.github.bilak.usercreator.service.UserCommandHandlerImpl;
import com.github.bilak.usercreator.service.UserProducer;
import com.github.bilak.usercreator.service.UserProducerImpl;

public class Application {

  public static void main(String[] args) throws InterruptedException {
    final Queue<Command> queue = new LinkedList<>();
    final UserProducer producer = new UserProducerImpl(queue);
    final UserRepository repository = new UserRepositoryImpl();
    final UserCommandHandler handler = new UserCommandHandlerImpl(queue, repository);

    ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);

    threadPoolExecutor.execute(producer);
    threadPoolExecutor.execute(handler);

    producer.add(1, "a1", "Robert");
    producer.add(2, "a2", "Martin");
    producer.printAll();
    producer.deleteAll();
    producer.printAll();


    threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
    threadPoolExecutor.shutdownNow();


  }
}
