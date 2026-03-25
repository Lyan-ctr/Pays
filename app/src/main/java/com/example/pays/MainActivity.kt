package com.example.pays
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.pays.R

// Classe du tp country
data class Country(
    val name: String,
    val capital: String,
    val code: String,
    val flagRes: Int
)
// classe du tp bonus
 data class Topic(
    val name: String,
    val courseCount: Int,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CountryList()
                    //pour excuter le tp bonus on'appelera la fonction
                    // topicgrid
                    //TopicGrid()
                }
            }
        }
    }
}
// PARTIE : 1 tp de country liste pays
@Composable
fun CountryList() {
    val countries = listOf(
        Country("RD Congo", "Kinshasa", "CD", R.drawable.rdc),
        Country("France", "Paris", "FR", R.drawable.france),
        Country("Canada", "Ottawa", "CA", R.drawable.canada),
        Country("Angleterre", "Londres", "UK", R.drawable.engleterre),
        Country("Ukraine", "Kyiv", "UA", R.drawable.ukraine)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(countries) { item ->
            CountryItem(item)
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // IMAGE Drapeau
            Image(
                painter = painterResource(id = country.flagRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Nom + Capitale et Code
            Column {
                // Le Nom du pays
                Text(text = country.name, style = MaterialTheme.typography.titleLarge)

                //  On appelle les deux variables
                Text(
                    text = "Capitale : ${country.capital} | Code : ${country.code}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// PARTIE 2 : tp bonus

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {

    val topics = listOf(
        Topic("Architecture", 58, R.drawable.architecture),
        Topic("Design", 421, R.drawable.design),
        Topic("Business", 78, R.drawable.business),
        Topic("Photography", 121, R.drawable.photography)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic) {
    Card {
        Row {
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = topic.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp) // Petite taille pour l'icône
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = topic.courseCount.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}