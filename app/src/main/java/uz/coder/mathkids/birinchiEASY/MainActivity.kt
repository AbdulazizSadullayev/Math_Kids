package uz.coder.mathkids.birinchiEASY

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

class MainActivity : AppCompatActivity() {
    var TimeTextView:TextView? = null
    var QuestionTextText:TextView? = null
    var ScoreTextView:TextView? = null
    var AlertTextView:TextView? = null
    var FinalScoreTextView:TextView? = null
    var btn0:Button? = null
    var btn1:Button? = null
    var btn2:Button? = null
    var btn3:Button? = null
    var countDownTimer:CountDownTimer? = null
    var random:Random = Random()
    var a:Int?= 0
    var b:Int? = 0
    var indexofCurrentAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calInt = intent.getStringExtra("key")
        if (calInt != null) {
            cals = calInt
        }
        TimeTextView = findViewById(R.id.TimeTextView)
        QuestionTextText = findViewById(R.id.QuestionTextText)
        ScoreTextView = findViewById(R.id.ScoreTextView)
        AlertTextView = findViewById(R.id.AlertTextView)

        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        start()
    }



    fun NextQuestion(cal:String){
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionTextText!!.text = "$a $cal $b"
        indexofCurrentAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3){
            if (indexofCurrentAnswer == i){
                when(cal){
                    "+"->{answers.add(a!! + b!!)}
                    "-"->{answers.add(a!! - b!!)}
                    "*"->{answers.add(a!! * b!!)}
                    "/"->{
                        try {
                            answers.add(a!! / b!!)
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }

                }
            }
            else{
                var wrongAnswer = random.nextInt(20)
                try {
                    while (
                        wrongAnswer == a!! + b!!
                        || wrongAnswer == a!! - b!!
                        || wrongAnswer == a!! * b!!
                        || wrongAnswer == a!! / b!!
                    ){
                        wrongAnswer = random.nextInt(20)

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
                TimeTextView!!.text = "Time Up !"
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
        ScoreTextView?.text = "$points/$totalQuestions"
        countDownTimer?.start()

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
        dialog.show()



        binding.FinalScoreTextView.text = ("$points/$totalQuestions"?:0).toString()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }

       dialog.show()


    }


}