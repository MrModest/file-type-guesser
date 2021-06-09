package filetypematchers

import java.io.EOFException
import java.io.RandomAccessFile

class FuzzyFileTypeMatcher(private val magicNumbers: Array<Byte?>) : FileTypeMatcher() {

    override fun matchesInternal(file: RandomAccessFile): Boolean {
        for (b in magicNumbers){
            try {
                val c = file.readByte()
                if (b != null && c != b) {
                    return false
                }
            }
            catch (ex: EOFException){
                return false
            }
        }

        return true
    }

    override val priority: Int
        get() = magicNumbers.size;
}