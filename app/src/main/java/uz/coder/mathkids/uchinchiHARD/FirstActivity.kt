package uz.coder.mathkids.uchinchiHARD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import uz.coder.mathkids.birinchiEASY.PlayActivity
import uz.coder.mathkids.databinding.ActivityFirstBinding
import uz.coder.mathkids.ikkinchiMEDIUM.PlayActivity2

class FirstActivity : AppCompatActivity() {
    lateinit var binding:ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(LayoutInflater.from(this),null,false)
        setContentView(binding.root)
        binding.apply {
            easy.setOnClickListener {
                val intent1 = Intent(this@FirstActivity, PlayActivity::class.java)
                intent1.putExtra("oson", "easy")
                startActivity(intent1)
            }

            medium.setOnClickListener {
                    val intent2 = Intent(this@FirstActivity, PlayActivity2::class.java)
                    intent2.putExtra("orta","medium")
                    startActivity(intent2)
                }

            hardd.setOnClickListener {
                val intent= Intent(this@FirstActivity,PlayActivity3::class.java)
                intent.putExtra("qiyin", "hard")
                startActivity(intent)
            }

        }

    }
} 