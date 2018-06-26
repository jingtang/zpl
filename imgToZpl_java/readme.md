#说明
ZPLConveter.java 图片转ZPL java算法

#集成谷歌zxing生成二维码  
加入zxing依赖  
<dependency>
	<groupId>com.google.zxing</groupId>
	<artifactId>core</artifactId>
	<version>3.3.2</version>
</dependency>  

QrCodeUtil.java  二维码工具类
LogoConfig.java 设置二维码中间的logo 
MatrixToImageWriter.java  生成图片工具类
EncodeTest.java 测试类
