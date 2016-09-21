# 使用java 命令启动程序报"错误: 找不到或无法加载主类"

1.网上的方法已经很全面了[点这里](http://blog.chinaunix.net/uid-22002627-id-3455122.html)	
2.但我要说的是,在使用上面这些方法都没有效果的时候,就应该试试使用类的全限定名称启动
>`java com.thinkinjava.strings.TestRegularExpression`	

一定要在包的最外层执行 这样问题就解决啦
如果这个时候需要编译文件,就需要将点号改为文件分隔符
>`javac com\thinkinjava\strings\TestRegularExpression.java`	

搞定!
