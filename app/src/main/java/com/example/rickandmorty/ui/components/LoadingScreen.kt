import coil.ImageLoader
import coil.decode.GifDecoder
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingScreen() {
    // Configuración personalizada de ImageLoader para GIFs
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    // Uso de AsyncImage con el ImageLoader personalizado
    val painter = rememberAsyncImagePainter(
        model = "https://imgur.com/mZas4AF.gif",
        imageLoader = imageLoader
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222222)), // Fondo con color #222222
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "Loading Animation",
            modifier = Modifier.fillMaxSize(),

        )
    }
}

