package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var fragmentResultFragment: FragmentResultBinding
    private var points: PointsResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           points = it.getParcelable("POINTS") ?: PointsResult(0f)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       fragmentResultFragment = FragmentResultBinding.inflate(inflater, container, false)

        println(points.toString())
            fragmentResultFragment.apply {
                "%.1f".format(points?.points).also {
                resultTv.text = it
                }
            }
        return fragmentResultFragment.root
    }

    companion object {
        @JvmStatic
        fun newInstance(points: PointsResult) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("POINTS", points)
                }
            }
    }
}