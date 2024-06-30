package com.android.assignment_sign_in

import User
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btn_sign_in = findViewById<Button>(R.id.btn_sign_in)
        val btn_sign_up = findViewById<Button>(R.id.btn_sign_up_page)
        var user = User()
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result -> if(result.resultCode == RESULT_OK) {
                user = result.data?.getParcelableExtra("user", User::class.java)?:User()
                Log.d("btn_sign_in_", user.toString())
                val et_id = findViewById<TextView>(R.id.et_sign_in_id)
                et_id.text = user?.id
                val et_password = findViewById<TextView>(R.id.et_sign_in_pw)
                et_password.text = user?.password
            }
        }

        btn_sign_in.setOnClickListener {
            val et_id = findViewById<EditText>(R.id.et_sign_in_id)
            val et_pw = findViewById<EditText>(R.id.et_sign_in_pw)
            Log.d("btn_sign_in_", user.toString())
            if(user.id.isNotEmpty())
                user = User(user.name, user.id,user.password, user.birthYear, user.birthMonth, user.birthDay, user.mbti)
            else {
                user.id = et_id.text.toString()
                user.password = et_pw.text.toString()
            }

            if(et_id.text.length == 0 || et_pw.text.length == 0)
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                intent.putExtra("user", user)
                resultLauncher.launch(intent)
            }
        }

        btn_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}