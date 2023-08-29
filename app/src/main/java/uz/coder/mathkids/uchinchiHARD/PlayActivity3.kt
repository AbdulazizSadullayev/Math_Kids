package uz.coder.mathkids.uchinchiHARD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import uz.coder.mathkids.databinding.ActivityPlay3Binding



class PlayActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityPlay3Binding
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlay3Binding.inflate(LayoutInflater.from(this),null,false)
        setContentView(binding.root)

        val intent3 = intent.getStringExtra("qiyin")
        if (intent3 != null) {
            cals = intent3
        }

        binding.apply {
            addtion.setOnClickListener{
                val calInt = Intent(this@PlayActivity3, HardClass::class.java)
                calInt.putExtra("key","+")
                startActivity(calInt)
            }

            sub.setOnClickListener{
                val calInt = Intent(this@PlayActivity3, HardClass::class.java)
                calInt.putExtra("key","-")
                startActivity(calInt)
            }

            multi.setOnClickListener{
                val calInt = Intent(this@PlayActivity3, HardClass::class.java)
                calInt.putExtra("key","*")
                startActivity(calInt)
            }

            divie.setOnClickListener{
                val calInt = Intent(this@PlayActivity3, HardClass::class.java)
                calInt.putExtra("key ","/")
                startActivity(calInt)
            }
        }
        }

}