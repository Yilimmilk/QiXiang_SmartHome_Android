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
> </br><u>比赛中，部分重要代码记得写注释，听说可以加分。</u>

- #### IP连接并登陆
> 在Eclipse中导入企想提供的jar包，或直接将jar文件拖到项目的libs目录中，包名为com.bizideal.smarthome
> 包结构:
>
> |-- BuildConfig.class
> |-- socket
> 	|-- Utils
> 		|--SpUtils.class
> 	|-- ConstantUtil.class
> 	|-- ControlUtils.class
> 	|-- DataCallback.class
> 	|-- DeviceBean.class
> 	|-- LoginCallback.class
> 	|-- SocketClient.class
>
> ​	**SpUtils**是一个sharedPreferences的操作类，可以直接用于存储数据，但是不要当作数据库使用！可以用于存储账号密码，使用方法:
> 存数据：
> `SpUtils.putValue(context, key, value);`
>
> > - 第一个参数为上下文Context，比如MainActivity.this；
> > - 第二个参数为键值String，比如"account";
> > - 第三个参数为键值对应的值String，比如"bizideal";
>
> 取数据：
> `SpUtils.getValue(context, key, defValue);`
> > - 第一个参数为上下文Context，比如MainActivity.this；
> > - 第二个参数为需要取的键值String，比如"account";
> > - 第三个参数为默认值String，比如"0";
> > - 最后会返回一个String值；
>
> **ControlUtils**是一个控制类，用于控制智能家居并获取数据，使用前先设置用户名密码：`ControlUtils.setUser("用户名", "密码", "IP地址");`三个参数均为String；
>
> **SocketClient**是一个创连接用的类，先使用`SocketClient.getInstance().creatConnect();`获取实例化对象并创建连接，然后使用`SocketClient.getInstance().login(new LoginCallback());`来获取返回数据，在LoginCallback内重写onEvent方法，必须创建一个runOnUiTread，因为连接是需要一定时间的，需要使用其他线程来获取结果，返回值为String类型，使用.equals()；来判断是否连接成功；
>
> **DeviceBean**是一个组件类，作用是将功能、值和其他任何可以用代码创造的对象进行打包，方便调用。
>
> **ConstantUtil**是一个不知道啥类，存了所有智能家居英文对应的id。
>
> **DataCallback**是一个用来返回所有传感器数据的类。
>
> **LoginCallback**是一个用来返回登陆成功与否的类。
>
> 登陆连接部分示例代码：
>
> ```java
> //将值放入SpUtil中
> SpUtils.putValue( this, "Account", mAccountEt.getText().toString() );
> SpUtils.putValue( this, "Password", mPasswordEt.getText().toString() );
> SpUtils.putValue( this, "Ip", mIpEt.getText().toString() );
> //设置账号密码
> ControlUtils.setUser( mAccountEt.getText().toString(), mPasswordEt.getText().toString(), mIpEt.getText().toString() );
> //进行socket连接
> SocketClient.getInstance().creatConnect();
> //连接回调
> SocketClient.getInstance().login( new LoginCallback() {
> 	@Override
> 	public void onEvent(final String status) {
> 			runOnUiThread( new Runnable() {
> 	    	@Override
> 	      public void run() {
> 	      	if (status.equals( ConstantUtil.SUCCESS )) {
> 	      		startActivity( new Intent( MainActivity.this, MainActivity.class ) );
> 	          finish();
> 	        }else {
> 	        	Toast.makeText( MainActivity.this, "失败！", Toast.LENGTH_SHORT ).show();
> 	        }
> 	      }
> 	    });
> 	  }
> 	});
> ```
>
> 备注：在反编译后的库文件中，发现了好几个demo中没有使用到的方法，我感觉其中有一个可能会用到的就是`SocketClient.disConnect();`这个方法，看方法名和代码，作用应该是断开连接.

- #### 滑动验证

