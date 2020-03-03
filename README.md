# 全国职业院校技能大赛2019智能家居安装与维护项目整理文件
<p align="center">
	<a href="http://www.chinaskills-jsw.org/"><img src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3275224511,3035196832&fm=26&gp=0.jpg" width="200"></a>
</p>

<p align="center">
	<strong>非官方,仅我个人整理的文件!!!</strong>
</p>

<p align="center">
	<a target="_blank" href="https://github.com/Yilimmilk/QiXiang_SmartHome_Android/blob/master/LICENSE">
		<img src="https://img.shields.io/github/license/Yilimmilk/QiXiang_SmartHome_Android?colorB=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8-green.svg" ></img>
	</a>
	<a target="_blank" href='https://github.com/Yilimmilk/QiXiang_SmartHome_Android'>
		<img src="https://img.shields.io/github/repo-size/Yilimmilk/QiXiang_SmartHome_Android?colorB=red" alt="repo size"></img>
	</a>
	<a target="_blank" href='https://github.com/Yilimmilk/QiXiang_SmartHome_Android'>
		<img src="https://img.shields.io/github/last-commit/Yilimmilk/QiXiang_SmartHome_Android" alt="last commit"></img>
	</a>
	<a target="_blank" href='https://github.com/Yilimmilk/QiXiang_SmartHome_Android'>
		<img src="https://img.shields.io/github/stars/Yilimmilk/QiXiang_SmartHome_Android" alt="github star"></img>
	</a>
	<a target="_blank" href='https://github.com/Yilimmilk/QiXiang_SmartHome_Android'>
		<img src="https://img.shields.io/github/forks/Yilimmilk/QiXiang_SmartHome_Android" alt="github fork"></img>
	</a>
</p>

## 📝简介
- 我是参加了2019全国职校技能大赛智能家居项目的其中一名选手，最终获得了国赛三等奖的成绩。
- 这个repo是我整理的所有我用到的文件，包括题库，说明文档，企想提供的Demo项目文件，我自己写的项目以及我参考过的项目，还有2017届的题目，题库以及Demo。
- 2019年抽取的是[E卷](https://github.com/Yilimmilk/QiXiang_SmartHome/blob/master/2019%E9%A2%98%E5%BA%93/E%E5%8D%B7.docx?raw=true)作为最终试题。
- 由于现在高三，准备参加技能高考了，所以可能这个repo整理的有点仓促，但是该写的我都写了，应该还是能看的。

## ✏️部分重要代码
> <u>千万不要太过于在意代码规范，毕竟就三个小时，时间会不够用的！(除非你觉得还有多的时间)</u>
> <u>比赛中，部分重要代码记得写注释，听说可以加分。</u>

- 滑动验证
> 部分题目中要求滑动验证，可以直接使用SeekBar，通过设置`android:progressDrawable`属性来设置背景资源，通过`android:thumb`属性来设置拖动滑块的资源；甚至还有一套题需要滑动拼图验证，也可以使用这种方法，通过`android:padding`来设置内边距，就可以让滑块到指定的位置，例子：
```xml
         <SeekBar
            android:id="@+id/sb_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" 
            android:minHeight="40dp"
            android:minWidth="40dp"
            android:paddingTop="15dp"
            android:layout_marginLeft="5dp"
            android:layout_marginRight="5dp"
            android:progressDrawable="@drawable/custom_progress"
            android:thumb="@drawable/a"/>
```

- 进度条加载
> 进度条加载是个假的，只需要达到加载动画的效果就可以了，要新开一个线程用来计数，在主线程中计数会导致UI界面卡死：
```java
new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 101; i++) {
					
					Message msg=new Message();
					msg.what=i;
					mHandler.sendMessage(msg);
					
					mainBar.setProgress(i);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
```

## 📖相关说明

+ 在这两个目录下：【[2017年智能家居安卓Demo](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2017%E6%99%BA%E8%83%BD%E5%AE%B6%E5%B1%85%E5%AE%89%E5%8D%93Demo "2017年智能家居Demo")，[2019年智能家居安卓Demo](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019%E6%99%BA%E8%83%BD%E5%AE%B6%E5%B1%85%E5%AE%89%E5%8D%93Demo "2019年智能家居Demo")】是企想提供的安卓Demo文件，提供最基础的智能家居控制功能，可直接导入eclipse。

+ 在这两个目录下：【[2017年题库](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2017%E9%A2%98%E5%BA%93 "2017年题库")，[2019年题库](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019%E9%A2%98%E5%BA%93 "2019年题库")】是题库文件，在正式比赛时会由比赛组委会从中随机抽取一套，作为比赛试题，2017年我不知道抽取了哪一套，2019年抽取的是[E卷](https://github.com/Yilimmilk/QiXiang_SmartHome/blob/master/2019%E9%A2%98%E5%BA%93/E%E5%8D%B7.docx?raw=true)作为最终试题，我感觉不算特别难，就是时间不咋够。。。

+ 在这个目录下：[2019年自己写的项目文件](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E8%87%AA%E5%B7%B1%E5%86%99%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6)是我自己写的项目(有几个项目没找着，等开学了去学校电脑上再找找)，要是嫌弃我的代码很辣鸡也能理解哈哈哈，项目名字对应的就是那一套题，基本完全按照试题要求的来，可直接导入eclipse。

+ 在这个目录下：[2019年我参考过的项目文件](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E5%8F%AF%E4%BE%9B%E5%8F%82%E8%80%83%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6)是我参考过的项目文件，解释一下每个项目大概是什么内容吧，可直接导入eclipse。
	+ [Login_Sqlite](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E5%8F%AF%E4%BE%9B%E5%8F%82%E8%80%83%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6/Login_Sqlite)是sqlite数据库的相关使用，主要被我用于实现账号登录，数据记录的相关功能。
	+ [A-J套试题参考Demo](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E5%8F%AF%E4%BE%9B%E5%8F%82%E8%80%83%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6)在这个目录下的DemoA-DemoJ项目是当时我们的参考项目Demo，实现了绝大部分试题要求的功能（听说是找人做的），我当时主要参考了Fragment的使用，生命周期管理，Canvas的绘制等等，这些项目都完整的实现了试题要求的效果，但最大的问题就是，这些项目并不能正常控制智能家居设备，也就是说，做这套题的人应该并没有实体的设备，仅从理论上做出了这些项目，但这些项目对我帮助也算是很大的了，感谢。
	+ [绘制折线图](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E5%8F%AF%E4%BE%9B%E5%8F%82%E8%80%83%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6/%E7%BB%98%E5%88%B6%E6%8A%98%E7%BA%BF%E5%9B%BE)顾名思义，这个项目是我用来实现折线图绘制的参考。

+ [其他文件](https://github.com/Yilimmilk/QiXiang_SmartHome/tree/master/%E5%85%B6%E4%BB%96%E7%9B%B8%E5%85%B3%E6%96%87%E4%BB%B6)这个是其他的一些文件，比如说明文档啥的，还有lib库文件。

## 📱联系方式

Email: miaococoo@gmail.com

| QQ：2510355993  | 
| :--------:   |
| <img src="https://raw.githubusercontent.com/Yilimmilk/QiXiang_SmartHome_Android/master/other_src/qr_qq.jpg" alt="qq：qr" width="260px" />  | 

## 🔔最后
*好了，也再没有什么好说的了，希望这个repo可以被用上吧，最后祝以后参加这个比赛的同学可以取得一个理想的成绩咯，干巴爹！*
