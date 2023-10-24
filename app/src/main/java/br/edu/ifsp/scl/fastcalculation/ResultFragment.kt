package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var fragmentResultFragment: FragmentResultBinding
    private var points: PointsResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           points = it.getParcelable(Extras.POINTS_RESULT) ?: PointsResult(0f, 0)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       fragmentResultFragment = FragmentResultBinding.inflate(inflater, container, false)

        println(points.toString())
        fragmentResultFragment.apply {
            "%.1f".format(points?.points).also {
                pointsTv.text = it
            }

            "Você acertou: ${points?.hits} questões!".also{
                acertosTv.text = it
            }

            restartBt.setOnClickListener{
                (context as onPlayGame).onPlayGame()
            }
        }
        return fragmentResultFragment.root
    }

    companion object {
        @JvmStatic
        fun newInstance(points: PointsResult) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Extras.POINTS_RESULT, points)
                }
            }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible = false
    }
}