package com.example.kotlin_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

data class Person(var score: Int, var totalScore: Int)

data class question(val question: String, val answer: Int, val type: String, val score: Int)

val listQuestions = listOf<question>(question(" 1+5", 6, "Easy", 5),
    question("(1+5)/6", 1, "Easy", 5),
    question("(5*6)*200/6", 1000, "Easy", 5),
    question(" 22*22*456/8", 27588, "Medium", 10),
    question("(55*10)/(5*20)", 6, "Easy", 5),
    question("11*11*11", 1331, "Hard", 15),
    question(" 111*111", 12321, "Hard", 15),
    question("791+540", 1331, "Hard", 15),
    question(" 50*49", 2450, "Medium", 5),
    question(" 30*36", 1080, "Medium", 15)
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var currentPerson = Person(0,0)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // access the chronometer from XML file
        val meter = findViewById<Chronometer>(R.id.c_meter)

        val editText = findViewById<EditText>(R.id.editAnswer)
        editText.visibility=View.INVISIBLE

        val btnNext = findViewById<Button>(R.id.nextButton)

        btnNext.visibility = View.INVISIBLE

        var scoreBoard = findViewById<TextView>(R.id.scoreBoard)
        scoreBoard.visibility= View.INVISIBLE

        val final = findViewById<TextView>(R.id.finalScore)

        final.visibility = View.INVISIBLE

        val btnDone1 = findViewById<Button>(R.id.doneButton)
        btnDone1.visibility = View.INVISIBLE

        //access the button using id
        val btn = findViewById<Button>(R.id.btn)
        btn?.setOnClickListener(object : View.OnClickListener {

            var isWorking = false

            override fun onClick(v: View) {

                if (!isWorking) {
                    meter.start()
                    isWorking = true
                } else {
                    meter.stop()
                    isWorking = false
                }


                btn.setText(if (!isWorking) R.string.start else R.string.stop)

                Toast.makeText(this@MainActivity, getString(
                    if (isWorking)
                        R.string.working
                    else
                        R.string.stopped),
                    Toast.LENGTH_SHORT).show()

                val questionTextView = findViewById<TextView>(R.id.question)

                questionTextView.visibility=View.INVISIBLE


                var index: Int = rand(0,8)

                var questionCurrent: question = listQuestions.get(index)


                questionTextView.text=questionCurrent.question;

                questionTextView.visibility = View.VISIBLE

                val editText = findViewById<EditText>(R.id.editAnswer)
                editText.visibility=View.VISIBLE

                btnNext.visibility=View.VISIBLE
            }
        })



        btnNext.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {



                val inputAnswer:Int = editText.text.toString().toInt()

                val finalScore: (Int, Int) -> Int ={ initialScore: Int, addingScore :Int -> initialScore + addingScore}


                val questionTextView = findViewById<TextView>(R.id.question)

                questionTextView.visibility=View.INVISIBLE


                var index: Int = rand(0,8)

                var questionCurrent: question = listQuestions.get(index)


                questionTextView.text=questionCurrent.question;

                questionTextView.visibility = View.VISIBLE

                val editText = findViewById<EditText>(R.id.editAnswer)
                editText.visibility=View.VISIBLE

                if(inputAnswer==questionCurrent.answer){
                    currentPerson.score= finalScore(currentPerson.score, questionCurrent.score)
                    currentPerson.totalScore =finalScore(currentPerson.totalScore, questionCurrent.score)
                }
                else{
                    currentPerson.score= finalScore(0, questionCurrent.score)
                    currentPerson.totalScore =finalScore(currentPerson.totalScore , questionCurrent.score)
                }


                val message = " Current Score = ${currentPerson.score}, Total score = ${currentPerson.totalScore}"

                var scoreBoard = findViewById<TextView>(R.id.scoreBoard)

                scoreBoard.visibility=View.VISIBLE

                scoreBoard.text = message

                val btnDone = findViewById<Button>(R.id.doneButton)
                btnDone.visibility = View.VISIBLE
            }

        })

        val btnDone = findViewById<Button>(R.id.doneButton)

        btnDone.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val editText = findViewById<EditText>(R.id.editAnswer)
                editText.visibility=View.INVISIBLE

                val btnNext = findViewById<Button>(R.id.nextButton)

                btnNext.visibility = View.INVISIBLE

                var scoreBoard = findViewById<TextView>(R.id.scoreBoard)
                scoreBoard.visibility= View.INVISIBLE

                val questionTextView = findViewById<TextView>(R.id.question)

                questionTextView.visibility=View.INVISIBLE

                final.visibility= View.VISIBLE

                final.text = "Final Score= ${currentPerson.score/currentPerson.totalScore*100}%"
            }

        })
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

//    fun summingScore(initialScore:Int, addingScore:Int) :Int{


    }


//
//    private fun changeScore(view: View){
//
////
////        val editText= findViewById<EditText>(R.id.nickname_edit)
////        val nicknameTextView = findViewById<TextView>(R.id.nickNameText)
////
////        nickNameText.text = editText.text
////
////        editText.visibility = View.GONE
////
////        view.visibility= View.GONE
////
////        nicknameTextView.visibility = View.VISIBLE
////
////        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
////        imm.hideSoftInputFromWindow(view.windowToken, 0)
//
//
//    }





