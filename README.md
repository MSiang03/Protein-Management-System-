# Protein-Management-System-
X-Protein是一个可以管理蛋白质序列的界面。其主要功能有将用户提供的文件存进数据库、按页浏览收录的蛋白质、检索蛋白质序列、蛋白质相似度的计算。

**预下载包**

安装MySQL可到<a href='https://dev.mysql.com/downloads/' target='_blank' class='url'>https://dev.mysql.com/downloads/</a>下载。（确保电脑可以运行MySQL,可看教程）

**编译源程序**

下载源程序。
打开src/login_register/LoginForm.java，即可进行编译

**运行jar**

将下载好的jar任意放到一个目录下
点击路径，输入cmd
点击回车，直接跳到当前路径
输入 java -jar Protein_Dashboard.jar, 回车

**测试已实现功能**

<strong>X-Protein用户登入与注册</strong>

开始运行程序后会弹出Login窗口

<img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005339886.png" alt="image-20231226005339886" style="zoom: 67%;" />
新用户点击Register进行注册

image-20231226005402353
成为用户后，输入密码便可以登入了(输入错误会弹出窗口提示)

image-20231226004821588
2. 将用户提供的文件或输入解析后存进数据库
单击Import按钮

复制文件的路径（注意：文件必须是.txt文件，格式可以参见cafa3_partial_data.txt)

crtl+v 粘贴路径后，选择OK

弹出提示窗口（稍等片刻）

弹出success窗口说明成功将用户提供的文件输入解析后存进数据库

image-20231226005244894
按页浏览数据库收录的蛋白质信息

可以选择一次跳过几个数据

image-20231226005626732
单击Next按钮，可进入下一页

image-20231226005743472image-20231226005749030

按关键字检索数据库收录的序列

单击Search按钮，输入想要检索的序列

image-20231226010123625
点击OK后，检索结果将按id顺序列出

image-20231226010155025
蛋白质序列相似度计算

在Table上单击两个要检索的蛋白质/ 手动输入也可以

image-20231226010226026
单击Match按钮得到相似度结果

image-20231226010251837
