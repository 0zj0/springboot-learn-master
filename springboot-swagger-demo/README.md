Swagger 是一款RESTFUL接口的文档在线自动生成+功能测试功能软件

使用
1.在pom里添加
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.8.0</version>
        </dependency>

 2.添加swagger配置文件
   见 com.example.demo.config.Swagger2文件
  @EnableSwagger2
  @Configuration
  public class Swagger2 {
      @Bean
      public Docket createRestApi() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .apiInfo(apiInfo())
                  .select()
                  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                  //.apis(RequestHandlerSelectors.basePackage("eumji.eumji.swagger.controller"))   这是扫描注解的配置，即你的API接口位置。
                  .paths(PathSelectors.any())
                  .build();
      }
      private ApiInfo apiInfo() {
          return new ApiInfoBuilder().title("swagger学习测试api")
                  .description("在线调试，快速开发")
                  .version("1.0")
                  .build();
      }
  }
 3.若在配置文件中设置apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    所有添加了@ApiOperation(value="添加用户", notes="添加用户信息")的可以被扫描到swagger列表中

 4.若在配置文件中设置apis(RequestHandlerSelectors.basePackage("eumji.eumji.swagger.controller"))
    在这个目录下的所有接口都会被扫描到swagger列表中

 5.访问swagger方式：http://localhost:8092/swagger-ui.html

 6.swagger中可以通过@ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")配置参数，
 也可以在实体类中进行配置，属性设置
    @ApiModelProperty(value="商户订单号",required = true)
    private String outTradeNo;



 一、相关注解解读
 1. @Api

 用在类上，说明该类的作用
 @Api(value = "UserController", description = "用户相关api")
 2. @ApiOperation

 用在方法上，说明方法的作用
 @ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 3 @ApiImplicitParams

 用在方法上包含一组参数说明
 4. @ApiImplicitParam

 用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 paramType：参数放在哪个地方
 header–>请求参数的获取：@RequestHeader
 query–>请求参数的获取：@RequestParam
 path（用于restful接口）–>请求参数的获取：@PathVariable
 body（不常用）
 form（不常用）
 name：参数名
 dataType：参数类型
 required：参数是否必须传
 value：参数的意思
 defaultValue：参数的默认值

 @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"), })
 5. @ApiResponses

 用于表示一组响应

 6. @ApiResponse

 用在@ApiResponses中，一般用于表达一个错误的响应信息
 code：数字，例如400
 message：信息，例如”请求参数没填好”
 response：抛出异常的类

 @ApiResponses(value = {
           @ApiResponse(code = 400, message = "No Name Provided")
   })
 7. @ApiModel
 描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 @ApiModel(value = "用户实体类")

 8. @ApiModelProperty
 描述一个model的属性
 @ApiModelProperty(value = "登录用户")
