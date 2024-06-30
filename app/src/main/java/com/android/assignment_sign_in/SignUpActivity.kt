package com.android.assignment_sign_in

import User
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_sign_up = findViewById<Button>(R.id.btn_sign_up_completed)
        val btn_sign_in_mbti_e_i = findViewById<Button>(R.id.btn_sign_up_mbti_e_i)
        val btn_sign_in_mbti_n_s = findViewById<Button>(R.id.btn_sign_up_mbti_n_s)
        val btn_sign_in_mbti_f_t = findViewById<Button>(R.id.btn_sign_up_mbti_f_t)
        val btn_sign_in_mbti_p_j = findViewById<Button>(R.id.btn_sign_up_mbti_p_j)

        var is_mbti_e = true
        var is_mbti_n = true
        var is_mbti_f = true
        var is_mbti_p = true
        btn_sign_in_mbti_e_i.setOnClickListener {
            if(is_mbti_e)
                btn_sign_in_mbti_e_i.text = "I"
            else
                btn_sign_in_mbti_e_i.text = "E"
            is_mbti_e = !is_mbti_e
        }

        btn_sign_in_mbti_n_s.setOnClickListener {
            if(is_mbti_n)
                btn_sign_in_mbti_n_s.text = "S"
            else
                btn_sign_in_mbti_n_s.text = "N"
            is_mbti_n = !is_mbti_n
        }

        btn_sign_in_mbti_f_t.setOnClickListener {
            if(is_mbti_f)
                btn_sign_in_mbti_f_t.text = "T"
            else
                btn_sign_in_mbti_f_t.text = "F"
            is_mbti_f = !is_mbti_f
        }

        btn_sign_in_mbti_p_j.setOnClickListener {
            if(is_mbti_p)
                btn_sign_in_mbti_p_j.text = "J"
            else
                btn_sign_in_mbti_p_j.text = "P"
            is_mbti_p = !is_mbti_p
        }

        val picker = findViewById<DatePicker>(R.id.dp_sign_up)
        btn_sign_up.setOnClickListener {
            val et_name = findViewById<EditText>(R.id.et_sign_up_name)
            val et_id = findViewById<EditText>(R.id.et_sign_up_id)
            val et_password= findViewById<EditText>(R.id.et_sign_up_pw)
            val birthYear = picker.year.toString()
            val birthMonth = (picker.month+1).toString()
            val birthDay = picker.dayOfMonth.toString()
            val mbti = btn_sign_in_mbti_e_i.text.toString() +
                    btn_sign_in_mbti_n_s.text.toString() +
                    btn_sign_in_mbti_f_t.text.toString() +
                    btn_sign_in_mbti_p_j.text.toString()

            Log.d("mbti", mbti)
            if(et_name.text.length == 0 || et_id.text.length ==0 ||et_password.text.length == 0) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val sign_up_user = User(et_name.text.toString(), et_id.text.toString(), et_password.text.toString(), birthYear, birthMonth, birthDay, mbti)
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", et_id.text.toString())
                intent.putExtra("password", et_password.text.toString())
                Log.d("sign_up", sign_up_user.id)
                Log.d("sign_up", sign_up_user.password)

                intent.putExtra("user", sign_up_user)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}