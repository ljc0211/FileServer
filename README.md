# WEB_FILES
	学习JavaWeb操作。更改自https://github.com/ZS1994/WEB_FILES.git

2019_9_1:
	它可以在你的服务器上搭建一个共享云盘，比较简单，目前也没有上传功能，只能下载。

		配置：
			1、运行于JDK8以上。
			2、服务器改用apache-tomcat-9.0.24（如有改动请修改Test.java中的private final String tomcatName="apache-tomcat-9.0.24";）
			3、已改为部署于Ubuntu 18.04 Lts，请注意Test.java中注释部分改动。
			4、修改了空目录下无法返回上一级的Bug。