> 部分题目中要求滑动验证，可以直接使用SeekBar，通过设置`android:progressDrawable`属性来设置背景资源，通过`android:thumb`属性来设置拖动滑块的资源；甚至还有一套题需要滑动拼图验证，也可以使用这种方法，通过`android:padding`来设置内边距，就可以让滑块到指定的位置，例子：
>
> ```xml
> <SeekBar
> 	android:id="@+id/sb_main"
>   android:layout_width="match_parent"
>   android:layout_height="wrap_content" 
>   android:minHeight="40dp"
>   android:minWidth="40dp"
>   android:paddingTop="15dp"
>   android:layout_marginLeft="5dp"
>   android:layout_marginRight="5dp"
>   android:progressDrawable="@drawable/custom_progress"
>   android:thumb="@drawable/a"/>
> ```
>
> 
- #### 进度条加载
> 进度条加载是个假的，只需要达到加载动画的效果就可以了，要新开一个线程用来计数，在主线程中计数会导致UI界面卡死：
>
> ```java
> new Thread(new Runnable() {
> 	@Override
> 	public void run() {
> 	//使用for循环计数
> 	for (int i = 0; i < 101; i++) {
> 		//使用Message向主线程传递计数
> 		Message msg=new Message();
> 		msg.what=i;
> 		mHandler.sendMessage(msg);
> 		mainBar.setProgress(i);
> 		//线程操作部分记得用trycatch括起来
> 		try {
> 			//线程休眠10ms，随后继续计数
> 			Thread.sleep(10);
> 			} catch (InterruptedException e) {
> 			e.printStackTrace();
> 			}
> 		}
> 	}
> }).start();
> ```
>
> 然后在主线程内使用Handler接收数据：
>
> ```java
> Handler mHandler=new Handler(){
> 			public void handleMessage(Message msg){
> 				super.handleMessage(msg);
> 				//通过其他线程传进来的Message对象，获取其中的msg的值，随后设置UI文本
> 				loadnum.setText(msg.what+"%");
> 				//当msg传进来的值到达100时，新建弹窗并设置弹窗按钮监听，跳转到下一个界面。
> 				switch (msg.what) {
> 					case 100:
> 						AlertDialog.Builder dialog=new AlertDialog.Builder(LoadActivity.this);
> 						dialog.setTitle("加载完毕");
> 						dialog.setNegativeButton("OK", new OnClickListener() {
> 							@Override
> 							public void onClick(DialogInterface dialog, int which) {
> 								// TODO Auto-generated method stub
> 								dialog.dismiss();
> 								startActivity(new Intent(LoadActivity.this,MainActivity.class));
> 								finish();
> 							}
> 						});
> 						dialog.show();
> 						break;
> 					default:
> 						break;
> 					}
> 				}
> 			};
> ```
>
> 

