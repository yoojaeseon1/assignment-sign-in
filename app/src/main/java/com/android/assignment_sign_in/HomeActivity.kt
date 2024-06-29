package com.android.assignment_sign_in

import User
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
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

//        Log.d("home_imnage id", "${R.id.home_image}")
        val image = findViewById<ImageView>(R.id.home_image)
        image.setImageResource(homeImages[homeImages.indices.random()])
//        Log.d("home password", "${intent.getStringExtra("password")}")

        val input_id = findViewById<TextView>(R.id.sign_up_et_id)
        val input_name = findViewById<TextView>(R.id.tv_input_name)
        val input_age = findViewById<TextView>(R.id.tv_input_age)
        val input_mbti = findViewById<TextView>(R.id.tv_input_mbti)

//        val id = intent.getStringExtra("id")
//        val password = intent.getStringExtra("password")
        val user = intent.getParcelableExtra("user", User::class.java)?:User()
        var birthDate = LocalDate.now()
        var age = 0
        if(user.birthYear.isNotEmpty() && user.birthMonth.isNotEmpty() && user.birthDay.isNotEmpty()) {
            birthDate =
                LocalDate.of(user.birthYear.toInt(), user.birthMonth.toInt(), user.birthDay.toInt())
            age = Period.between(birthDate, LocalDate.now()).years
        }
//        Log.d("homeActivity", user.id)
//        Log.d("homeActivity", user.name)
//        Log.d("homeActivity", user.mbti)
        input_id.text = user.id
        input_name.text = user.name
        input_age.text = age.toString()
        input_mbti.text = user.mbti

        val btn_finish = findViewById<ConstraintLayout>(R.id.exit_home_widgets)
        btn_finish.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
//            intent.putExtra("id", user.id)
//            intent.putExtra("password", user.password)
            intent.putExtra("user", user)
            setResult(RESULT_OK, intent)
//            startActivity(intent)
            finish()
        }

//        val layout_exit_home = findViewById<ConstraintLayout>(R.id.exit_home)
//
//        layout_exit_home.setOnClickListener{
//            Log.d("home", "click home")
//        }
    }
}