package com.project.Instargram.kotlin.src.login.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterBirthDateBinding
import com.project.Instargram.kotlin.databinding.DialogDatePickerBinding

class EnterBirthDateActivity: BaseActivity<ActivityEnterBirthDateBinding>(ActivityEnterBirthDateBinding::inflate) {

    private val KEY_SEND = "birthday"
    //date format yyyy-mm-dd
    private lateinit var datePicked: String
    private lateinit var today: String
    private var age: Int = 0
//    var dateFormat = SimpleDateFormat("YYYY/MM/DD")

    //여전히 문제
    //TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        today = "2023-3-8"
        datePicked = today

//        getTodayDate()
//        Log.d(TAG, "onCreate: "+todayFormat)
//        datePickedFormat = todayFormat

//        updateBirthDate()
    }

    override fun onResume() {
        super.onResume()

        binding.txtBithDate.text = datePicked

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnDatePicker.setOnClickListener {
            openDatePickerDialog()
        }

        binding.btnNext.setOnClickListener {
            val data = binding.txtBithDate.text.toString()
            saveFromEditText(KEY_SEND, data)
            startActivity(Intent(this, EnterNicknameActivity::class.java))
        }
    }


    private fun openDatePickerDialog() {
        val dialog = Dialog(this)
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true)
        //Mention the name of the layout of your custom dialog.
        val binding = DialogDatePickerBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)


        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnSet.setOnClickListener {
            datePicked = binding.timepicker.year.toString() +"-" + binding.timepicker.month.toString() +"-" + binding.timepicker.dayOfMonth
            val thisYear = today.slice(0..3).toInt()
            val birthYear = datePicked.slice(0..3).toInt()
            age = thisYear - birthYear
            this.binding.txtBithDate.text = datePicked
            this.binding.txtAge.text = "생일("+age.toString() + "세)"
            dialog.dismiss()
        }

        dialog.show()
    }







//
//    private fun getTodayDate() {
//        val today: Date = Date()
//        todayFormat = DateFormat.getDateInstance(DateFormat.SHORT).format(today)
//    }
//
//    private fun compareDate(start: String, end: String) {
//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYY/MM/DD")
//        val start: LocalDate = LocalDate.parse(start, formatter)
//        val end: LocalDate = LocalDate.parse(end, formatter)
//
//        ChronoUnit.YEARS.between(end, start)
//
//    }
//
//    private fun changeDateFormat(date: String): String {
//        return date.slice(0..3)+"년"+date.slice(5..6)+"월" +date.slice(7..8)+ "일"
//    }
}