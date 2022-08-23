package jp.co.casareal.simpleflowapisample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow

class MyViewModel : ViewModel() {
    val displayCount = MutableLiveData<Int>(0)
    private var counter = 0
    val myFlow = flow<Int> {
        counter++
        emit(counter)
    }
}