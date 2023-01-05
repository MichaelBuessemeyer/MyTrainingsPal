package com.example.mytrainingpal.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

// Reference: Dr BigKitkat answer on github + Coil library docs
// https://stackoverflow.com/questions/60229555/adding-gif-into-jetpack-compose
// about AsyncImagePainter --> https://coil-kt.github.io/coil/compose/


@Composable
fun GifImage(
    gifPath: String,
    size: Int
) {

    val context = LocalContext.current
    // Getting the resource id of the gif using its path.
    // Code from https://stackoverflow.com/questions/70062705/how-can-i-get-drawable-resource-by-string was used.
    val gifResourceId = remember(gifPath) {
        context.resources.getIdentifier(
            gifPath,
            "drawable",
            context.packageName
        )
    }

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifResourceId).apply(block = {
                size(size)
            }).build(),
            imageLoader = imageLoader,
        ),
        contentDescription = null,
    )
}
