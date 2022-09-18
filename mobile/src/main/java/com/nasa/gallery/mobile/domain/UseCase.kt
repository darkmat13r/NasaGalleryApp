package com.nasa.gallery.mobile.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import com.nasa.gallery.mobile.result.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception

abstract class UseCase<T> (private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke()  = flow {
       try{
           emit(Result.Loading)
           emit(Result.Success(execute()))
       }catch(ex : Exception){
           emit(Result.Error(ex))
       }
    }.flowOn(dispatcher)

    abstract suspend fun execute(): T
}