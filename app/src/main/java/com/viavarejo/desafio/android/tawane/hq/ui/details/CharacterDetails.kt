package com.viavarejo.desafio.android.tawane.hq.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.viavarejo.desafio.android.tawane.hq.R
import com.viavarejo.desafio.android.tawane.hq.ui.details.adapter.HqsRecyclerAdapter
import com.viavarejo.desafio.android.tawane.hq.ui.home.HomeActivity.Companion.container
import com.viavarejo.desafio.android.tawane.hq.utils.gone
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetails : Fragment() {

    private val viewModel: CharacterDetailsViewModel by viewModel()

    private var image: ImageView? = null
    private var imageBack: ImageView? = null
    private var textView: TextView? = null
    private var adapterHqs = HqsRecyclerAdapter()
    private var recyclerView: RecyclerView? = null
    lateinit var intention: CharacterDetailsViewModel.DetailsIntention

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initViewModel()
        bindStates()
    }

    private fun initViews(view: View) {
        image = view.findViewById(R.id.imageViewId)
        textView = view.findViewById(R.id.textViewId)
        imageBack = view.findViewById(R.id.imgBack)
        recyclerView = view.findViewById(R.id.hqsList)
    }

    private fun initViewModel() {
        intention = CharacterDetailsViewModel.DetailsIntention(viewModel::takeIntention)
        intention.loadInitViewModel()

        imageBack?.setOnClickListener {
            intention.navigateToHome()
        }
    }

    private fun bindStates() {
        viewModel.state.observeForever { state ->
            when (state) {
                is CharacterDetailsViewModel.ScreenState.GetPosition -> {
                    state.hqs?.heroData?.results?.let {
                        adapterHqs.marvelList = it
                        adapterHqs.notifyDataSetChanged()
                    }

                    textView?.text = state.value.name
                    Glide.with(image!!.context).load(state.value.thumbnail!!.getCompletePath())
                        .into(image!!)
                }
                is CharacterDetailsViewModel.ScreenState.NavigateToHome -> {
                    container?.gone()
                }
            }
        }
    }

    companion object {
        fun newInstant() = CharacterDetails()
    }

}