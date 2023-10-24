package br.edu.ifsp.scl.fastcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.edu.ifsp.scl.fastcalculation.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), onPlayGame, onResultGame {
    private val actitityGameBinding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }
    private lateinit var settings: Settings
    private lateinit var pointsResult: PointsResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(actitityGameBinding.root)
        setSupportActionBar(actitityGameBinding.gametTbIn.gameTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = getString(R.string.game)
        }

        settings = intent.getParcelableExtra(Extras.EXTRA_SETTINGS) ?: Settings()

        pointsResult = intent.getParcelableExtra(Extras.POINTS_RESULT) ?: PointsResult(0f, 0)

        supportFragmentManager.beginTransaction().replace(R.id.gameFl, WelcomeFragment.newInstance(settings)).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.restartGameMi -> {
                onPlayGame()
                true
            }
            R.id.exitGameMi -> {
                finish()
                true
            }
            else -> {false}
        }
    }

    override fun onPlayGame() {
        supportFragmentManager.beginTransaction().replace(R.id.gameFl, GameFragment.newInstance(settings)).commit()
    }

    override fun onResultGame(pointsResult: PointsResult) {
        println("GameActitivity")
        println(pointsResult.toString())
        supportFragmentManager.beginTransaction().replace(R.id.gameFl, ResultFragment.newInstance(pointsResult)).commit()
    }
}