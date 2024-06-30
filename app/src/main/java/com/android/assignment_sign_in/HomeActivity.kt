package com.android.assignment_sign_in

import User
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.Period

class HomeActivity : AppCompatActivity() {

    private val homeImages = mutableListOf<Int>()

    init {
        homeImages.add(R.drawable.ryan1)
        homeImages.add(R.drawable.ryan2)
        homeImages.add(R.drawable.ryan3)
        homeImages.add(R.drawable.ryan4)
        homeImages.add(R.drawable.ryan5)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image = findViewById<ImageView>(R.id.iv_home_title)
        image.setImageResource(homeImages[homeImages.indices.random()])
        val input_id = findViewById<TextView>(R.id.tv_home_id_value)
        val input_name = findViewById<TextView>(R.id.tv_home_name_value)
        val input_age = findViewById<TextView>(R.id.tv_home_age_value)
        val input_mbti = findViewById<TextView>(R.id.tv_home_mbti_value)

        val user = intent.getParcelableExtra("user", User::class.java)?:User()
        var birthDate = LocalDate.now()
        var age = 0
        if(user.birthYear.isNotEmpty() && user.birthMonth.isNotEmpty() && user.birthDay.isNotEmpty()) {
            birthDate =
                LocalDate.of(user.birthYear.toInt(), user.birthMonth.toInt(), user.birthDay.toInt())
            age = Period.between(birthDate, LocalDate.now()).years
        }
        input_id.text = user.id
        input_name.text = user.name
        input_age.text = age.toString()
        input_mbti.text = user.mbti

        val btn_finish = findViewById<ConstraintLayout>(R.id.btn_exit_home)
        btn_finish.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("user", user)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}