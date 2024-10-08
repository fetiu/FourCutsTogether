package com.foke.together.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foke.together.domain.input.GetCameraSourceTypeInterface
import com.foke.together.domain.input.SetCameraSourceTypeInterface
import com.foke.together.domain.interactor.entity.CameraSourceType
import com.foke.together.util.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getCameraSourceTypeInterface: GetCameraSourceTypeInterface,
    private val setCameraSourceTypeInterface: SetCameraSourceTypeInterface
): ViewModel() {
    val cameraSourceType = getCameraSourceTypeInterface().shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        replay = 1
    )

    // TODO: need to change usecase
    private val _cameraIPAddress = MutableStateFlow("0.0.0.0")
    val cameraIPAddress = _cameraIPAddress.asStateFlow()

    fun setCameraSourceType(index: Int){
        setCameraSourceType(CameraSourceType.entries[index])
    }

    fun setCameraSourceType(type: CameraSourceType){
        viewModelScope.launch {
            AppLog.e(TAG, "setCameraSourceType", "type: $type")
            setCameraSourceTypeInterface(type)
        }
    }

    fun setCameraIPAddress(address: String){
        viewModelScope.launch {
            _cameraIPAddress.emit(address)
            // TODO: add usecase
            // setCameraIPAddress(this)
        }
    }

    companion object {
        private val TAG = SettingViewModel::class.java.simpleName
    }
}