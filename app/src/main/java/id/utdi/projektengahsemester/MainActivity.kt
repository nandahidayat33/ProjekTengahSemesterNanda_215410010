package id.utdi.projektengahsemester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.utdi.projektengahsemester.ui.theme.ProjekTengahSemesterTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjekTengahSemesterTheme {
                // Surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieCard()
                }
            }
        }
    }
}

data class Movie(val imageResource: Int, val moreInfo: String)

@Composable
fun MovieCard() {
    var currentMovieIndex by remember { mutableStateOf(0) }
    var isDescriptionVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val movies = listOf( //pada bagian ini memanggil gambar yang disediakan pada drwable.
                        //hal ini terdapat pada materi artspace pada teori pemrograman berbasis mobile
        Movie(
            R.drawable.film1,
            context.getString(R.string.film1_info)
        ),
        Movie(
            R.drawable.film2,
            context.getString(R.string.film2_info)
        ),
        Movie(
            R.drawable.film3,
            context.getString(R.string.film3_info)
        ),
        Movie(
            R.drawable.film4,
            context.getString(R.string.film4_info)
        ),
        Movie(
            R.drawable.film5,
            context.getString(R.string.film5_info)
        )
    )

    LazyColumn( //pada bagian ini digunakan untuk membuat daftar scroll
                //pada bagian ini juga didapat pada materi ke 7 pada pemrograman berbasis mobile tepatnya pada daftar scroll
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        item {
            // Column 1: Image
            MovieImage(imageResource = movies[currentMovieIndex].imageResource) {
                // Toggle description visibility on image click
                isDescriptionVisible = !isDescriptionVisible
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Column 3: Action Buttons (Previous, Next)
            Row( //bagian ini adalah row, yang terdapat tombol tindakan untuk next dan previous
                //pada bagian box, row, dan column terdapat pada bagian materi art space pada teori pemrograman berbasis mobile
                //selain itu terdapat juga perintah next previous yang didapat pada teori pemrograman berbasis mobile
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Action for the Previous button
                        currentMovieIndex = (currentMovieIndex - 1).coerceAtLeast(0)
                        isDescriptionVisible = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "<<<")
                }

                // Add some space between the "Previous" and "Next" buttons
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        // Action for the Next button
                        currentMovieIndex = (currentMovieIndex + 1) % movies.size
                        isDescriptionVisible = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = ">>>")
                }
            }

            // More Info Button
            Button( //pada bagian ini terdapat button untuk melihat informasi lebih lanjut mengenai film\
                    //dan setelah di klik maka dapat di scroll
                onClick = {
                    // Action for the More Info button
                    isDescriptionVisible = !isDescriptionVisible
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = if (isDescriptionVisible) "Hide Info" else "More Info")
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Informasi tambahan
            if (isDescriptionVisible) {
                Text(
                    text = movies[currentMovieIndex].moreInfo,
                    fontSize = 16.sp,
                    color = Color.Blue
                )
            }
        }
    }
}

@Composable
fun MovieImage(imageResource: Int, onClick: () -> Unit) {
    Image( //bagian ini yang mengatur gambar dari film//
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(600.dp)
            .padding(0.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    ProjekTengahSemesterTheme {
        MovieCard()
    }
}
