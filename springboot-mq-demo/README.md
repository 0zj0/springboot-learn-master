rabbitmq 生产者

1.安装启动rabbitmq服务：
    安装教程自行网上寻找，安装好后，启动sbin中的rabbitmq-server.bat，访问127.0.0.1:15672并登录
    
2.第一步：创建User；
  第二步：创建Virtual Host,并建立user与Virtual Host的联系；
  第三步：创建ExChange;
  第四步：创建Queue,建立和Exchange的关联关系；
  
3.通过交换机分发消息到路由有4种（常用）的Exchange Types类型
direct：直连
    direct类型的Exchange路由规则也很简单，它会把消息路由到那些binding key与routing key完全匹配的Queue中；
    在direct.DirectSend测试中发现，当创建ExChange选择类型为fanout（预计其他非direct类型一样）时，消息直接发送到了交换机下所有的队列中；
未通过指定路由发送到指定队列

fanout：广播
    它会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中
    在fanout.FanoutSend测试中发现，当创建ExChange选择类型为direct（预计其他非fanout类型一样）是，队列接受不到消息

topic: 匹配
    topic类型的Exchange在匹配规则上进行了扩展，它与direct类型的Exchage相似，也是将消息路由到binding key与routing key相匹配的Queue中，但这里的匹配规则有些不同，它约定：
    routing key为一个句点号“. ”分隔的字符串（我们将被句点号“. ”分隔开的每一段独立的字符串称为一个单词），如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”
    即在topic模式中，在设置routing_key 是，可以用通配符匹配，在代码中用详细路由访问，若路径匹配成功及会向指定队列（可能多个）发送消息；

headers：头消息匹配
    headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。
    在绑定Queue与Exchange时指定一组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），
对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。
     

延时队列：
利用TTL DLX 实现延时队列
1.创建死信队列
dead-test-queue    //队列-死信队列
dead-test-exchange    //交换机-死信队列
dead-test-key    //死信队列

2.创建业务mq
queue-test-dead-1       //队列-延时队列-发送
exchange-test-dead-1    //交换机-延时队列-发送
key-test-dead-1
其中:queue绑定设置
x-dead-letter-exchange:dead-test-exchange
x-dead-letter-routing-key:dead-test-key

3.mq产生：
exchange-test-dead-1，key-test-dead-1 发送mq消息，需设置有效期
firstRabbitTemplate.convertAndSend(DEAD_EXCHANGE,DEAD_ROUT,"发送direct模式消息-延时队列-路由：key-test-dead-1",
        message -> {message.getMessageProperties().setExpiration(5*1000 + "");
            System.out.println(System.currentTimeMillis());
        return message;});

4.mq消费：
设置dead-test-queue的消费者，
延时队列queue-test-dead-1 不用消费，在有效期内未消费，进入死信队列,在死信队列执行操作，如此实现延时队列