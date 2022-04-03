package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var counter = 0 //liczba strzałów
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toast.makeText(applicationContext, "Twoja liczba jest mniejsza", Toast.LENGTH_SHORT).show()     //robi toast - ten mały komunkacik
        var num = Random.nextInt(0,20)      //losuje liczbe losową
        val button = findViewById<Button>(R.id.button)      //łączy się z guzikiem ref do guzika
        val resetpoints = findViewById<Button>(R.id.resetpoints)
        val newgame = findViewById<Button>(R.id.newgame)
        val editTextNumber = findViewById<TextView>(R.id.editTextNumber)
        val numpoints = findViewById<TextView>(R.id.numpoints)
        val numguesses = findViewById<TextView>(R.id.numguesses)
        resetpoints.setOnClickListener {
            setRecord(0)
            numpoints.text = getRecord().toString()
        }
        newgame.setOnClickListener {
            num = Random.nextInt(0,20)
            counter = 0
            numguesses.text = counter.toString()
        }

            button.setOnClickListener {
                if (editTextNumber.text == null) {
                    Toast.makeText(applicationContext, "Wpisz coś", Toast.LENGTH_LONG).show()
                } else {
                    if (editTextNumber.text != "") {
                        val guess = editTextNumber.text
                        var pom = 0
                        when (guess.toString()) {
                            "" -> pom = -1
                            else -> { // Note the block
                                var a = guess.toString()
                                pom = a.toInt()
                            }
                        }
                        if ((pom > 20) or (pom < 0)) {
                            Toast.makeText(
                                applicationContext,
                                "Number out of range [0;20]",
                                Toast.LENGTH_SHORT
                            ).show()
                            editTextNumber.text = null
                        } else {
                            editTextNumber.text = null
                            counter += 1
                            if(counter>=10){
                                newgame.performClick()
                                //komunikat o przegranej
                                val builder = AlertDialog.Builder(this@MainActivity)
                                builder.setTitle("You lest!")//title)
                                builder.setMessage("Too many guesses")
                                builder.setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->}
                                val dialog: AlertDialog = builder.create()
                                dialog.show()
                            }
                            numguesses.text = counter.toString()
                            if (guess.toString() == num.toString()) {
                                var score = getRecord().toString().toInt()
                                when (counter.toString().toInt()) {
                                    1 -> score+=5
                                    2, 3, 4 -> score+=3
                                    5, 6 -> score+=2
                                    else -> { // Note the block
                                        score+=1
                                    }
                                }
                                //score += 1
                                setRecord(score)
                                numpoints.text = getRecord().toString()
                                num = Random.nextInt(0, 20)
                                Toast.makeText(applicationContext, "Perfect!", Toast.LENGTH_SHORT)
                                    .show()
                                //komunikat o wygranej
                                val builder = AlertDialog.Builder(this@MainActivity)
                                builder.setTitle("You guessed!")//title)
                                builder.setMessage("In $counter tries PS NOWA wylosowana liczba: $num")
                                builder.setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->}
                                val dialog: AlertDialog = builder.create()
                                dialog.show()
                                counter = 0
                                numguesses.text = counter.toString()
                            } else{ // guess.toString().toInt()

                                if(pom>num.toString().toInt()){Toast.makeText(applicationContext, "Guess bigger than the drawn number.", Toast.LENGTH_SHORT).show()}
                                else{
                                    Toast.makeText(applicationContext, "Guess smaller than the drawn number.", Toast.LENGTH_SHORT).show()}
                            }
                        }
                    } else {
                    }


                }
                //editTextNumber.listener
            }



        //START - tu tworzy komunikat z wiadomościa i przyciskiem ok do zamknięcia jej
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("To jest tajny komunikat")//title)
        builder.setMessage("To jest wylosowana liczba: $num")
        builder.setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
        //STOP
    }
    fun setRecord(score: Int){            //do zapisywania wyniku w shared reference
        val sharedScore = this.getSharedPreferences("com.example.myapplication.shared",0)
        val edit = sharedScore.edit()
        edit.putInt("score", score)
        edit.apply()
    }
    fun getRecord(): Int {   //do odczytywania wyniku z shared reference i wyświetlania (później)
        var score = 0
        val sharedScore = this.getSharedPreferences("com.example.myapplication.shared",0)
        score = sharedScore.getInt("score", 0)
        return score
    }

}
