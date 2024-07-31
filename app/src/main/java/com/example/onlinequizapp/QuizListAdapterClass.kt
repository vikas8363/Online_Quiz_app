package com.example.onlinequizapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinequizapp.databinding.QuizeItemLayoutBinding

class QuizListAdapterClass(private val quizeModelList: List<QuizModelClass>) :
    RecyclerView.Adapter<QuizListAdapterClass.quizViewHolder>() {


    class quizViewHolder(val binding:QuizeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(model:QuizModelClass)
        {
//            Bind All the Views:
            binding.apply {
                lblQuizTitle.text=model.title
                lblQuizSubTitle.text=model.subtitle
                lblQuestionTime.text=model.time+" Min"

                root.setOnClickListener {
                    val intent=Intent(root.context,QuizActivity::class.java)
                    QuizActivity.QuestionModelList=model.questionList
                    QuizActivity.time=model.time
                    root.context.startActivity(intent)

                }


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): quizViewHolder {

        val binding=QuizeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return quizViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return quizeModelList.size
    }

    override fun onBindViewHolder(holder: quizViewHolder, position: Int) {

        holder.bind(quizeModelList[position])

    }
}