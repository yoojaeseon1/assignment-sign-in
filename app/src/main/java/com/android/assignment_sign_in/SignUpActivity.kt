package com.android.assignment_sign_in

import User
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.YearMonth

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

        val btn_sign_in = findViewById<Button>(R.id.btn_sign_up)
        val btn_sign_in_mbti_e_i = findViewById<Button>(R.id.sign_in_btn_mbti_e_i)
        val btn_sign_in_mbti_n_s = findViewById<Button>(R.id.sign_in_btn_mbti_n_s)
        val btn_sign_in_mbti_f_t = findViewById<Button>(R.id.sign_in_btn_mbti_f_t)
        val btn_sign_in_mbti_p_j = findViewById<Button>(R.id.sign_in_btn_mbti_p_j)

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

//        val number_picker_year = findViewById<NumberPicker>(R.id.sign_up_np_year)
//        val number_picker_month = findViewById<NumberPicker>(R.id.sign_up_np_month)
//        val number_picker_day = findViewById<NumberPicker>(R.id.sign_up_np_day)
//
//        number_picker_year.minValue = 1900
//        number_picker_year.maxValue = LocalDate.now().year
//        number_picker_month.minValue = 1
//        number_picker_month.maxValue = 12
//        number_picker_day.minValue = 1
//
//
//
//        number_picker_month.setOnClickListener{
//            val day = YearMonth.of(number_picker_year.value, number_picker_month.value)
////        val day = YearMonth.of(,2)
//            number_picker_day.maxValue = day.lengthOfMonth()
//            Log.d("year", day.lengthOfMonth().toString())
//        }

//        val rootView = menuInflater.inflate(R.layout.setting_birth)

//        val date = DatePickerDialog.OnDateSetListener{
//
//            val cal = Calendar.getInstance()
//            var data = DatePickerDialog.OnDateSetListener{ view, year, month, day ->
//
//            }
//
//        }

        val picker = findViewById<DatePicker>(R.id.sign_up_dp)
//        val sign_up_btn_birthday = findViewById<Button>(R.id.sign_up_btn_birthday)
//        val minDate = Calendar.getInstance()
//        val maxDate = Calendar.getInstance()
//
//        minDate.set(2020,1-1,1)
//
//        picker.minDate = minDate.time.time
//
//        maxDate.set(2024,1-1,1)
//
//        picker.setMaxDate(maxDate.timeInMillis)

//        sign_up_btn_birthday.setOnClickListener{
//
//        }

//        val calendar = Calendar.getInstance()

//        var year = calendar.get(Calendar.YEAR)
//        var month = calendar.get(Calendar.MONTH)
//        var day = calendar.get(Calendar.DAY_OF_MONTH)



        btn_sign_in.setOnClickListener {
            val et_name = findViewById<EditText>(R.id.sign_up_et_name)
            val et_id = findViewById<EditText>(R.id.sign_up_et_id)
            val et_password= findViewById<EditText>(R.id.sign_up_et_pw)
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
//                startActivity(intent)
                finish()
            }
        }
    }
}