- #### 数据库的使用
> 在安卓里，系统自带sqlite数据库，在某些题目里需要使用到数据库，首先你得写一个SqlHelper继承SQLiteOpenHelper类，在这个类里面咱们得实现三种方法：构造函数，onCreate，onUpgrade：
>
> 构造函数：
> ```java
> //构造函数，必须得有，dbName为你需要创建的数据库名
> public DatabaseHelper(Context context, String dbName, CursorFactory factory, int version) {
> super(context, name, factory, version);
> }
> ```
>
> onCreate:
>
> ```java
> //可以理解为初始化数据库，使用sql语句创建数据库
> public void onCreate(SQLiteDatabase db) {
> db.execSQL("sql语句");
> }
> ```
>
> onUpgrade：
>
> ```Java
> //实现数据库升级方法，可以暂时不用管
> public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
> 
> }
> ```
> 来一段示例代码：MyDatabaseHelper.java
>
> ```java
> package com.example.sqlitedb;
> 
> import android.content.Context;
> import android.database.sqlite.SQLiteDatabase;
> import android.database.sqlite.SQLiteDatabase.CursorFactory;
> import android.database.sqlite.SQLiteOpenHelper;
> 
> public class MyDatabaseHelper extends SQLiteOpenHelper{
> //创建表UserData_1的sql语句
> public static final String CREATE_ST_STRING = "CREATE TABLE UserData_1" +
>          "(" +
>          "_id INTEGER PRIMARY KEY AUTOINCREMENT," +   	//序号，键值，int类型，自增
>          "userName TEXT," +                  //用户名，text类型
>          "passWord TEXT," +               		//密码，text类型
>          "tempData TEXT," +                  //温度传感器数据，text类型
>          "curtainStatus INT" +               //窗帘状态，int类型
>          ")";
> 
> //构造方法，可在其他类中调用
> 	public MyDatabaseHelper(Context context, String name,CursorFactory factory, int version) {
> 		super(context, name, factory, version);
> 	}
> 
> 	@Override
> 	public void onCreate(SQLiteDatabase db) {
>  //执行sql语句
> 		db.execSQL(CREATE_ST_STRING);
> 	}
> 
> 	@Override
> 	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
> 		
> 	}
> 
> }
> ```
>
> 然后在主类中使用这个MyDatabaseHelper类：
>
> ```java
> //新建一个MyDatabaseHelper对象，main_data是数据库名，factory使用默认factory构造，所以可为null，version随便写一个就行
> MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "main_data", null, 1);
> //获取可读写数据库
> SQLiteDatabase dbMain = dbHelper.getWritableDatabase();
> ```
>
> 可能需要自己实现数据库的增删改查功能，我自己写了一个SqliteDatabaseUtil工具类，可以作为参考：
>
> ```java
> import android.content.ContentValues;
> import android.database.Cursor;
> import android.database.sqlite.SQLiteDatabase;
> import android.os.Message;
> import android.util.Log;
> import android.widget.SimpleCursorAdapter;
> 
> import com.icezx.subbook.MainActivity;
> 
> import java.util.ArrayList;
> import java.util.HashMap;
> import java.util.List;
> import java.util.Map;
> import java.util.concurrent.LinkedBlockingQueue;
> import java.util.concurrent.ThreadPoolExecutor;
> import java.util.concurrent.TimeUnit;
> 
> /**
>  * @author Yili(yili)
>  * @description
>  * @package com.icezx.subbook
>  * @date 2020-02-12
>  */
> public class SqliteDatabaseUtil {
> 
>     private static final String TAG = "SqliteDatabaseUtil";
> 
>     /**
>      * Sqlite数据库增加记录
>      *
>      * @param db        数据库对象
>      * @param tableName 数据表名字
>      * @param keys      要插入的数据的列的名字
>      * @param values    与列对应的值
>      */
>     public static void insertData(SQLiteDatabase db, String tableName, String[] keys, String[] values) {
>       ContentValues mContentValues = new ContentValues();
>       for (int i = 0; i < keys.length; i++) {
>         	mContentValues.put(keys[i], values[i].trim());
>       }
>       db.insert(tableName, null, mContentValues);
>       mContentValues.clear();
>       Log.i(TAG, "执行了增加记录操作");
>     }
> 
>     /**
>      * Sqlite数据库删除记录，根据_id删除
>      *
>      * @param adapter   SimpleCursorAdapter适配器对象适配器对象
>      * @param db        数据库对象
>      * @param tableName 数据表名字
>      * @param positon   在列表中对应的位置
>      */
>     public static void deleteData(SimpleCursorAdapter adapter, SQLiteDatabase db, String tableName, int positon) {
>         Cursor mCursor = adapter.getCursor();
>         mCursor.moveToPosition(positon);
>         int itemId = mCursor.getInt(mCursor.getColumnIndex("_id"));
>         db.delete(tableName, "_id=?", new String[]{itemId + ""});
>         mCursor.close();
>         Log.i(TAG, "执行了删除记录操作");
>     }
> 
>     /**
>      * Sqlite数据库修改记录
>      *
>      * @param adapter   SimpleCursorAdapter适配器对象
>      * @param db        数据库对象
>      * @param tableName 数据表名字
>      * @param positon   在列表中对应的位置
>      * @param keys      要插入的数据的列的名字
>      * @param values    与列对应的值
>      */
>     public static void updateData(SimpleCursorAdapter adapter, SQLiteDatabase db, String tableName, int positon, String[] keys, String[] values) {
>         Cursor mCursor = adapter.getCursor();
>         mCursor.moveToPosition(positon);
>         int itemId = mCursor.getInt(mCursor.getColumnIndex("_id"));
>         ContentValues mContentValues = new ContentValues();
>         for (int i = 0; i < keys.length; i++) {
>             mContentValues.put(keys[i], values[i].trim());
>         }
>         db.update(tableName, mContentValues, "_id=?", new String[]{itemId + ""});
>         mContentValues.clear();
>         mCursor.close();
>         Log.i(TAG, "执行了修改记录操作");
>     }
> 
>     /**
>      * Sqlite数据库查询记录，返回一个List对象
>      *
>      * @param db          数据库对象
>      * @param tableName   数据表名字
>      * @param whichColumn 想要查询的列，这是一个String[]
>      * @param keyWord     想要查询的关键词
>      * @param whichSelect 想要查询的这个关键词对应的列名字
>      */
>     public static List<Map<String, String>> queryData1(SQLiteDatabase db, String tableName, String[] whichColumn, String keyWord, String whichSelect) {
>         List<Map<String, String>> mStringList = new ArrayList<>();
>         //第二个参数是你需要查找的列
>         //第三和第四个参数确定是从哪些行去查找第二个参数的列
>         Cursor mCursor1 = db.query(tableName, whichColumn, whichSelect + "=?", new String[]{keyWord}, null, null, null);
>         Log.d(TAG, "得到了" + mCursor1.getCount() + "条结果");
>         if (mCursor1.getCount() > 0) {
>             mStringList.clear();
>             //游标总是在查询到的上一行
>             while (mCursor1.moveToNext()) {
>                 Map<String, String> mMap = new HashMap<>();
>                 for (int i = 0; i < whichColumn.length; i++) {
>                     mMap.put(whichColumn[i], mCursor1.getString(mCursor1.getColumnIndex(whichColumn[i])));
>                 }
>                 mStringList.add(mMap);
>             }
>             mCursor1.close();
>         } else {
>             mStringList.clear();
>             Map<String, String> mMap = new HashMap<>();
>             mMap.put(whichSelect, "无结果");
>             mStringList.add(mMap);
>         }
>         Log.d(TAG, "查询结果" + mStringList.toString());
>         return mStringList;
>     }
> }
> 
> ```
>
> 数据库这部分可能会有些复杂，慢慢来吧。

