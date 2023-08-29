package uz.coder.mathkids.ikkinchiMEDIUM

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import uz.coder.mathkids.R
import uz.coder.mathkids.databinding.WinLayoutBinding
import java.util.Random

class MediumClass : AppCompatActivity() {
    var TimeTextView: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random = Random()
    var a1:Int?= 0
    var b1:Int? = 0
    var indexofCurrentAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_class)
        val intent2 = intent.getStringExtra("key")
        if (intent2 != null) {
            cals = intent2
        }
        setIU()
        start()
    }
    fun setIU(){
        TimeTextView = findViewById(R.id.TimeTextView2)
        QuestionTextText = findViewById(R.id.QuestionTextText2)
        ScoreTextView = findViewById(R.id.ScoreTextView2)
        AlertTextView = findViewById(R.id.AlertTextView2)

        btn0 = findViewById(R.id.button02)
        btn1 = findViewById(R.id.button12)
        btn2 = findViewById(R.id.button22)
        btn3 = findViewById(R.id.button32)
    }



    @SuppressLint("SetTextI18n")
    fun NextQuestion(cal:String){
        a1 = random.nextInt(150)
        b1 = random.nextInt(150)
        QuestionTextText!!.text = "$a1 $cal $b1"
        indexofCurrentAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..5){
            if (indexofCurrentAnswer == i){
                when(cal){
                    "+"->{answers.add(a1!! + b1!!)}
                    "-"->{answers.add(a1!! - b1!!)}
                    "*"->{answers.add(a1!! * b1!!)}
                    "--"->{
                        try {
                            answers.add(a1!! / b1!!)
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }



                /*{answers.add(a1!! / b1!!)}*/




                }
            }
            else{
                var wrongAnswer = random.nextInt(3000)
                try {
                    while (
                        wrongAnswer == a1!! + b1!!
                        || wrongAnswer == a1!! - b1!!
                        || wrongAnswer == a1!! * b1!!
                        || wrongAnswer == try {
                            a1!! / b1!!
                        }catch (e:Exception){
                            e.printStackTrace()
                        }

                    ){
                        wrongAnswer = random.nextInt(3000)

                    }
                    answers.add(wrongAnswer)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }


        try {
            btn0!!.text = "${answers[0]}"
            btn1!!.text = "${answers[1]}"
            btn2!!.text = "${answers[2]}"
            btn3!!.text = "${answers[3]}"
        }catch (e:Exception){
            e.printStackTrace()
        }



    }


    private fun start() {
        NextQuestion(cals)
        countDownTimer = object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                TimeTextView!!.text =(p0 / 1000).toString() + "s"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                openDialog().toString()
                TimeTextView?.text = "Time Up !"
            }

        }.start()

    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexofCurrentAnswer.toString() == view?.tag.toString()) {
            points++
            AlertTextView?.text = "Cross"
        }
        else{
            AlertTextView?.text = "Wrong"
        }
        ScoreTextView?.text = "$points/$totalQuestions"
        NextQuestion(cals)
    }

    fun playAgain(view: View?){
        points = 0
        totalQuestions = 0
        ScoreTextView!!.text = "$points/$totalQuestions"
        countDownTimer!!.start()

    }
    private fun openDialog() {
        val binding = WinLayoutBinding.inflate(LayoutInflater.from(this),null,false)



        val dialog = AlertDialog.Builder(this).create()
        dialog.setCancelable(false)
        dialog.setView(binding.root)
        binding.buttonPlayAgain.setOnClickListener {

            playAgain(binding.root)
            dialog.dismiss()

        }



        binding.FinalScoreTextView.text = ("$points/$totalQuestions"?:0).toString()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, PlayActivity2::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()


    }


}