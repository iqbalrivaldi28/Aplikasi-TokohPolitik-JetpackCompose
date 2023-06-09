package com.example.composeiqbal.Repo


import com.example.composeiqbal.models.TokohPolitik
import com.example.composeiqbal.models.TokohPolitikData
import kotlinx.coroutines.flow.flow


class TokohPolitikRepository {
    private val dummyTokoh = mutableListOf<TokohPolitik>()

    init {
        if (dummyTokoh.isEmpty()) {
            TokohPolitikData.dummyData.forEach {
                dummyTokoh.add(it)
            }
        }
    }

    fun getTokohById(TokohId: Int): TokohPolitik {
        return dummyTokoh.first {
            it.id == TokohId
        }
    }

    fun searchPlayer(query: String) = flow {
        val data = dummyTokoh.filter {
            it.nama.contains(query, ignoreCase = true)
        }
        emit(data)
    }



    companion object {
        @Volatile
        private var instance: TokohPolitikRepository? = null

        fun getInstance(): TokohPolitikRepository =
            instance ?: synchronized(this) {
                TokohPolitikRepository().apply {
                    instance = this
                }
            }
    }

}