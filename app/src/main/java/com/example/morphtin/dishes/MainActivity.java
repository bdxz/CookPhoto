package com.example.morphtin.dishes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPOutputStream;


public class MainActivity extends AppCompatActivity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivAdd = (ImageView)findViewById(R.id.dd5);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                applyWritePermission();

                JSONObject jsonPic = new JSONObject();
                try {
                    jsonPic.put("PICSTRING",getPicToString(file));
                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }
        });

        ImageView iv = (ImageView)findViewById(R.id.dd1);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getPicToString(File file){//1.将图片转为字节数组 2.将字节数组转为字符串 3.压缩字符串，放入json传输
        //用于返回的字节数组
        byte[] data=null;
        //打开文件输入流
        FileInputStream fin=null;
        //打开字节输出流
        ByteArrayOutputStream bout=null;
        try{
            //文件输入流获取对应文件
            fin=new FileInputStream(file);
            //输出流定义缓冲区大小
            bout=new ByteArrayOutputStream((int)file.length());
            //定义字节数组，用于读取文件流
            byte[] buffer=new byte[1024];
            //用于表示读取的位置
            int len=-1;
            //开始读取文件
            while((len=fin.read(buffer))!=-1){
                //从buffer的第0位置开始，读取至第len位置，结果写入bout
                bout.write(buffer,0,len);
            }
            //将输出流转为字节数组
            data=bout.toByteArray();
            //关闭输入输出流
            fin.close();
            bout.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String dataString=null;
        try{
            //将字节数组转为字符串，编码格式为ISO-8859-1
            dataString=new String(data,"ISO-8859-1");
        }catch(Exception e){
            e.printStackTrace();
        }
        String finalData=null;
        try{
            //打开字节输出流
            bout=new ByteArrayOutputStream();
            //打开压缩用的输出流,压缩后的结果放在bout中
            GZIPOutputStream gout=new GZIPOutputStream(bout);
            //写入待压缩的字节数组
            gout.write(dataString.getBytes("ISO-8859-1"));
            //完成压缩写入
            gout.finish();
            //关闭输出流
            gout.close();
            finalData=bout.toString("ISO-8859-1");

            bout.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return finalData;
    }

    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,1);// REQUEST_CAMERA);
    }

    public void applyWritePermission() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            Log.e("TAG", "---------" + FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file));
            ImageView imageView= (ImageView)findViewById(R.id.currentImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }
    }
}
