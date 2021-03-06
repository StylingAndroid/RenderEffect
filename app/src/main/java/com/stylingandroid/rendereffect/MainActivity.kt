package com.stylingandroid.rendereffect

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.google.android.material.slider.Slider
import com.stylingandroid.rendereffect.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.xRadius.bind(viewModel.radiusX, viewModel::setRadiusX)
        binding.yRadius.bind(viewModel.radiusY, viewModel::setRadiusY)
        binding.blurToggle.bind(viewModel.blur, viewModel::setBlurred)
        binding.fullscreenToggle.bind(viewModel.fullscreen, viewModel::setFullscreen)

        viewModel.fullscreenEffectFlow.onEach { (fullscreen, effect) ->
            if (fullscreen) {
                binding.root.setRenderEffect(effect)
                binding.image.setRenderEffect(null)
            } else {
                binding.root.setRenderEffect(null)
                binding.image.setRenderEffect(effect)
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun Slider.bind(flow: Flow<Float>, setter: (Float) -> Unit) {
        flow.onEach { newValue -> value = newValue }
            .launchIn(viewModel.viewModelScope)
        addOnChangeListener { _, value, _ ->
            setter(value)
        }
    }

    private fun CompoundButton.bind(flow: Flow<Boolean>, setter: (Boolean) -> Unit) {
        flow.onEach { newValue -> isChecked = newValue }
            .launchIn(viewModel.viewModelScope)
        setOnCheckedChangeListener { _, checked -> setter(checked) }
    }
}
