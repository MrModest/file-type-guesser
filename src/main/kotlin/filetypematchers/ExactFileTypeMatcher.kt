package filetypematchers

import java.io.RandomAccessFile

class ExactFileTypeMatcher(private val magicNumbers: Array<Byte>) : FileTypeMatcher() {
    override fun matchesInternal(file: RandomAccessFile): Boolean {
        return magicNumbers.all { file.readByte() == it }
    }

    override val priority: Int
        get() = magicNumbers.size;
}