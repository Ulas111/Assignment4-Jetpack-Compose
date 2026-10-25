
package com.example.artspace

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {

    var currentArtwork by remember {
        mutableIntStateOf(1)
    }


    val imageRes = when (currentArtwork) {
        1 -> R.drawable.starry_night
        2 -> R.drawable.great_wave
        else -> R.drawable.girl_with_pearl
    }

    val artworkTitle = when (currentArtwork) {
        1 -> "The Starry Night"
        2 -> "The Great Wave off Kanagawa"
        else -> "Girl with a Pearl Earring"
    }

    val artworkArtist = when (currentArtwork) {
        1 -> "Vincent van Gogh (1889)"
        2 -> "Katsushika Hokusai (c. 1831)"
        else -> "Johannes Vermeer (c. 1665)"
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .size(260.dp)
                .border(6.dp, Color.LightGray)
                .background(Color(0xFFF2EDF6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = imageRes
                ),
                contentDescription = "$artworkTitle by $artworkArtist",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEAE6F2))
                .padding(16.dp)
        ) {
            Text(
                text = artworkTitle,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = artworkArtist,
                color = Color.DarkGray
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {
                    currentArtwork = if (currentArtwork == 1) {
                        3
                    } else {
                        currentArtwork - 1
                    }
                }
            ) {
                Text("Previous")
            }



            Button(
                onClick = {
                    currentArtwork = if (currentArtwork == 3) {
                        1
                    } else {
                        currentArtwork + 1
                    }
                }
            ) {
                Text("Next")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