- 获取登陆或数据回调
> 这一部分我遇到过坑，所以一定要写！！！
>
> 在整个App中，获取回调的时候，这些代码一定只能出现一次：
>
> 这是获取登陆状态回调：
>
> ```java
> SocketClient.getInstance().login( new LoginCallback() {
>             @Override
>             public void onEvent(final String status) {
>                 runOnUiThread( new Runnable() {
>                     @Override
>                     public void run() {
>                         if (status.equals( ConstantUtil.SUCCESS  )) {
>                             Toast.makeText( MainActivity.this, "重连成功！", Toast.LENGTH_SHORT ).show();
>                         } else if (status.equals( ConstantUtil.FAILURE  )) {
>                             Toast.makeText( MainActivity.this, "重连失败！", Toast.LENGTH_SHORT ).show();
>                         } else {
>                             Toast.makeText( MainActivity.this, "重连中！", Toast.LENGTH_SHORT ).show();
>                         }
>                     }
>                 } );
> ```
>
> 这是获取传感器数据回调：
>
> ```java
> ControlUtils.getData();
> SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
> 	@Override
> 	public void onResult(final DeviceBean bean) {
> 		// TODO Auto-generated method stub
> 		runOnUiThread(new Runnable() {			
> 			@Override
> 			public void run() {
> 				// TODO Auto-generated method stub
> 				if (!TextUtils.isEmpty(bean.getTemperature())) {
> 					wendu.setText(bean.getTemperature());
> 				}
> 				if (!TextUtils.isEmpty(bean.getHumidity())) {
> 					shidu.setText(bean.getHumidity());
> 				}
> 				if (!TextUtils.isEmpty(bean.getGas())) {
> 					ranqi.setText(bean.getGas());
> 				}
> 				if (!TextUtils.isEmpty(bean.getIllumination())) {
> 					guangzhao.setText(bean.getIllumination());
> 				}
> 				if (!TextUtils.isEmpty(bean.getPM25())) {
> 					pm25.setText(bean.getPM25());
> 				}
> 				if (!TextUtils.isEmpty(bean.getAirPressure())) {
> 					qiya.setText(bean.getAirPressure());
> 				}
> 				if (!TextUtils.isEmpty(bean.getSmoke())) {
> 					yanwu.setText(bean.getSmoke());
> 				}
> 				if (!TextUtils.isEmpty(bean.getCo2())) {
> 					co2.setText(bean.getCo2());
> 				}
> 				if (!TextUtils.isEmpty( bean.getStateHumanInfrared() ) && bean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )){
> 					renti.setText( "无人" );
>         }else {
>           renti.setText( "有人" );
>         }
> 				if (!TextUtils.isEmpty( bean.getLamp() ) && bean.getLamp().equals( ConstantUtil.CLOSE )) {
>           shedeng.setChecked( false );
>           shedeng.setBackgroundResource(R.drawable.shedeng);
>         } else {
>           shedeng.setChecked( true );
>           shedeng.setBackgroundResource(R.drawable.shedeng_press);
>         }
>         if (!TextUtils.isEmpty( bean.getFan() ) && bean.getFan().equals( ConstantUtil.CLOSE )) {
>           fengshan.setChecked( false );
>           fengshan.setBackgroundResource(R.drawable.fengshan);
>         }else {
>           fengshan.setChecked( true );
>           fengshan.setBackgroundResource(R.drawable.fengshan_press);
>         }
>         if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_3 )) {
>           Toast.makeText(MainActivity.this, "窗帘开", Toast.LENGTH_SHORT).show();
>         }else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_1 )) {
>           Toast.makeText(MainActivity.this, "窗帘停", Toast.LENGTH_SHORT).show();
>         }else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_2 )) {
>           Toast.makeText(MainActivity.this, "窗帘关", Toast.LENGTH_SHORT).show();
>         }
>         if (!TextUtils.isEmpty( bean.getWarningLight() ) && bean.getWarningLight().equals( ConstantUtil.CLOSE )) {
>           baojing.setChecked( false );
>           baojing.setBackgroundResource(R.drawable.baojing);
>         }else {
>           baojing.setChecked( true );
>           baojing.setBackgroundResource(R.drawable.baojing_press);
>         }
> 			}
> 		});
> 	}
> });
> ```
>
> 这些代码在整个App中一定只能出现一次，不同界面间的数据传输请使用全局变量的方法(java的全部变量可以自行百度)，比如我在“MainActivity”中使用了这些代码获取回调数据，我想在“SecondActivity”中也想使用“MainActivity”中获取到的数据，那就在“MainActivity”中传值给全局变量的类。千万不能重复使用上面的代码，那样会导致每刷新一次数据，界面就会重载一次！

