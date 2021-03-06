package com.stylingandroid.rendereffect

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

typealias FullscreenEffect = Pair<Boolean, RenderEffect?>

class MainActivityViewModel : ViewModel() {

    private val _fullscreen = MutableStateFlow(false)
    val fullscreen: StateFlow<Boolean> = _fullscreen
    fun setFullscreen(isFullscreen: Boolean) {
        _fullscreen.value = isFullscreen
    }

    private val _blur = MutableStateFlow(false)
    val blur: StateFlow<Boolean> = _blur
    fun setBlurred(isBlurred: Boolean) {
        _blur.value = isBlurred
    }

    private val _radiusX = MutableStateFlow(DEFAULT_BLUR)
    val radiusX: StateFlow<Float> = _radiusX
    fun setRadiusX(newValue: Float) {
        _radiusX.value = newValue
    }

    private val _radiusY = MutableStateFlow(DEFAULT_BLUR)
    val radiusY: StateFlow<Float> = _radiusY
    fun setRadiusY(newValue: Float) {
        _radiusY.value = newValue
    }

    private val _desaturate = MutableStateFlow(false)
    val desaturate: StateFlow<Boolean> = _desaturate
    fun setDesaturate(newValue: Boolean) {
        _desaturate.value = newValue
    }

    private val _saturation = MutableStateFlow(1f)
    val saturation: StateFlow<Float> = _saturation
    fun setSaturation(newValue: Float) {
        _saturation.value = newValue
    }

    private val desaturateEffectFlow =
        combine(_saturation, _desaturate) { saturationAmount, applyDesaturate ->
            if (applyDesaturate) {
                RenderEffect.createColorFilterEffect(
                    ColorMatrixColorFilter(
                        ColorMatrix().apply {
                            setSaturation(saturationAmount)
                        }
                    )
                )
            } else {
                null
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val blurEffectFlow = combine(_radiusX, _radiusY, _blur) { x, y, applyBlur ->
        if (applyBlur) {
            RenderEffect.createBlurEffect(x, y, Shader.TileMode.MIRROR)
        } else null
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val effectFlow: Flow<FullscreenEffect> =
        combine(
            _fullscreen,
            blurEffectFlow,
            desaturateEffectFlow
        ) { isFullscreen, blurEffect, desaturateEffect ->
            isFullscreen to if (blurEffect != null && desaturateEffect != null) {
                RenderEffect.createChainEffect(blurEffect, desaturateEffect)
            } else blurEffect ?: desaturateEffect
        }

    companion object {
        const val DEFAULT_BLUR = 8f
    }
}
