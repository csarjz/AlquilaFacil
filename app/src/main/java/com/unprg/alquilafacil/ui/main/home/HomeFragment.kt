package com.unprg.alquilafacil.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.request.anuncio.AnuncioFilterRequest
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.ui.anuncio.AnuncioActivity
import com.unprg.alquilafacil.util.ResponseType
import com.unprg.alquilafacil.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), AnuncioListAdapter.MyViewHolder.OnAdapterListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private val adapter = AnuncioListAdapter(ArrayList(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView = homeRecyclerView
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter

        callServiceListAnuncios()

        btnSearch.setOnClickListener{
            it.hideKeyboard()
            callServiceListAnuncios()
        }
    }

    private fun callServiceListAnuncios() {

        GlobalScope.launch (Dispatchers.Main) {
            val filtro = AnuncioFilterRequest()
            filtro.search = edtBuscar.text.toString()

            val response = withContext(Dispatchers.IO) {
                AnuncioUseCase(requireContext()).listAnuncios(filtro)
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
        startActivity(Intent(requireContext(), AnuncioActivity::class.java).apply {
            putExtra("idanuncio", anuncio.idanuncio)
        })
    }
}