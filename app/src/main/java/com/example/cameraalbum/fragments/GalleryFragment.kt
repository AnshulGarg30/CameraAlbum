package com.example.cameraalbum.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cameraalbum.R
import com.example.cameraalbum.adapter.ImageAdapter
import com.example.cameraalbum.databinding.FragmentGalleryBinding
import com.example.cameraalbum.model.Photo
import com.example.cameraalbum.viewmodel.GallaryViewmodel


class GalleryFragment : Fragment() {

    private lateinit var mViewModel: GallaryViewmodel
    lateinit var  binding :FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this)[GallaryViewmodel::class.java]

        val manager = GridLayoutManager(requireContext(),2)
        binding.galleryGridView.layoutManager = manager

        mViewModel.readAllData.observe(viewLifecycleOwner, Observer { photoList ->
            Log.e("photolist","list is ${photoList}")
            setEmptyzview(photoList)
        })

        binding.cameraclick.setOnClickListener{
            Navigation.findNavController(
                requireActivity(), R.id.fragment_container
            ).navigate(GalleryFragmentDirections
                .actionGallaryToCamera())

        }

    }

    override fun onResume() {
        super.onResume()
        if (!PermissionsFragment.hasPermissions(requireContext())) {
            Navigation.findNavController(requireActivity(),R.id.fragment_container).navigate(
                GalleryFragmentDirections.actionGallaryToPermission()
            )
        }
    }

    fun setEmptyzview(photoList: List<Photo>)
    {
        if(photoList.isNotEmpty())
        {
            binding.galleryGridView.visibility = View.VISIBLE
            binding.emptyview.visibility = View.GONE

            binding.galleryGridView.adapter = ImageAdapter(photoList)

        }else {
            binding.galleryGridView.visibility = View.GONE
            binding.emptyview.visibility = View.VISIBLE
        }
    }
}