- 绘图界面
> 具体项目请参考：
>
> [绘制折线图]: https://github.com/Yilimmilk/QiXiang_SmartHome_Android/tree/master/2019eclipse%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6_%E5%AE%89%E5%8D%93/%E5%8F%AF%E4%BE%9B%E5%8F%82%E8%80%83%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%96%87%E4%BB%B6/%E7%BB%98%E5%88%B6%E6%8A%98%E7%BA%BF%E5%9B%BE
>
> 需要自定义View，然后使用Canvas绘图，可供参考的代码：
>
> ```java
> import android.content.Context;
> import android.graphics.Canvas;
> import android.graphics.Color;
> import android.graphics.Paint;
> import android.util.AttributeSet;
> import android.view.View;
> 
> public class MyView extends View {
> 
> 	public MyView(Context context, AttributeSet attrs) {
> 		super(context, attrs);
> 		// TODO Auto-generated constructor stub
> 	}
> 	
> 	// 默认边距
> 	private int Margin = 40;
> 	// 原点坐标
> 	private int Xpoint;
> 	private int Ypoint;
> 	// X,Y轴的单位长度
> 	private int Xscale = 20;
> 	private int Yscale = 20;
> 	// X,Y轴上面的显示文字
> 	private String[] Xlabel = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
> 	private String[] Ylabel = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
> 	// 标题文本
> 	private String Title;
> 	// 曲线数据
> 	private int[] Data = { 1,2,3,4,5,6,7,8,9 };
> 
> 	public MyView(Context context, String[] xlabel, String[] ylabel,
> 			String title, int[] data) {
> 		super(context);
> 		this.Xlabel = xlabel;
> 		this.Ylabel = ylabel;
> 		this.Title = title;
> 		this.Data = data;
> 	}
> 
> 	public MyView(Context context) {
> 		super(context);
> 	}
> 
> 	// 初始化数据值
> 	public void init() {
> 		Xpoint = this.Margin;
> 		Ypoint = this.getHeight() - this.Margin;
> 		Xscale = (this.getWidth() - 2 * this.Margin) / (this.Xlabel.length - 1);
> 		Yscale = (this.getHeight() - 2 * this.Margin)
> 				/ (this.Ylabel.length - 1);
> 	}
> 
> 	public int getMargin() {
> 		return Margin;
> 	}
> 
> 	public void setMargin(int margin) {
> 		Margin = margin;
> 	}
> 
> 	@Override
> 	protected void onDraw(Canvas canvas) {
> 		canvas.drawColor(Color.WHITE);
> 		Paint p1 = new Paint();
> 		p1.setStyle(Paint.Style.STROKE);
> 		p1.setAntiAlias(true);
> 		p1.setColor(Color.BLACK);
> 		p1.setStrokeWidth(2);
> 		init();
> 		this.drawXLine(canvas, p1);
> 		this.drawYLine(canvas, p1);
> 		// this.drawTable(canvas);
> 		this.drawData(canvas);
> 	}
> 
> 	// // 画表格
> 	// private void drawTable(Canvas canvas) {
> 	// Paint paint = new Paint();
> 	// paint.setStyle(Paint.Style.STROKE);
> 	// paint.setColor(Color.GRAY);
> 	// Path path = new Path();
> 	// PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
> 	// paint.setPathEffect(effects);
> 	// // 纵向线
> 	// for (int i = 1; i * Xscale <= (this.getWidth() - this.Margin); i++) {
> 	// int startX = Xpoint + i * Xscale;
> 	// int startY = Ypoint;
> 	// int stopY = Ypoint - (this.Ylabel.length - 1) * Yscale;
> 	// path.moveTo(startX, startY);
> 	// path.lineTo(startX, stopY);
> 	// canvas.drawPath(path, paint);
> 	// }
> 	// // 横向线
> 	// for (int i = 1; (Ypoint - i * Yscale) >= this.Margin; i++) {
> 	// int startX = Xpoint;
> 	// int startY = Ypoint - i * Yscale;
> 	// int stopX = Xpoint + (this.Xlabel.length - 1) * Xscale;
> 	// path.moveTo(startX, startY);
> 	// path.lineTo(stopX, startY);
> 	// paint.setColor(Color.DKGRAY);
> 	// canvas.drawPath(path, paint);
> 	// paint.setColor(Color.WHITE);
> 	// paint.setTextSize(this.Margin / 2);
> 	// canvas.drawText(this.Ylabel[i], this.Margin / 4, startY
> 	// + this.Margin / 4, paint);
> 	// }
> 	// }
> 
> 	// 画横纵轴
> 	private void drawXLine(Canvas canvas, Paint p) {
> 		canvas.drawLine(Xpoint, Ypoint, this.Margin, this.Margin, p);
> 		//canvas.drawLine(Xpoint, this.Margin, Xpoint - Xpoint / 3, this.Margin+ this.Margin / 3, p);
> 		//canvas.drawLine(Xpoint, this.Margin, Xpoint + Xpoint / 3, this.Margin+ this.Margin / 3, p);
> 	}
> 
> 	private void drawYLine(Canvas canvas, Paint p) {
> 		canvas.drawLine(Xpoint, Ypoint, this.getWidth() - this.Margin, Ypoint,p);
> 		//canvas.drawLine(this.getWidth() - this.Margin, Ypoint, this.getWidth()- this.Margin - this.Margin / 3, Ypoint - this.Margin / 3, p);
> 		//canvas.drawLine(this.getWidth() - this.Margin, Ypoint, this.getWidth()- this.Margin - this.Margin / 3, Ypoint + this.Margin / 3, p);
> 	}
> 
> 	// 画数据
> 	private void drawData(Canvas canvas) {
> 		Paint p = new Paint();
> 		p.setAntiAlias(true);
> 		p.setColor(Color.BLACK);
> 		p.setTextSize(this.Margin / 2);
> 		// 纵向线
> 		for (int i = 1; i * Xscale <= (this.getWidth() - this.Margin); i++) {
> 			int startX = Xpoint + i * Xscale;
> 			canvas.drawText(this.Xlabel[i], startX - this.Margin / 4,
> 					this.getHeight() - this.Margin / 4, p);
> 			canvas.drawCircle(startX, calY(Data[i]), 4, p);
> 			canvas.drawLine(Xpoint + (i - 1) * Xscale, calY(Data[i - 1]),
> 					startX, calY(Data[i]), p);
> 		}
> 	}
> 
> 	/**
> 	 * 
> 	 * @param y
> 	 * @return
> 	 */
> 	private int calY(int y) {
> 		int y0 = 0;
> 		int y1 = 0;
> 		// Log.i("zzzz", "y:"+y);
> 		try {
> 			y0 = Integer.parseInt(Ylabel[0]);
> 			// Log.i("zzzz", "y0"+y0);
> 			y1 = Integer.parseInt(Ylabel[1]);
> 			// Log.i("zzzz","y1"+y1);
> 		} catch (Exception e) {
> 			// Log.i("zzzz", "string changed is err");
> 			return 0;
> 		}
> 		try {
> 			// Log.i("zzzz", "返回数据"+(Ypoint-(y-y0)*Yscale/(y1-y0)) );
> 			return Ypoint - ((y - y0) * Yscale / (y1 - y0));
> 		} catch (Exception e) {
> 			// Log.i("zzzz", "return is err");
> 			return 0;
> 		}
> 	}
> 
> }
> ```
>
> 然后在layout文件中使用这些代码来使用这个自定义View：
>
> ```xml
> <com.example.testview.MyView
>         android:layout_width="wrap_content"
>         android:layout_height="wrap_content">
> </com.example.testview.MyView>
> ```
>
> 这只是参考代码，实际在项目中使用的话，需要使用到大量变量，数组，然后在主线程中设置定时，隔一段时间刷新一次，数组中填充新数据，替换掉旧数据。
>
> 绘图这部分可能需要很多时间，所以尽力完成吧，要是时间实在不够就别做了。(毕竟我就没时间做了.....)

## 📖相关说明

+ 在eclipse的设置中，记得打开auto-import，详细：[百度经验-Eclipse自动导包](https://jingyan.baidu.com/article/870c6fc34a4430b03fe4beca.html)

+ 也请记住Eclipse的部分常用快捷键：[菜鸟教程-Eclipse快捷键](https://www.runoob.com/eclipse/eclipse-shortcuts.html)

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

