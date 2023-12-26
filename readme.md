X-Protein是一个可以管理蛋白质序列的界面。其主要功能有将用户提供的文件存进数据库、按页浏览收录的蛋白质、检索蛋白质序列、蛋白质相似度的计算。

**预下载包**

安装MySQL可到https://dev.mysql.com/downloads/下载。（确保电脑可以运行MySQL,可看教程）

**编译源程序**

1. 下载源程序。
2. 打开`src/login_register/LoginForm.java`，即可进行编译

**运行jar**

1. 将下载好的jar任意放到一个目录下
2. 点击路径，输入`cmd`
3. 点击回车，直接跳到当前路径
4. 输入 `java -jar Protein_Dashboard.jar`, 回车

**测试已实现功能**

- **X-Protein用户登入与注册**

1. 开始运行程序后会弹出Login窗口

   <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005339886.png" alt="image-20231226005339886" style="zoom: 67%;" />

2. 新用户点击Register进行注册

   <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005402353.png" alt="image-20231226005402353" style="zoom: 67%;" />

3. 成为用户后，输入密码便可以登入了(输入错误会弹出窗口提示)

   <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226004821588.png" alt="image-20231226004821588" style="zoom:50%;" />

- **将用户提供的文件或输入解析后存进数据库**

1. 单击Import按钮

2. 复制文件的路径（注意：文件必须是.txt文件，格式可以参见cafa3_partial_data.txt)

3. crtl+v 粘贴路径后，选择OK

4. 弹出提示窗口（稍等片刻）

5. 弹出success窗口说明成功将用户提供的文件输入解析后存进数据库

   <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005244894.png" alt="image-20231226005244894" style="zoom: 67%;" />

- **按页浏览数据库收录的蛋白质信息**

  1. 可以选择一次跳过几个数据

     <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005626732.png" alt="image-20231226005626732" style="zoom: 80%;" />

  2. 单击Next按钮，可进入下一页

     <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005743472.png" alt="image-20231226005743472" style="zoom:67%;" /><img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226005749030.png" alt="image-20231226005749030" style="zoom:67%;" />

- 按关键字检索数据库收录的序列

  1. 单击Search按钮，输入想要检索的序列

     <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226010123625.png" alt="image-20231226010123625" style="zoom:80%;" />

  2. 点击OK后，检索结果将按id顺序列出

     <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226010155025.png" alt="image-20231226010155025" style="zoom:67%;" />

- 蛋白质序列相似度计算

  1. 在Table上单击两个要检索的蛋白质/ 手动输入也可以

     <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226010226026.png" alt="image-20231226010226026" style="zoom:67%;" />



2. 单击Match按钮得到相似度结果

   <img src="C:\Users\User\AppData\Roaming\Typora\typora-user-images\image-20231226010251837.png" alt="image-20231226010251837" style="zoom:67%;" />