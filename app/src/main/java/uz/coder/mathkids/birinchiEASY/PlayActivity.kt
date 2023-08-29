package uz.coder.mathkids.birinchiEASY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.coder.mathkids.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    lateinit var binding:ActivityPlayBinding
    var cals = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent1 = intent.getStringExtra("oson")
        if (intent1 != null) {
            cals = intent1
        }



        binding.apply {
            addtion.setOnClickListener{
                val calInt = Intent(this@PlayActivity, MainActivity::class.java)
                calInt.putExtra("key","+")
                startActivity(calInt)
            }

            sub.setOnClickListener{
                val calInt = Intent(this@PlayActivity, MainActivity::class.java)
                calInt.putExtra("key","-")
                startActivity(calInt)
            }

            multi.setOnClickListener{
                val calInt = Intent(this@PlayActivity, MainActivity::class.java)
                calInt.putExtra("key","*")
                startActivity(calInt)
            }

            divie.setOnClickListener{
                val calInt = Intent(this@PlayActivity, MainActivity::class.java)
                calInt.putExtra("key","/")
                startActivity(calInt)
            }

        }
    }
}