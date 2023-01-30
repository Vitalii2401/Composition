package com.example.composition.presentation.choose_level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entities.Level

class ChooseLevelFragment : Fragment() {

    private lateinit var binding: FragmentChooseLevelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }

            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }

            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }

            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }
}