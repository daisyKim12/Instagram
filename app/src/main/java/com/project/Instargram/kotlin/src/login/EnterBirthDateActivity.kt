package com.project.Instargram.kotlin.src.login

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterBirthDateBinding
import com.project.Instargram.kotlin.databinding.DialogDatePickerBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class EnterBirthDateActivity: BaseActivity<ActivityEnterBirthDateBinding>(ActivityEnterBirthDateBinding::inflate) {

    private val KEY_SEND = "birth_date"
    private lateinit var datePicked: String
//    private lateinit var today: String
//    var dateFormat = SimpleDateFormat("YYYY/MM/DD")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datePicked = "2023년3월8일"

//        getTodayDate()
//        Log.d(TAG, "onCreate: "+todayFormat)
//        datePickedFormat = todayFormat

//        updateBirthDate()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

//        updateBirthDate()
//        binding.txtBithDate.text = datePicked
    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnDatePicker.setOnClickListener {
            openDatePickerDialog()
        }

        binding.btnNext.setOnClickListener {
            val data = binding.txtBithDate.text.toString()
            saveFromEditText(KEY_SEND, data)
            //startActivity(Intent(this, NextActivity::class.java))
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
            datePicked = binding.timepicker.year.toString() +"년" + binding.timepicker.month.toString() +"월" + binding.timepicker.dayOfMonth + "일"

            dialog.dismiss()
        }

        dialog.show()
    }






    //    private fun updateBirthDate() {
//        binding.txtBithDate.text = changeDateFormat(datePickedFormat)
//        //val age = compareDate(todayFormat, datePickedFormat).toString()
//        //binding.txtAge.text = "생일("+age+")"
//    }
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