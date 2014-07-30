#selenium-spoon-plugin#
=====================

Maven plugin for generating screenshot report when running selenium case.  

参考Android-Spoon插件，改写成selenium插件。  

使用方式：  
在你的selenium项目的pom中添加下面的依赖：   

		<dependency>
        	<groupId>com.yeetrack.selenium</groupId>
        	<artifactId>spoon</artifactId>
        	<version>1.0</version>
    	</dependency>

添加plugin：  

		<plugin>
            <groupId>com.yeetrack.selenium</groupId>
            <artifactId>spoon</artifactId>
            <version>1.0</version>
            <executions>
                <execution>
                    <id>spoon report</id>
                    <phase>post-integration-test</phase>
                    <goals>
                        <goal>spoon</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

在截图时，不要再使用selenium提供的方法，要使用插件提供的方法：   

	DriverCapture.snapShot("这里输入图片描述", driver);

执行：

	mvn clean install

截图存放路径：**target/screenshots**  
html报告路径： **target/screenshotResult/report**

