package com.example.myapp.ui.main.publish

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp.R
import com.example.myapp.ui.publish.PublishActivity
import kotlinx.android.synthetic.main.fragment_publish.*

class PublishFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category_casa.setOnClickListener {
            clickCategory(1)
        }
        category_departamento.setOnClickListener {
            clickCategory(2)
        }
        category_habitacion.setOnClickListener {
            clickCategory(3)
        }
        category_oficina.setOnClickListener {
            clickCategory(4)
        }
    }

    fun clickCategory(idcategoria: Int) {
        requireActivity().startActivity(Intent(requireContext(), PublishActivity::class.java).apply {
            putExtra("idcategoria", idcategoria)
        })
    }
}