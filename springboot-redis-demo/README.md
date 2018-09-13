redis学习
    1.redisTemplate
    spring封装了RedisTemplate对象来进行对Redis的各种操作，它支持所有的Redis原生的api
    redisTemplate.opsForValue();//操作字符串
    redisTemplate.opsForHash();//操作hash
    redisTemplate.opsForList();//操作list
    redisTemplate.opsForSet();//操作set
    redisTemplate.opsForZSet();//操作有序set

    public interface ValueOperations<K,V>
    Redis operations for simple (or in Redis terminology 'string') values.
    ValueOperations可以对String数据结构进行操作：

    ValueOperations<K,V> operations=redisTemplate.opsForValue();

    1.set void set(K key, V value);

    使用：redisTemplate.opsForValue().set("name","tom");
    结果：redisTemplate.opsForValue().get("name")  输出结果为tom

    2.set void set(K key, V value, long timeout, TimeUnit unit);

    使用：redisTemplate.opsForValue().set("name","tom",10, TimeUnit.SECONDS);
    结果：redisTemplate.opsForValue().get("name")由于设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null

    3.set void set(K key, V value, long offset);
        该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始

    使用：template.opsForValue().set("key","hello world");
            template.opsForValue().set("key","redis", 6);
            System.out.println("***************"+template.opsForValue().get("key"));
    结果：***************hello redis

    4.setIfAbsent Boolean setIfAbsent(K key, V value);

    使用：System.out.println(template.opsForValue().setIfAbsent("multi1","multi1"));//false  multi1之前已经存在
            System.out.println(template.opsForValue().setIfAbsent("multi111","multi111"));//true  multi111之前不存在
    结果：false
    true

    5.multiSet void multiSet(Map<? extends K, ? extends V> m);
        为多个键分别设置它们的值

    使用：Map<String,String> maps = new HashMap<String, String>();
            maps.put("multi1","multi1");
            maps.put("multi2","multi2");
            maps.put("multi3","multi3");
            template.opsForValue().multiSet(maps);
            List<String> keys = new ArrayList<String>();
            keys.add("multi1");
            keys.add("multi2");
            keys.add("multi3");
            System.out.println(template.opsForValue().multiGet(keys));
    结果：[multi1, multi2, multi3]


