package jp.co.casareal.simpleflowapisample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import jp.co.casareal.simpleflowapisample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        val viewModel by viewModels<MyViewModel>()

        binding.model = viewModel

        binding.btnCountUpWithLambda.setOnClickListener {
            lifecycleScope.launch {
                viewModel.myFlow
                    .catch {
                        Log.e("TEST", "エラーが発生しました。 ", it)
                    }
                    .collect {
                        viewModel.displayCount.postValue(it)
                    }
            }
        }
        binding.btnCountUpWithoutLambda.setOnClickListener {
            lifecycleScope.launch{
                viewModel.myFlow.collect()
            }
        }
    }
}
