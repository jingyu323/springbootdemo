
pdf maven 依赖下载不下来后需要在maven setting.xml 中添加的

 <mirrors>

        <mirror>
            <id>pdf.free</id>
            <mirrorOf>*</mirrorOf>
            <name>pdf.free</name>
            <url>http://repo.e-iceblue.cn/repository/maven-public</url>
        </mirror>


    </mirrors>
