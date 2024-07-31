 package com.example.onlinequizapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinequizapp.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

 class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var QuizModelClassList:MutableList<QuizModelClass>
    lateinit var adapter:QuizListAdapterClass



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        getAllDataFromFirebase()

    }

     private fun setRecyclerView() {

         binding.dataLoaderPb.visibility=View.GONE
        adapter= QuizListAdapterClass(QuizModelClassList)
         binding.recyViewMain.layoutManager=LinearLayoutManager(this)
         binding.recyViewMain.adapter=adapter

   }

     private fun initViews() {

         QuizModelClassList= mutableListOf()

     }
     private fun getAllDataFromFirebase()
     {
         binding.dataLoaderPb.visibility= View.VISIBLE
/*

//         Android Related Question
         val listOfQuestion= mutableListOf<QuestionModel>()
         listOfQuestion.add(QuestionModel("What is Android ", mutableListOf("Os","tool","Architacture","All Of The Above"),"Os"))
         listOfQuestion.add(QuestionModel("Who is Founder of Android", mutableListOf("Dennis Riche","James Ghoslin ","Bajarane Strostrup","Andy Robin"),"Andy Robin"))
         listOfQuestion.add(QuestionModel("Who own Android ", mutableListOf("Google","Microsoft","Amazon","Flipkard"),"Google"))
         listOfQuestion.add(QuestionModel("Which Assistent Android Will be using", mutableListOf("Siri","cortana","Google Assistent","Alexa"),"Google Assistent"))

         QuizModelClassList.add(QuizModelClass("1", "Android Question", "All Basic Question of Android ", "20",listOfQuestion))


//         Computer Related Question
         val listOfComputerQuestion= mutableListOf<QuestionModel>()
         listOfComputerQuestion.add(QuestionModel("What is Computer", mutableListOf("Machine That save tha Human Time And Efforts","Operating System ","both A and b","None Of the Above"),"Machine That save tha Human Time And Efforts"))
         listOfComputerQuestion.add(QuestionModel("What is the main component of a computer?", mutableListOf("CPU", "Mouse", "Keyboard", "Monitor"), "CPU"))
         listOfComputerQuestion.add(QuestionModel("What does RAM stand for?", mutableListOf("Random Access Memory", "Read Access Memory", "Run Access Memory", "Real Access Memory"), "Random Access Memory"))
         listOfComputerQuestion.add(QuestionModel("Which part of the computer is responsible for processing data?", mutableListOf("CPU", "RAM", "Hard Drive", "Motherboard"), "CPU"))
         listOfComputerQuestion.add(QuestionModel("What is the primary function of an operating system?", mutableListOf("Manage computer hardware", "Create software", "Protect data", "None of the above"), "Manage computer hardware"))

         QuizModelClassList.add(QuizModelClass("2", "Basic Computer Question ", "Basic And Fundamenatal of Computer", "10",listOfComputerQuestion))

*/
         /*     QuizModelClassList.add(QuizModelClass("3", "Engineering Subject", "OSY, DTE, ETI", "15 Min"))
               QuizModelClassList.add(QuizModelClass("4", "Medical Science", "All Medical Science Subject", "30 Min"))
         */

         FirebaseDatabase.getInstance().reference
             .get()
             .addOnSuccessListener { data->
                 if(data.exists())
                 {
                     for (datasnapshot in data.children)
                     {
                         val QuizModel=datasnapshot.getValue(QuizModelClass::class.java)
                         if (QuizModel != null) {
                             QuizModelClassList.add(QuizModel)
                           }
                     }
                 }
                 setRecyclerView()
             }
     }
 }