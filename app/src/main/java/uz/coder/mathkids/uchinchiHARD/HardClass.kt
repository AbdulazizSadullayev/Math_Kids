package uz.coder.mathkids.uchinchiHARD

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
import uz.coder.mathkids.birinchiEASY.PlayActivity
import uz.coder.mathkids.databinding.WinLayoutBinding
import uz.coder.mathkids.ikkinchiMEDIUM.PlayActivity2
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class HardClass : AppCompatActivity() {

    var TimeTextView3: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var btn00: Button? = null
    var btn11: Button? = null
    var btn22: Button? = null
    var btn33: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random = Random()
    var a:Int?= 0
    var b:Int? = 0
    var indexofCurrentAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_class)
        val intent3 = intent.getStringExtra("key")
        if (intent3 != null) {
            cals = intent3
        }

        setUI()
        start()
    }

    fun setUI(){
        TimeTextView3 = findViewById(R.id.TimeTextView3)
        QuestionTextText = findViewById(R.id.QuestionTextText3)
        ScoreTextView = findViewById(R.id.ScoreTextView3)
        AlertTextView = findViewById(R.id.AlertTextView3)

        btn00 = findViewById(R.id.button00)
        btn11 = findViewById(R.id.button11)
        btn22 = findViewById(R.id.button22)
        btn33 = findViewById(R.id.button33)
    }



    @SuppressLint("SetTextI18n")
    fun NextQuestion(cal:String){
        a = random.nextInt(1000)
        b = random.nextInt(1000)
        QuestionTextText!!.text = "$a $cal $b"
        indexofCurrentAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..5){
            if (indexofCurrentAnswer == i){
                when(cal){
                    "+"->{answers.add((a!! + b!!))}
                    "-"->{answers.add((a!! - b!!))}
                    "*"->{answers.add((a!! * b!!))}
                    "/"->{
                        try {
                            answers.add((a!! / b!!))
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }

                }
            }
            else{
                var wrongAnswer = random.nextInt(50000)
                try {
                    while (
                        wrongAnswer == a!! + b!!
                        || wrongAnswer == a!! - b!!
                        || wrongAnswer == a!! * b!!
                        || wrongAnswer == a!! / b!!
                    ){
                        wrongAnswer = random.nextInt(50000)

                    }
                    answers.add(wrongAnswer)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }


        try {
            btn00!!.text = "${answers[0]}"
            btn11!!.text = "${answers[1]}"
            btn22!!.text = "${answers[2]}"
            btn33!!.text = "${answers[4]}"
        }catch (e:Exception){
            e.printStackTrace()
        }



    }


    private fun start() {
        NextQuestion(cals)
        countDownTimer = object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                TimeTextView3!!.text =(p0 / 1000).toString() + "s"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                openDialog().toString()
                TimeTextView3!!.text = "Time Up !"
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
            val intent = Intent(this, PlayActivity3::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()




    }
}