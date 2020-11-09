package com.unprg.alquilafacil.ui.main.chats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.ui.anuncio.AnuncioActivity
import com.unprg.alquilafacil.util.ResponseType
import kotlinx.android.synthetic.main.fragment_myads.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatsFragment : Fragment(),  ChatsAdapter.MyViewHolder.OnAdapterListener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val adapter = ChatsAdapter(ArrayList(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
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

        startActivity(Intent(requireContext(), AnuncioActivity::class.java).apply {
            putExtra("idanuncio", anuncio.idanuncio)
        })
    }
}