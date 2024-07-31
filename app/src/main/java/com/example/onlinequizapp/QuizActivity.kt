package com.example.onlinequizapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.onlinequizapp.databinding.ActivityQuizBinding
import com.example.onlinequizapp.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var binding:ActivityQuizBinding

    companion object{
        var QuestionModelList:List<QuestionModel> =listOf()
        var time:String=""

    }
    var currentIndex=0
    var selectedOption=""
    var score:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadQuestion()
        startimer()

        binding.apply {
            btn1.setOnClickListener(this@QuizActivity)
            btn0.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            btnNext.setOnClickListener(this@QuizActivity)

        }


    }

    private fun startimer()
    {
        val totalTimer= time

        var totalTimerInMills= time.toInt()*60*1000L
        object :CountDownTimer(totalTimerInMills,1000L)
        {
            override fun onTick(millisUntilFinished: Long) {
                val second=millisUntilFinished/1000
                val minuts=second/60
                var remainingSecond=second % 60
                binding.lblTimeIndicator.text= String.format("%02d:%02d",minuts,remainingSecond)


            }

            override fun onFinish() {
            }

        }.start()
    }

    private fun loadQuestion()
    {
        selectedOption=""
//        Check whether the Question is last or not :
        if(currentIndex== QuestionModelList.size)
        {
            finishQuiz()
            return
        }

        binding.apply {

            lblQuestionIndicator.text="Question ${currentIndex+1}/${QuestionModelList.size}"
            quizQuestionProgressIndicator.progress=
                (currentIndex.toFloat()/ QuestionModelList.size.toFloat()*100).toInt()
           // lblTimeIndicator.text= time

            lblMainQuestion.text= QuestionModelList[currentIndex].question
            btn0.text= QuestionModelList[currentIndex].option[0]
            btn1.text= QuestionModelList[currentIndex].option[1]
            btn2.text= QuestionModelList[currentIndex].option[2]
            btn3.text= QuestionModelList[currentIndex].option[3]

        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {



        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))

        }

        val ClickedButton=v as Button
        if(ClickedButton.id==R.id.btn_next)
        {
            if(selectedOption==QuestionModelList[currentIndex].correct)
            {
                score++
                Log.d("Scrore of User ",score.toString())
            }

            currentIndex++
            loadQuestion()
        }
        else{
//            Any One Option Button is Clicked
            ClickedButton.setBackgroundColor(getColor(R.color.orange))
            selectedOption=ClickedButton.text.toString()

        }

    }

    private fun finishQuiz()
    {
        val totalQuestion= QuestionModelList.size
        val percentage=((score.toFloat() / totalQuestion.toFloat())*100).toInt()

        val dialogBinding=ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress=percentage
            lblScoreProgress.text="${percentage}"

            if(percentage>60)
            {
                scoreTitle.text="Congrats! You Have Passed"
                scoreTitle.setTextColor(Color.BLUE)
            }
            else
            {
                scoreTitle.text="Bad Luck! please Try Again"
                scoreTitle.setTextColor(Color.RED)
            }

            lblScoreSubtitle.text="${score} Out of ${totalQuestion} Are Correct"
            btnFinish.setOnClickListener{
                finish()

            }

            AlertDialog.Builder(this@QuizActivity)
                .setView(dialogBinding.root)
                .setCancelable(false)
                .show()

        }

    }
}