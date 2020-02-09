package com.example.smartdemo;

import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.bizideal.smarthome.socket.ConstantUtil;

public class ControlActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private TextView mTempTv, mHumidityTv, mGasTv, mIlluminationTv, mPm25Tv, mPressureTv, mSmokeTv, mCo2Tv, mStateHumanInfraredTv;
    private CheckBox mLampCb, mDoorCb, mWindSpeedCb, mAlarmCb, mChanne1Cb, mChanne2Cb, mChanne3Cb, mIlluminationCb, mTempCb,
            mSecurityCb, mLeavehomeCb, mSleepCb, mGetupCb;
    private ImageView mCurtainOpenImg, mCurtainStopImg, mCurtainCloseImg;
    private boolean isCheckedState = false, isState = false;
    public String mPosition;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_control );
        initViews();
        initData();
        SocketClient.getInstance().login( new LoginCallback() {
            @Override
            public void onEvent(final String status) {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (status.equals( ConstantUtil.SUCCESS  )) {
                            Toast.makeText( ControlActivity.this, "重连成功！", Toast.LENGTH_SHORT ).show();
                        } else if (status.equals( ConstantUtil.FAILURE  )) {
                            Toast.makeText( ControlActivity.this, "重连失败！", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( ControlActivity.this, "重连中！", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } );
    }


    private void initData() {
        ControlUtils.getData();
        SocketClient.getInstance().getData( new DataCallback<DeviceBean>() {
            @Override
            public void onResult(final DeviceBean bean) {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(bean.getTemperature() )){
                            mTempTv.setText( bean.getTemperature() );
                        }
                        if (!TextUtils.isEmpty( bean.getHumidity() )){
                            mHumidityTv.setText( bean.getHumidity() );
                        }
                        if (!TextUtils.isEmpty( bean.getGas() )){
                            mGasTv.setText( bean.getGas() );
                        }
                        if (!TextUtils.isEmpty( bean.getIllumination() )){
                            mIlluminationTv.setText( bean.getIllumination() );
                        }
                        if (!TextUtils.isEmpty( bean.getPM25() )){
                            mPm25Tv.setText( bean.getPM25() );
                        }
                        if (!TextUtils.isEmpty( bean.getSmoke() )){
                            mSmokeTv.setText( bean.getSmoke() );
                        }
                        if (!TextUtils.isEmpty( bean.getCo2() )){
                            mCo2Tv.setText( bean.getCo2() );
                        }
                        if (!TextUtils.isEmpty( bean.getAirPressure() )){
                            mPressureTv.setText( bean.getAirPressure() );
                        }
                        if (!TextUtils.isEmpty( bean.getStateHumanInfrared() ) && bean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )){
                            mStateHumanInfraredTv.setText( "无人" );
                        }else{
                            mStateHumanInfraredTv.setText( "有人" );
                        }
                        if (!TextUtils.isEmpty( bean.getLamp() ) && bean.getLamp().equals( ConstantUtil.CLOSE )) {
                            mLampCb.setChecked( false );
                            mLampCb.setBackgroundResource( R.drawable.lamp_unpressed );
                        } else {
                            mLampCb.setBackgroundResource( R.drawable.lamp_pressed );
                            mLampCb.setChecked( true );
                        }
                        if (!TextUtils.isEmpty( bean.getFan() ) && bean.getFan().equals( ConstantUtil.CLOSE )) {
                        	mWindSpeedCb.setChecked( false );
                            mWindSpeedCb.setBackgroundResource( R.drawable.wind_speed_unpressed );
                        } else {
                            mWindSpeedCb.setBackgroundResource( R.drawable.wind_speed_pressed );
                            mWindSpeedCb.setChecked( true );
                        }
                        if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_1 )) {
                            mCurtainOpenImg.setBackgroundResource( R.drawable.curtain_open_pressed );
                            mCurtainStopImg.setBackgroundResource( R.drawable.curtain_stop_unpressed );
                            mCurtainCloseImg.setBackgroundResource( R.drawable.curtain_close_unpressed );
                        } else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_2 )) {
                            mCurtainOpenImg.setBackgroundResource( R.drawable.curtain_open_unpressed );
                            mCurtainStopImg.setBackgroundResource( R.drawable.curtain_stop_pressed );
                            mCurtainCloseImg.setBackgroundResource( R.drawable.curtain_close_unpressed );
                        } else if (!TextUtils.isEmpty( bean.getCurtain() ) && bean.getCurtain().equals( ConstantUtil.CHANNEL_3 )) {
                            mCurtainOpenImg.setBackgroundResource( R.drawable.curtain_open_unpressed );
                            mCurtainStopImg.setBackgroundResource( R.drawable.curtain_stop_unpressed );
                            mCurtainCloseImg.setBackgroundResource( R.drawable.curtain_close_pressed );
                        }
                        if (!TextUtils.isEmpty( bean.getWarningLight() ) && bean.getWarningLight().equals( ConstantUtil.CLOSE )) {
                            mAlarmCb.setChecked( false );
                            mAlarmCb.setBackgroundResource( R.drawable.alarm_unpressed );
                        } else {
                            mAlarmCb.setChecked( true );
                            mAlarmCb.setBackgroundResource( R.drawable.alarm_pressed );
                        }
                    }
                } );
            }
        } );
    }

    private void initViews(){
        mTempTv = (TextView) findViewById( R.id.temp_tv );//温度
        mHumidityTv = (TextView) findViewById( R.id.humidity_tv );//湿度
        mGasTv = (TextView) findViewById( R.id.gas_tv );//燃气
        mIlluminationTv = (TextView) findViewById( R.id.illumination_tv );//光照度
        mPm25Tv = (TextView) findViewById( R.id.pm25_tv );//Pm2.5
        mPressureTv = (TextView) findViewById( R.id.pressure_tv );//气压
        mSmokeTv = (TextView) findViewById( R.id.smoke_tv );//烟雾
        mCo2Tv = (TextView) findViewById( R.id.c02_tv );//C02
        mStateHumanInfraredTv = (TextView) findViewById( R.id.stateHumanInfrared_tv );//人体红外
        mLampCb = (CheckBox) findViewById( R.id.lamp_cb );//射灯
        mLampCb.setOnCheckedChangeListener( this );
        mDoorCb = (CheckBox) findViewById( R.id.door_cb );//门禁
        mDoorCb.setOnCheckedChangeListener( this );
        mWindSpeedCb = (CheckBox) findViewById( R.id.wind_speed_cb );//风扇
        mWindSpeedCb.setOnCheckedChangeListener( this );
        mAlarmCb = (CheckBox) findViewById( R.id.alarm_cb );//报警灯
        mAlarmCb.setOnCheckedChangeListener( this );
        mCurtainOpenImg = (ImageView) findViewById( R.id.curtain_open );//窗帘开
        mCurtainOpenImg.setOnClickListener( this );
        mCurtainStopImg = (ImageView) findViewById( R.id.curtain_stop );//窗帘停
        mCurtainStopImg.setOnClickListener( this );
        mCurtainCloseImg = (ImageView) findViewById( R.id.curtain_close );//窗帘关
        mCurtainCloseImg.setOnClickListener( this );
        mChanne1Cb = (CheckBox) findViewById( R.id.channe1_cb );//通道1
        mChanne1Cb.setOnCheckedChangeListener( this );
        mChanne2Cb = (CheckBox) findViewById( R.id.channe2_cb );//通道2
        mChanne2Cb.setOnCheckedChangeListener( this );
        mChanne3Cb = (CheckBox) findViewById( R.id.channe3_cb );//通道3
        mChanne3Cb.setOnCheckedChangeListener( this );
        mIlluminationCb = (CheckBox) findViewById( R.id.illumination_cb );//光照
        mIlluminationCb.setOnCheckedChangeListener( this );
        mTempCb = (CheckBox) findViewById( R.id.temp_cb );//温度
        mTempCb.setOnCheckedChangeListener( this );
        mSecurityCb = (CheckBox) findViewById( R.id.security_cb );//安防
        mSecurityCb.setOnCheckedChangeListener( this );
        mLeavehomeCb = (CheckBox) findViewById( R.id.leavehome_cb );//离家
        mLeavehomeCb.setOnCheckedChangeListener( this );
        mSleepCb = (CheckBox) findViewById( R.id.sleep_cb );//睡眠
        mSleepCb.setOnCheckedChangeListener( this );
        mGetupCb = (CheckBox) findViewById( R.id.getup_cb );//起床
        mGetupCb.setOnCheckedChangeListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.curtain_open://窗帘开
                ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
                break;
            case R.id.curtain_stop://窗帘停
                ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
                break;
            case R.id.curtain_close://窗帘关
                //关
                ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!buttonView.isPressed()) return;
        switch (buttonView.getId()) {
            case R.id.lamp_cb://射灯
                if (isChecked) {
                    ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN );
                } else {
                    ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
                }
                break;
            case R.id.door_cb://门禁
                ControlUtils.control( ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
                mDoorCb.setChecked( false );
                break;
            case R.id.wind_speed_cb://风扇
                if (isChecked){
                   ControlUtils.control( ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN );
                }else{
                    ControlUtils.control( ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
                }
            break;
            case R.id.alarm_cb://报警灯
                if (isChecked){
                    ControlUtils.control( ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN );
                }else{
                    ControlUtils.control( ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
                }
                break;
            case R.id.channe1_cb://通道一
                if (isChecked){//开启
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
                }else{//关闭
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE );
                }
                break;
            case R.id.channe2_cb://通道二
                if (isChecked){//开启
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
                }else{
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE );
                }
                break;
            case R.id.channe3_cb://通道三
                if (isChecked){//开启
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
                }else{//关闭
                    ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE );
                }
                break;
            case R.id.illumination_cb://光照
                if (isCheckedState == true && !mPosition.equals( "1" )) {
                    mIlluminationCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "1";
                    mHandler.postDelayed( Scencthread, 1000 );
                } else {
                    isCheckedState = false;
                    if (Scencthread != null)
                        mHandler.removeCallbacks( Scencthread );
                }
                break;
            case R.id.temp_cb://温度
                if (isCheckedState == true && !mPosition.equals( "2" )) {
                    mTempCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "2";
                    mHandler.postDelayed( Scencthread, 1000 );
                } else {
                    isCheckedState = false;
                    if (Scencthread != null)
                        mHandler.removeCallbacks( Scencthread );
                }
                break;
            case R.id.security_cb://安防
                if (isCheckedState == true && !mPosition.equals( "3" )) {
                    mSecurityCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "3";
                    mHandler.postDelayed( Scencthread, 1000 );
                } else {//关闭
                    isCheckedState = false;
                    if (Scencthread != null)
                        mHandler.removeCallbacks( Scencthread );
                }
                break;
            case R.id.leavehome_cb://离家
                if (isCheckedState == true && !mPosition.equals( "4" )) {
                    mLeavehomeCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "4";
                    mHandler.postDelayed( Scencthread, 1000 );
                    i = 0;
                } else {//关闭
                    isCheckedState = false;
                    if (Scencthread != null) {
                        mHandler.removeCallbacks( Scencthread );
                    }
                }
                break;
            case R.id.sleep_cb://睡眠
                if (isCheckedState == true && !mPosition.equals( "5" )) {
                    mSleepCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "5";
                    mHandler.postDelayed( Scencthread, 1000 );
                    j = 0;
                } else {//关闭
                    isCheckedState = false;
                    if (Scencthread != null) {
                        mHandler.removeCallbacks( Scencthread );
                    }
                }
                break;
            case R.id.getup_cb://起床
                if (isCheckedState == true && !mPosition.equals( "6" )) {
                    mGetupCb.setChecked( false );
                    Toast.makeText( ControlActivity.this, "每次只能启用一个场景，请先停用其它场景后在执行该场景", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (isChecked) {//开启
                    isCheckedState = true;
                    mPosition = "6";
                    mHandler.postDelayed( Scencthread, 1000 );
                } else {//关闭
                    isCheckedState = false;
                    if (Scencthread != null)
                        mHandler.removeCallbacks( Scencthread );
                }
                break;
        }
    }

    /**
     * 场景模式
     */
    Thread Scencthread = new Thread( new Runnable() {
        @Override
        public void run() {
            switch (Integer.parseInt( mPosition )) {
                case 1:
                    if (mIlluminationCb.isChecked() == true) {//光照度
                        mHandler.postDelayed( Illumination, 2000 ); //隔2s执行
                    }
                    break;
                case 2:
                    if (mTempCb.isChecked() == true) {//温度
                        mHandler.postDelayed( Temp, 2000 ); //隔2s执行
                    }
                    break;
                case 3:
                    if (mSecurityCb.isChecked() == true) {//安防
                        mHandler.postDelayed( Security, 2000 ); //隔2s执行
                    }
                    break;
                case 4:
                    if (mLeavehomeCb.isChecked() == true) {//离家
                        mHandler.postDelayed( runnable, 2000 ); //隔2s执行
                    }
                    break;
                case 5:
                    if (mSleepCb.isChecked() == true) {//睡眠
                        mHandler.postDelayed( runnable1, 2000 ); //隔2s执行
                    }
                    break;
                case 6:
                    if (mGetupCb.isChecked() == true) {//起床
                        mHandler.postDelayed( Getup, 2000 ); //隔2s执行
                    }
                    break;
            }
        }
    } );

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks( Scencthread );
        }
    }


    Runnable Illumination = new Runnable() {//光照度模式
        @Override
        public void run() {
            if (!TextUtils.isEmpty( DeviceBean.getIllumination() ) && Double.parseDouble( DeviceBean.getIllumination() ) >= 100) {
                if (!TextUtils.isEmpty( DeviceBean.getLamp() ) && DeviceBean.getLamp().equals( ConstantUtil.CLOSE ))
                    ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN );
            } else {//灯光开，窗户关
                if (!TextUtils.isEmpty( DeviceBean.getLamp() ) && !DeviceBean.getLamp().equals( ConstantUtil.CLOSE ))
                    ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
            }
            mHandler.removeCallbacks( Illumination );
        }
    };

    Runnable Security = new Runnable() {//安防模式
        @Override
        public void run() {
            if (!TextUtils.isEmpty( DeviceBean.getStateHumanInfrared() ) && DeviceBean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )) {//人体红外无人时关闭
                if (!TextUtils.isEmpty( DeviceBean.getWarningLight() ) && !DeviceBean.getWarningLight().equals( ConstantUtil.CLOSE ))
                    ControlUtils.control( ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
            } else if (!TextUtils.isEmpty( DeviceBean.getStateHumanInfrared() ) && !DeviceBean.getStateHumanInfrared().equals( ConstantUtil.CLOSE )) {//有人时打开报警灯
                if (!TextUtils.isEmpty( DeviceBean.getWarningLight() ) && DeviceBean.getWarningLight().equals( ConstantUtil.CLOSE ))
                    ControlUtils.control( ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN );
            }
            mHandler.removeCallbacks( Security );
        }
    };
    Runnable Temp = new Runnable() {//温度模式
        @Override
        public void run() {
            if (!TextUtils.isEmpty( DeviceBean.getTemperature() ) && Double.parseDouble( DeviceBean.getTemperature() ) <= 10) {
                ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
            } else {
                ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE );
            }
            mHandler.removeCallbacks( Temp );
        }
    };

    Runnable Getup = new Runnable() {//起床模式
        @Override
        public void run() {
            if (!TextUtils.isEmpty( DeviceBean.getCurtain() ) && !DeviceBean.getCurtain().equals( ConstantUtil.CHANNEL_1 )) {
                ControlUtils.control( ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );//开
            }
            mHandler.removeCallbacks( Getup );
        }
    };
    private int i = 0;
    //离家模式
    Runnable runnable = new Runnable() {//离家模式
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                i++;
                switch (i) {//
                    case 1://灯光
                        if (!TextUtils.isEmpty( DeviceBean.getLamp() ) && !DeviceBean.getLamp().equals( ConstantUtil.CLOSE )){
                            ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE );
                        }
                        break;
                    case 2: //窗帘
                        if (!TextUtils.isEmpty( DeviceBean.getCurtain() ) && DeviceBean.getCurtain().equals( ConstantUtil.CHANNEL_1 ) ||
                                DeviceBean.getCurtain().equals( ConstantUtil.CLOSE )){
                            ControlUtils.control( ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );//关
                        }
                        break;
                    case 3: //通道1
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
                        break;
                    case 4://通道2
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
                        break;
                    case 5://通道3
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
                        break;
                    case 6://风扇
                        ControlUtils.control( ConstantUtil.Fan, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE );
                        mHandler.removeCallbacks( runnable );
                        break;
                }
                mHandler.postDelayed( this, 3000 );
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


    private int j = 0;
    //睡眠模式
    Runnable runnable1 = new Runnable() {//睡眠模式
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                j++;
                switch (j) {
                    case 1://灯光
                        if (!TextUtils.isEmpty( DeviceBean.getLamp() ) && !DeviceBean.getLamp().equals( ConstantUtil.CLOSE )){
                            ControlUtils.control( ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE );
                        }
                        break;
                    case 2: //窗帘
                        if (!TextUtils.isEmpty( DeviceBean.getCurtain() ) && DeviceBean.getCurtain().equals( ConstantUtil.CHANNEL_1 ) ||
                                DeviceBean.getCurtain().equals( ConstantUtil.CLOSE )){
                               ControlUtils.control( ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );//关
                        }
                        break;
                    case 3: //通道1
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN );
                        break;
                    case 4://通道2
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN );
                        break;
                    case 5://通道3
                        ControlUtils.control( ConstantUtil.INFRARED_1_SERVE, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN );
                        break;
                    case 6://风扇
                        if (!TextUtils.isEmpty( DeviceBean.getLamp() ) && !DeviceBean.getLamp().equals( ConstantUtil.CLOSE )){
                            ControlUtils.control( ConstantUtil.Fan, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE );
                        }
                        mHandler.removeCallbacks( runnable1 );
                        break;
                }
                mHandler.postDelayed( this, 3000 );
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


}
