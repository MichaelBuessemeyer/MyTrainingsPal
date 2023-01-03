package com.example.mytrainingpal.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

// Icon taken from https://uxwing.com/shoulder-pain-icon/ and modified.

fun ShoulderPainIcon(): ImageVector {
    return Builder(
        name = "Shoulder-pain-icon", defaultWidth = 24.dp,
        defaultHeight = 17.4092.dp, viewportWidth = 148.75508f, viewportHeight =
        107.90434f
    ).apply {
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 8.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveToRelative(144.56f, 23.094f)
            curveToRelative(0.64f, 4.46f, -0.18f, 8.07f, -2.5f, 10.83f)
            curveToRelative(-2.25f, 2.67f, -5.82f, 4.36f, -10.76f, 5.07f)
            curveToRelative(-4.16f, 0.59f, -8.49f, 1.02f, -12.87f, 1.45f)
            curveToRelative(-4.56f, 0.45f, -9.17f, 0.9f, -13.88f, 1.59f)
            curveToRelative(-0.12f, 0.02f, -0.25f, 0.03f, -0.37f, 0.02f)
            curveToRelative(-11.37f, 0.52f, -21.79f, 1.02f, -28.18f, 5.16f)
            curveToRelative(-6.1f, 3.95f, -8.77f, 11.68f, -5.42f, 26.93f)
            lineToRelative(2.45f, 7.35f)
            curveToRelative(0.24f, 0.72f, 0.1f, 1.48f, -0.32f, 2.06f)
            verticalLineToRelative(0.0f)
            curveToRelative(-1.88f, 2.62f, -3.23f, 5.48f, -3.93f, 8.66f)
            curveToRelative(-0.69f, 3.19f, -0.72f, 7.77f, 0.04f, 11.69f)
            horizontalLineToRelative(-4.51f)
            curveToRelative(-0.89f, -4.56f, -0.85f, -8.87f, -0.02f, -12.66f)
            curveToRelative(0.74f, -3.41f, 2.12f, -6.52f, 4.02f, -9.39f)
            lineToRelative(-2.1f, -6.31f)
            curveToRelative(-0.04f, -0.1f, -0.07f, -0.2f, -0.09f, -0.3f)
            curveToRelative(-3.89f, -17.63f, -0.38f, -26.84f, 7.37f, -31.87f)
            curveToRelative(7.42f, -4.81f, 18.44f, -5.35f, 30.45f, -5.9f)
            curveToRelative(4.62f, -0.67f, 9.35f, -1.14f, 14.02f, -1.6f)
            curveToRelative(4.27f, -0.42f, 8.49f, -0.83f, 12.68f, -1.43f)
            curveToRelative(3.78f, -0.54f, 6.41f, -1.71f, 7.91f, -3.49f)
            curveToRelative(1.43f, -1.69f, 1.91f, -4.11f, 1.46f, -7.22f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 8.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveToRelative(135.7f, 103.654f)
            lineToRelative(-10.65f, -0.37f)
            verticalLineToRelative(0.01f)
            horizontalLineToRelative(-0.23f)
            verticalLineToRelative(-0.01f)
            curveToRelative(-7.62f, 0.0f, -12.82f, 0.0f, -19.5f, -5.4f)
            curveToRelative(-2.14f, -1.73f, -4.1f, -3.83f, -5.89f, -6.28f)
            curveToRelative(-0.37f, -0.5f, -0.72f, -1.01f, -1.08f, -1.54f)
            curveToRelative(0.02f, 1.67f, 0.07f, 3.35f, 0.11f, 5.02f)
            curveToRelative(0.07f, 2.83f, 0.14f, 6.47f, 0.14f, 8.81f)
            horizontalLineToRelative(-4.6f)
            curveToRelative(0.0f, -2.95f, -0.06f, -6.23f, -0.12f, -8.7f)
            curveToRelative(-0.14f, -5.38f, -0.27f, -10.77f, 0.13f, -16.1f)
            curveToRelative(0.16f, -2.15f, -0.09f, -3.7f, -0.73f, -4.68f)
            curveToRelative(-0.53f, -0.82f, -1.45f, -1.3f, -2.71f, -1.47f)
            lineToRelative(0.61f, -4.54f)
            curveToRelative(2.63f, 0.36f, 4.63f, 1.51f, 5.94f, 3.52f)
            curveToRelative(1.2f, 1.85f, 1.71f, 4.33f, 1.47f, 7.52f)
            curveToRelative(-0.05f, 0.65f, -0.09f, 1.3f, -0.12f, 1.94f)
            lineToRelative(0.21f, -0.1f)
            curveToRelative(1.33f, 2.83f, 2.81f, 5.38f, 4.45f, 7.61f)
            curveToRelative(1.56f, 2.13f, 3.25f, 3.95f, 5.07f, 5.42f)
            curveToRelative(5.44f, 4.4f, 9.98f, 4.4f, 16.63f, 4.4f)
            verticalLineToRelative(-0.01f)
            horizontalLineToRelative(0.23f)
            verticalLineToRelative(0.01f)
            curveToRelative(0.02f, 0.0f, 0.05f, 0.0f, 0.07f, 0.0f)
            lineToRelative(10.72f, 0.37f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 1.69803f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveTo(48.321f, 2.003f)
            lineTo(46.737f, 15.384f)
            lineTo(22.485f, 2.632f)
            lineTo(49.54f, 32.873f)
            lineTo(52.273f, 20.568f)
            lineTo(76.177f, 33.999f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 1.69803f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveTo(17.023f, 75.325f)
            lineTo(29.1f, 81.303f)
            lineTo(8.956f, 99.878f)
            lineTo(46.516f, 84.524f)
            lineTo(35.839f, 77.825f)
            lineTo(56.506f, 59.806f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 1.69803f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveTo(20.454f, 34.986f)
            lineTo(27.218f, 46.641f)
            lineTo(0.166f, 50.996f)
            lineTo(39.957f, 58.947f)
            lineTo(34.758f, 47.464f)
            lineTo(61.939f, 43.861f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
            strokeLineWidth = 8.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = PathFillType.NonZero
        ) {
            moveToRelative(122.18f, 82.984f)
            curveToRelative(1.59f, 0.0f, 2.87f, 1.29f, 2.87f, 2.87f)
            curveToRelative(0.0f, 1.59f, -1.29f, 2.87f, -2.87f, 2.87f)
            curveToRelative(-1.59f, 0.0f, -2.87f, -1.29f, -2.87f, -2.87f)
            curveToRelative(0.0f, -1.59f, 1.28f, -2.87f, 2.87f, -2.87f)
            close()
        }
    }
        .build()

}

