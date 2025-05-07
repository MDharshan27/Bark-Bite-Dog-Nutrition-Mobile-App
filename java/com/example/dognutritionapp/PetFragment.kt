package com.example.dognutritionapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment

class PetFragment : Fragment(R.layout.fragment_pet) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up back button navigation
        val backButton: View = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }

        // Set up first video with play/pause functionality
        val videoView1: VideoView = view.findViewById(R.id.videoView1)
        val playButton1: ImageView = view.findViewById(R.id.playButton1)
        val videoPath1 =
            "android.resource://" + requireContext().packageName + "/" + R.raw.ic_video1
        videoView1.setVideoPath(videoPath1)

        val mediaController1 = MediaController(requireContext())
        mediaController1.setAnchorView(videoView1)
        videoView1.setMediaController(mediaController1)

        playButton1.setOnClickListener {
            if (videoView1.isPlaying) {
                videoView1.pause()
                playButton1.setImageResource(R.drawable.ic_play)
            } else {
                videoView1.start()
                playButton1.setImageResource(R.drawable.ic_pause)
            }
        }

        val videoTitle1: TextView = view.findViewById(R.id.videoTitle1)
        val videoDescription1: TextView = view.findViewById(R.id.videoDescription1)
        videoTitle1.text = "1.Amazing Pet Video"
        videoDescription1.text = "This video showcases adorable pets playing around and having fun."

        // Set up second video with play/pause functionality
        val videoView2: VideoView = view.findViewById(R.id.videoView2)
        val playButton2: ImageView = view.findViewById(R.id.playButton2)
        val videoPath2 =
            "android.resource://" + requireContext().packageName + "/" + R.raw.ic_video2
        videoView2.setVideoPath(videoPath2)

        val mediaController2 = MediaController(requireContext())
        mediaController2.setAnchorView(videoView2)
        videoView2.setMediaController(mediaController2)

        playButton2.setOnClickListener {
            if (videoView2.isPlaying) {
                videoView2.pause()
                playButton2.setImageResource(R.drawable.ic_play)
            } else {
                videoView2.start()
                playButton2.setImageResource(R.drawable.ic_pause)
            }
        }

        val videoTitle2: TextView = view.findViewById(R.id.videoTitle2)
        val videoDescription2: TextView = view.findViewById(R.id.videoDescription2)
        videoTitle2.text = "2.Funny Pet Moments"
        videoDescription2.text = "Watch these pets in their funniest moments caught on camera!"

        // Set up third video with play/pause functionality
        val videoView3: VideoView = view.findViewById(R.id.videoView3)
        val playButton3: ImageView = view.findViewById(R.id.playButton3)
        val videoPath3 =
            "android.resource://" + requireContext().packageName + "/" + R.raw.ic_video3
        videoView3.setVideoPath(videoPath3)

        val mediaController3 = MediaController(requireContext())
        mediaController3.setAnchorView(videoView3)
        videoView3.setMediaController(mediaController3)

        playButton3.setOnClickListener {
            if (videoView3.isPlaying) {
                videoView3.pause()
                playButton3.setImageResource(R.drawable.ic_play)
            } else {
                videoView3.start()
                playButton3.setImageResource(R.drawable.ic_pause)
            }
        }

        // Set title and description for the third video
        val videoTitle3: TextView = view.findViewById(R.id.videoTitle3)
        val videoDescription3: TextView = view.findViewById(R.id.videoDescription3)
        videoTitle3.text = "3.Training Tips for Puppies"
        videoDescription3.text =
            "Essential training tips for puppies to ensure a well-behaved companion."

        // Add articles about dog nutrition
        val articleTitle1: TextView = view.findViewById(R.id.article_title1)
        val articleSummary1: TextView = view.findViewById(R.id.article_summary1)
        articleTitle1.text = "1. Importance of Balanced Diet for Dogs"
        articleSummary1.text =
            "A balanced diet provides the essential nutrients dogs need to thrive, impacting everything from coat quality to energy levels and immune health. Dogs require a mix of proteins, fats, vitamins, minerals, and fiber to maintain optimal health. Feeding a well-rounded diet tailored to their age, breed, and activity level can enhance longevity and reduce the risk of chronic conditions. When planning your dog’s meals, avoid human foods that can disrupt their nutritional balance and consult your vet for advice on portion sizes."

        val articleTitle2: TextView = view.findViewById(R.id.article_title2)
        val articleSummary2: TextView = view.findViewById(R.id.article_summary2)
        articleTitle2.text = "2. Essential Nutrients Every Dog Needs"
        articleSummary2.text =
            "Dogs rely on six essential nutrients: water, proteins, fats, carbohydrates, vitamins, and minerals. Each plays a critical role in their growth and bodily functions. Proteins help build and repair muscles, while fats provide energy and aid in nutrient absorption. Carbohydrates offer fiber for digestion, while vitamins and minerals support immunity and metabolic functions. Ensuring your dog's diet includes these nutrients can help prevent deficiencies and promote overall well-being."

        val articleTitle3: TextView = view.findViewById(R.id.article_title3)
        val articleSummary3: TextView = view.findViewById(R.id.article_summary3)
        articleTitle3.text = "3. Homemade Dog Food Recipes"
        articleSummary3.text =
            "Homemade dog food offers a way to control ingredients and tailor meals to specific needs. Use quality proteins like chicken or beef, paired with grains (like rice) and vegetables for balanced nutrition. Avoid adding salt, sugar, and foods that can harm dogs, such as onions or garlic. Consult your veterinarian before switching to homemade food, as they can provide insights into portion control and supplement needs. Homemade meals should also be varied to ensure all nutrient requirements are met."

        val articleTitle4: TextView = view.findViewById(R.id.article_title4)
        val articleSummary4: TextView = view.findViewById(R.id.article_summary4)
        articleTitle4.text = "4. Foods to Avoid Feeding Your Dog"
        articleSummary4.text =
            "Certain foods are hazardous to dogs and should be kept out of reach. Common offenders include chocolate, grapes, onions, garlic, and anything containing xylitol, an artificial sweetener. These foods can cause issues ranging from mild stomach upset to severe poisoning. Knowing which foods are toxic and understanding the symptoms of poisoning (like vomiting and lethargy) can help you act quickly in an emergency. When in doubt, only give your dog food specifically made for pets."

    }
}