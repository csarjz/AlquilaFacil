package com.unprg.alquilafacil.ui.publish

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.unprg.alquilafacil.R
import com.unprg.alquilafacil.data.usecase.AnuncioUseCase
import com.unprg.alquilafacil.util.ResponseType
import kotlinx.android.synthetic.main.fragment_publish_images.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PublishImagesFragment(val idanuncio: Int) : Fragment() {
    private val PICK_IMAGE = 100
    private var picturePath: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        imagenAnuncio.setOnClickListener{
            selectImage()
        }

        btnPublicar.setOnClickListener{
            if (picturePath.isNotBlank()) {
                //callServiceSaveImageAnuncio(idanuncio, picturePath)
                //TODO guardar imagen con retrofit y eliminar las 2 lineas siguientes

                Toast.makeText(requireContext(), "Su anuncio fue publicado!", Toast.LENGTH_LONG).show()
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Seleccione una imagan", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun selectImage() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE && data != null) {
            when (requestCode) {
                PICK_IMAGE -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? = requireActivity().contentResolver
                            .query(selectedImage, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                            picturePath = cursor.getString(columnIndex)
                            imagenAnuncio.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                            cursor.close()
                        }
                    }
                }
            }
        }
    }

    private fun callServiceSaveImageAnuncio(idanuncio: Int, filePath: String) {

        GlobalScope.launch (Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                val file = File(filePath)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                var filePart = MultipartBody.Part.createFormData("upload_file", file.name, requestBody)
                AnuncioUseCase(requireContext()).saveImageAnuncio(idanuncio, filePart)
            }

            when (response) {
                is ResponseType.Success -> {
                    Toast.makeText(requireContext(), "Su anuncio fue publicado!", Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
                is ResponseType.Error -> {
                    Toast.makeText(requireContext(), response.value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}