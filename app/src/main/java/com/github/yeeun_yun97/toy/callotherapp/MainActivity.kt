package com.github.yeeun_yun97.toy.callotherapp

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val registerButton: Button by lazy{findViewById(R.id.MainActivity_registerButton)}
    private val savedFormsAppPackageName= "com.github.yeeun_yun97.clone.savedforms"
    private val savedFormsAppActivityName= "com.github.yeeun_yun97.clone.savedforms.RegisterActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!checkAppInstalled(savedFormsAppPackageName)){
            registerButton.isEnabled=false
        }

        registerButton.setOnClickListener {
            var compName= ComponentName(savedFormsAppPackageName,savedFormsAppActivityName)
            var intent= Intent().apply{
                action=Intent.ACTION_VIEW
                setComponent(compName)
            }
            startActivity(intent)
        }
    }

    private fun checkAppInstalled(packageName: String): Boolean{
        return try{
            packageManager.getPackageInfo(packageName, 0)
            true
        }catch(e: PackageManager.NameNotFoundException){
            false
        }
    }
}