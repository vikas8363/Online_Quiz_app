package com.example.onlinequizapp

data class QuizModelClass(
    val id:String,
    val title:String,
    val subtitle:String,
    val time:String,
    val questionList:List<QuestionModel>,

)
{
    constructor():this("","","","", emptyList())
    {

    }
}
/*New Question Data Class Which Will Accept the question , multiple Option of the String formate
  and correct option.*/

data class QuestionModel(
    val question:String,
    val option:List<String>,
    val correct:String,

)
{
    constructor():this("", emptyList(),"")
}