package com.example.thriftstore.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.thriftstore.R
import com.example.thriftstore.repository.userRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class user_fragment : Fragment() {
    private lateinit var userIcon:ImageView
    private lateinit var imageButton:ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.userfragment, container, false)

        userIcon = view.findViewById(R.id.usericon)
        imageButton = view.findViewById(R.id.imageButton)


        imageButton.setOnClickListener {
            loadPopUpMenu()
        }


        return  view
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }


    // Load pop up menu
    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(requireContext(), imageButton)
        popupMenu.menuInflater.inflate(R.menu.gallerymenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera -> openCamera()
                R.id.menuGallery -> openGallery()
            }
            true
        }
        popupMenu.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage: Uri? = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = requireActivity().contentResolver
                val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                userIcon.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                userIcon.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }


    private fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
    private fun uploadImage() {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val mimeType = getMimeType(file.path);
            val reqFile = RequestBody.create(MediaType.parse(mimeType!!), file)
            val body = MultipartBody.Part.createFormData("file", file.name, reqFile)

            CoroutineScope(Dispatchers.IO).launch {
                try {

                    val userRepository = userRepository()
//                    val response = userRepository.uploadImage(body)
//                    if (response.success == true) {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }

    private fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? {
        var file: File? = null
        return try {
            file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                .toString() + File.separator + fileNameToSave)
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

}
















