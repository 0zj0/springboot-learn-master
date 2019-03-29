#一次简单的springboot+quartz 实现定时功能
## 步骤1：添加相关maven包
        <!-- quartz  -->
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz-jobs</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.0.6.RELEASE</version>
            <scope>compile</scope>
        </dependency>
        
        <!- c3p0的连接池 ->
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
       
 ## 步骤2:添加应用配置文件
    # ===========================================================================
    # Configure Main Scheduler Properties 调度器属性
    # ===========================================================================
    #调度标识名 集群中每一个实例都必须使用相同的名称
    org.quartz.scheduler.instanceName=MyScheduler
    #ID设置为自动获取 每一个必须不同
    org.quartz.scheduler.instanceId=AUTO
    
    #============================================================================
    # Configure ThreadPool  线程池配置
    #============================================================================
    #线程池的实现类（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
    org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool
    #指定线程数，至少为1（无默认值）(一般设置为1-100直接的整数合适)
    org.quartz.threadPool.threadCount=10
    #设置线程的优先级（最大为java.lang.Thread.MAX_PRIORITY 10，最小为Thread.MIN_PRIORITY 1，默认为5）
    org.quartz.threadPool.threadPriority=5
    
    #============================================================================
    # Configure JobStore
    #============================================================================
    # 信息保存时间 默认值60秒
    org.quartz.jobStore.misfireThreshold=60000
    #数据保存方式为数据库持久化
    org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX
    #数据库代理类，一般org.quartz.impl.jdbcjobstore.StdJDBCDelegate可以满足大部分数据库
    org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.StdJDBCDelegate
    #JobDataMaps是否都为String类型
    org.quartz.jobStore.useProperties=false
    #数据库别名 随便取(通过别名设置链接失败，暂不支持此种配置)
    #org.quartz.jobStore.dataSource=myDs
    #表的前缀，默认QRTZ_
    org.quartz.jobStore.tablePrefix=QRTZ_
    #是否加入集群
    org.quartz.jobStore.isClustered=true
    #调度实例失效的检查时间间隔
    org.quartz.jobStore.clusterCheckinInterval=20000
    
 ## 步骤3：
    一般情况下，quartz的job中使用autowired注解注入的对象为空，这时候我们就要使用spring-quartz提供的AdaptableJobFactory类
    @Component
    public class QuartzJobFactory extends AdaptableJobFactory {
        //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;
        
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            //调用父类的方法
            Object jobInstance = super.createJobInstance(bundle);
            //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

    定义job类
    public class TestOneJob implements Job {
    
        private static Logger logger = LoggerFactory.getLogger(TestOneJob.class);
    
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            logger.info("TestOneJob.begin...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("TestOneJob.end...");
        }
    }
    
    定义job调度类
    @Configuration
    @EnableAutoConfiguration
    public class TestOneTriggerConfig {
    
        public static final String TRIGGER_NAME="test1_trigger";
        public static final String TRIGGER_GROUP="test1_trigger_group";
        public static final String JOB_NAME="test1_job";
        public static final String JOB_GROUP="test1_group";
        public static final String JOB_CRON="0 0/1 * * * ? ";
    
        @Bean(name = "test1Trigger")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public CronTriggerFactoryBean cronTriggerFactoryBean(){
            CronTriggerFactoryBean cronTriggerFactoryBean=new CronTriggerFactoryBean();
            cronTriggerFactoryBean.setName(TRIGGER_NAME);
            cronTriggerFactoryBean.setGroup(TRIGGER_GROUP);
            cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
            cronTriggerFactoryBean.setCronExpression(JOB_CRON);
            return cronTriggerFactoryBean;
        }
    
        @Bean(name = "test1Detail")
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public JobDetailFactoryBean jobDetailFactoryBean(){
            JobDetailFactoryBean jobDetailFactoryBean=new JobDetailFactoryBean();
            jobDetailFactoryBean.setName(JOB_NAME);
            jobDetailFactoryBean.setGroup(JOB_GROUP);
            jobDetailFactoryBean.setJobClass(TestOneJob.class);
            jobDetailFactoryBean.setDurability(true);//必须设置为true，如果为false，当没有活动的触发器与之关联时会在调度器中会删除该任务
            return jobDetailFactoryBean;
        }
    }
    
    添加任务配置类
    @Configuration
    @EnableAutoConfiguration
    public class QuartzConfig {
    
        @Autowired
        private QuartzJobFactory quartzJobFactory;
    
        //@Autowired
        //private TestOneTriggerConfig testOneTriggerConfig;
    
        @Autowired
        private TestTwoTriggerConfig testTwoTriggerConfig;
    
        @Bean
        public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
            SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
            schedulerFactoryBean.setDataSource(quartzDataSource());
            //使job实例支持spring 容器管理
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
            schedulerFactoryBean.setJobFactory(quartzJobFactory);
            schedulerFactoryBean.setQuartzProperties(properties());
            // 延迟10s启动quartz
            schedulerFactoryBean.setStartupDelay(10);
            schedulerFactoryBean.setTriggers(
                    //testOneTriggerConfig.cronTriggerFactoryBean().getObject(),
                    testTwoTriggerConfig.cronTriggerFactoryBean().getObject()
            );
            return schedulerFactoryBean;
        }
    
        @Bean
        public Properties properties() throws IOException {
            Properties prop = new Properties();
            prop.load(new ClassPathResource("/quartz.properties").getInputStream());
            return prop;
        }
    
        //quartz使用的是c3p0的连接池
        public ComboPooledDataSource quartzDataSource() throws Exception{
            ComboPooledDataSource myDs=new ComboPooledDataSource();
            myDs.setJdbcUrl("jdbc:mysql://192.168.4.98:3306/quartz_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
            myDs.setDriverClass("com.mysql.jdbc.Driver");
            myDs.setUser("root");
            myDs.setPassword("123456");
            myDs.setMaxPoolSize(5);
            return myDs;
        }
    }
    
    项目启动类添加任务调度注解
    @EnableScheduling
    
    job类添加@DisallowConcurrentExecution，标识quartz任务执行完之后再执行下一轮任务
    
 ##总结发现
    quartz添加定时任务成功后，会在数据库中添加相关数据。若同一项目在不同端口号下启动，
    建立伪集群，会发现在同一时刻仅有一台服务器调用指定job方法
    在方法调用时会在相关数据表中进行操作；