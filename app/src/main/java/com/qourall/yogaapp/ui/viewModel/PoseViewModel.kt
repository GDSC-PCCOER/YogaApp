package com.qourall.yogaapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.qourall.yogaapp.YogaApplication
import com.qourall.yogaapp.data.localDB.FavPose
import com.qourall.yogaapp.data.localDB.Pose
import com.qourall.yogaapp.data.models.PoseDetails
import com.qourall.yogaapp.repository.YogaRepository
import kotlinx.coroutines.launch

class PoseViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = YogaRepository(application as YogaApplication)

    private var _isFav = MutableLiveData<Boolean>()
    val isFav : LiveData<Boolean>
        get() = _isFav

    private var _isAdded = MutableLiveData<Boolean>()
    val isAdded : LiveData<Boolean>
        get() = _isAdded

    private var _allCatPose = MutableLiveData<List<Pose>>()
    val allCatPose : LiveData<List<Pose>>
        get() = _allCatPose

    private var _allPose = MutableLiveData<List<Pose>>()
    val allPose : LiveData<List<Pose>>
        get() = _allPose

    private var _allFavPose = MutableLiveData<List<FavPose>>()
    val allFavPose : LiveData<List<FavPose>>
        get() = _allFavPose

    private var _poseDetails = MutableLiveData<PoseDetails>()
    val poseDetails : LiveData<PoseDetails>
        get() = _poseDetails


    fun getCatPoses(name : String) {
        viewModelScope.launch {
            _allCatPose.value =  repo.getCatAllPoses(name)
        }
    }

    fun getPoses() {
        viewModelScope.launch {
            _allPose.value =  repo.getAllPoses()
        }
    }

    fun getPoseDetails(id : Int) {
        viewModelScope.launch {
            _poseDetails.value =  repo.getPoseDetails(id)
        }
    }

    fun checkFav(id: Int) {
        viewModelScope.launch {
            _isFav.value = repo.checkFav(id)
        }
    }

    fun removeFav(poseId: Int) {
        viewModelScope.launch {
            repo.removeFav(poseId)
        }
    }

    fun addFav(poseId: Int) {
        viewModelScope.launch {
            _isAdded.value = repo.addFav(poseId)
        }
    }

    fun getAllFavPose() {
        viewModelScope.launch {
            _allFavPose.value = repo.getAllFavPose()
        }
    }

}