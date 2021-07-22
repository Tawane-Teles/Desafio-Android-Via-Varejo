package com.viavarejo.desafio.android.tawane.hq.ui.home


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viavarejo.desafio.android.tawane.hq.utils.visible
import com.viavarejo.desafio.android.tawane.hq.model.CharacterResults
import com.viavarejo.desafio.android.tawane.hq.ui.home.adpater.MarvelRecyclerAdapter
import com.viavarejo.desafio.android.tawane.hq.ui.details.CharacterDetails
import com.viavarejo.desafio.android.tawane.hq.R
import com.viavarejo.desafio.android.tawane.hq.utils.gone
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()

    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var adapterMarvel = MarvelRecyclerAdapter(this::getCharacter)

    lateinit var intention: HomeViewModel.HomeIntention

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        iniViews()
        initViewModel()
        bindStates()
    }

    private fun iniViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.listmarvel)
        container = findViewById(R.id.container)
        setAdapter()
    }

    private fun initViewModel() {
        intention = HomeViewModel.HomeIntention(viewModel::takeIntention)
        intention.loadInitialData()
    }

    private fun bindStates() {
        viewModel.state.observeForever { state ->
            when (state) {
                is HomeViewModel.ScreenState.Loading -> {
                    progressBar?.visible()
                }

                is HomeViewModel.ScreenState.ApiError -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }

                is HomeViewModel.ScreenState.ApiSucesso -> {
                    progressBar?.gone()
                    adapterMarvel.marvelList = state.value.allResponse.results
                    adapterMarvel.notifyDataSetChanged()
                    Toast.makeText(this, "Carregado com sucesso", Toast.LENGTH_LONG).show()
                }

                is HomeViewModel.ScreenState.NavigateToDetails -> {
                    container?.visible()
                    navigateDetails()
                }
            }
        }
    }

    private fun navigateDetails() {
        val frameLayout = CharacterDetails.newInstant()
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container, frameLayout)
        transition.commit()
    }

    private fun setAdapter() {
        val manager = LinearLayoutManager(this)
        with(recyclerView!!) {
            layoutManager = manager
            adapter = adapterMarvel
        }
    }

    private fun getCharacter(obj: CharacterResults) {
        viewModel.savePosition(obj)
    }

    companion object {
      var container: FrameLayout? = null
    }

}