package com.example.mywork122.domain.usecase

import com.example.mywork122.domain.model.Targets
import com.example.mywork122.domain.repository.Repository


class OnSubmitTargets(private val repository: Repository) {

    suspend operator fun invoke(dayTar:String, weekTar:String):Boolean{
        try {
            val a = dayTar.toFloat()
            val b = weekTar.toFloat()
            if(a <= 0 || b<=0)
                return false
            else {
                repository.insertTargets(Targets(a, b))
            }
        }catch (e:Exception){
           // Log.i("rr","the : e:$e")
            return false
        }

        return true
    }

}