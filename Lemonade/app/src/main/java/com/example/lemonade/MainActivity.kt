package com.example.lemonade

import android.media.Image
import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun PickAndSqueeze(modifier: Modifier = Modifier) {

    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val contentDescriptionResource = when (step) {
        1 -> R.string.treeContent
        2 -> R.string.lemonContent
        3 -> R.string.glassContent
        else -> R.string.emptyContent
    }

    val textResource = when (step) {
        1 -> R.string.Tree
        2 -> R.string.Lemon
        3 -> R.string.Glass
        else -> R.string.Empty
    }

    var counter = 0

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            when (step) {
                1 -> {
                    squeezeCount = (2..4).random()
                    step = 2
                }
                2 -> {
                    squeezeCount--
                    if (squeezeCount == 0)
                        step = 3
                }
                3 -> {
                    step = 4
                }
                4 -> {
                    step = 1
                }
            }
        },
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFB3E5FC), shape = RoundedCornerShape(64.dp))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = contentDescriptionResource)
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(id = textResource),
            color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Signiora il limoni signioraaaaa",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFFFFEB3B)
                )
            )
        }

    ) { innerPadding ->
        PickAndSqueeze(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center)
        )
    }

}
