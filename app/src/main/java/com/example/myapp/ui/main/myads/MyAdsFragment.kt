package com.example.myapp.ui.main.myads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.data.usecase.AnuncioUseCase
import com.example.myapp.domain.model.Anuncio
import com.example.myapp.util.ResponseType
import kotlinx.android.synthetic.main.fragment_myads.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAdsFragment : Fragment(),  MisAnunciosAdapter.MyViewHolder.OnAdapterListener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val adapter = MisAnunciosAdapter(ArrayList(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_myads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView = myAdsRecyclerView
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        callServiceMisAnuncios()
    }

    private fun callServiceMisAnuncios() {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                AnuncioUseCase(requireContext()).misAnuncios()
            }

            when (response) {
                is ResponseType.Success -> {
                    adapter.updateList(response.value)
                }
                is ResponseType.Error -> {
                    Toast.makeText(requireContext(), response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onItemClickListener(anuncio: Anuncio, view: View) {
        Toast.makeText(requireContext(), anuncio.idanuncio.toString(), Toast.LENGTH_SHORT).show()
    }
}