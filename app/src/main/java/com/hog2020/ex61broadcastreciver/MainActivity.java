package com.hog2020.ex61broadcastreciver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //안드로이드 애플리케이션 4대구성요소
    //1.Activity - 화면 담당 클래스
    //2.Broadcast - 디바이스의 특징 상태를 os 에서 발송해주면 이를 수신할 때 사용
    //3.Service - 백그라운드(앱은 실행중 -화면에서 가려지는)에서 코드를 동작하게 하고 싶을 때 사용
    //4.Contents Provider - 다른앱에게 나의 DB 정보를 제공할 때 사용

    //이예제는 브로드캐스트 리스버의 수신 연습을 위해서
    //원래는 Os[운영체제:android]가 방송[Broadcast] 을 해야하지만
    //이앱에서 버튼을 눌러서 방송 해보고 이를 수신해보기
    //다음예제에서 실제 os의 방송을 수신해보기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickbtn(View view) {
        //방송 보내기..4대 컴포넌트 중 Activity,Service,Broadcast receiver 는 모든 Intent 로실행
        //방송은 운영체제가 발송하는 것임...연습으로 여기서 강제로 발송 실행

        //1.명시적 인텐트[class 값으로 식별] - [같은 앱]안에 있는 리시버만 듣는 방송
        //4대 구성요소는 메니페스트에 등록되어 있어야함
//        Intent intent = new Intent(this,MyReceiver.class);
//        sendBroadcast(intent);

        //2.암시적 인텐트[action 값으로 식별] - 디바이스 안에 설치 된 [모든앱] 안에 있는 리시버가 듣는 방송 Intent
        // oreo 버전 부터 암시적 인텐트는 시스템엑션만 가능함.[os만 방송 할 수 있는 Intent]
        //그럼에도 사용 하고 싶다면.. 암시적 인텐트를 듣는 리시버를 동적으로 등록해야 만함- Manifest.xml에 리시버를 등록하지않고 Java 에 등록하는 방법
        //"aaa"라는 이름의 방송 송출
        Intent intent =new Intent();
        intent.setAction("aaa");

        sendBroadcast(intent);
    }

    //앱이 화면에 보여질때 리시버 등록

    MyReceiver receiver;
    @Override
    protected void onResume() {
        super.onResume();
        //"aaa"라는 일시적인 인텐트 방송을 수신하는 리시버 동적 등록
        receiver=  new MyReceiver();

        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction("aaa");

        registerReceiver(receiver,intentFilter);
    }

    //화면에서 안보일때 발동하는 메소드[onPause]


    @Override
    protected void onPause() {
        super.onPause();
        //리시버 제거
        unregisterReceiver(receiver);
    }
}