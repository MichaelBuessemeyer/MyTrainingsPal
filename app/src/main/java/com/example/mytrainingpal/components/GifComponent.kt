package com.example.mytrainingpal.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

// Reference: Dr BigKitkat answer on github + Coil library docs
// https://stackoverflow.com/questions/60229555/adding-gif-into-jetpack-compose
// about AsyncImagePainter --> https://coil-kt.github.io/coil/compose/


@Composable
fun GifImage(
    size: Int,
    // TODO: gifs saved under R.drawable.<gifName> (added through the GUI Resource Manager)
    gifPath: Any?
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifPath).apply(block = {
                size(size)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        //modifier = Modifier.fillMaxWidth()
    )
}
