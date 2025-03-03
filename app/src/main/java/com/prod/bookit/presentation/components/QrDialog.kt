package com.prod.bookit.presentation.components

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.prod.bookit.domain.model.ProfileBookingModel

@Composable
fun QrDialog(
    booking: ProfileBookingModel,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("QR-код для \"${booking.title}\"") },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                QrCodeGeneratorImage(
                    content = booking.id,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть")
            }
        }
    )
}

@Composable
fun QrCodeGeneratorImage(content: String, modifier: Modifier = Modifier) {
    val qrBitmap = remember(content) { generateQrCode(content) }

    qrBitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "QR Code",
            modifier = modifier
        )
    }
}

fun generateQrCode(content: String): Bitmap? {
    return try {
        val size = 512
        val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size)
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        for (x in 0 until size) {
            for (y in 0 until size) {
                bmp.setPixel(x, y, if (bits.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        bmp
    } catch (e: Exception) {
        Log.e("INFOG", "Ошибка генерации QR-кода", e)
        null
    }
}
