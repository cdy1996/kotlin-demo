package com.cdy.kotlin.demo

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.io.core.ByteReadPacket
import kotlinx.io.core.buildPacket
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer


private fun copyFile(source: File, dest: File) {
    FileInputStream(source).use { input ->
        FileOutputStream(dest).use { output ->
            {
                val buf = ByteArray(1024)
                while (true) {
                    val byteRead = input.read(buf)
                    if (byteRead <= 0) {
                        break
                    }
                    output.write(buf, 0, byteRead)
                }
            }
        }
    }
}

private fun copyFile2(source: File, dest: File) {
    FileInputStream(source).channel.use { input ->
        FileOutputStream(dest).channel.use { output ->
            {
                val buf = ByteBuffer.allocate(1024)
                while (true) {
                    buf.clear()
                    val byteRead = input.read(buf)
                    if (byteRead <= 0) {
                        break
                    }
                    buf.flip()
                    output.write(buf)
                }
            }
        }
    }
}


fun testIO() = runBlocking {

    val  tee = Channel<ByteReadPacket>()

    val file = File("./build.gradle")
    val packet = buildPacket(1) {
        writeStringUtf8(file.readText())
    }
    launch {
        tee.send(packet.copy())
    }
    launch {
        println(tee.receive().readText())
    }

    delay(1000)
    Unit


}