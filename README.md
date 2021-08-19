**WELCOME MAIL SERVICE**

_Foreword_

    该项目实现每天早上9.30给当天生日的员工发送邮件，支持读取json格式的文件，解析后将员工信息存入数据库，每天凌晨一点自动检查数据信息是否完整。

_Introduction_

1、jdk版本最低支持1.8，idea开发需要安装lombok插件。

2、springboot版本为2.5.3，mybatis版本3.5.7。

3、运行项目前请先在application-dev.yml中配置好数据库信息和邮箱信息。

4、确保mysql环境及mysql服务已启动。

5、运行项目。

_TODO_

1、服务器宕机 错过当天9点30发邮件，导致少发，目前的思路是每天早上9.30分过后，每隔半小时检查当天应发邮件但sendResult=0的用户。

2、同一个员工发送失败次数failureCount>=3的处理。

3、统一异常处理。

4、其他数据源加载引擎实现。

5、代码优化。

